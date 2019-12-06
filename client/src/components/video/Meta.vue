<template>
  <ul :class="classPrefix">
    <li :class="metaTitle" v-html="title" />
    <li :class="metaBottom" v-if="!~[viewCount, likeCount].indexOf(undefined)">
      <span v-if="viewCount !== undefined" :class="metaViewCount">
        <FAI icon="eye" />
        {{ viewCount }}
      </span>
      <span v-if="likeCount !== undefined" :class="metaLike">
        <FAI icon="thumbs-up" @click.prevent="incrementLike" />
        {{ likeCount }}
      </span>
    </li>
    <li :class="metaSlot" v-if="$slots.default !== undefined">
      <slot></slot>
    </li>
  </ul>
</template>
<script>
import { mapState } from 'vuex';
import { VIDEO_LIKE } from '@/middleware/store/mutations-type';

const token = state => state.user.token;

export default {
  computed: {
    ...mapState({ token }),
    metaTitle() {
      return `${this.classPrefix}__title`;
    },
    metaBottom() {
      return `${this.classPrefix}__bottom`;
    },
    metaSlot() {
      return `${this.classPrefix}__slot`;
    },
    metaViewCount() {
      return `${this.classPrefix}__view-count`;
    },
    metaLike() {
      const { classPrefix, userLiked } = this;
      return `${classPrefix}__like ${this.token && userLiked ? 'active' : ''}`;
    },
  },
  methods: {
    incrementLike() {
      this.$store.dispatch(VIDEO_LIKE);
    },
  },
  props: ['classPrefix', 'title', 'userLiked', 'viewCount', 'likeCount'],
};
</script>
