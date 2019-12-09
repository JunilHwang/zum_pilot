<template>
  <ul :class="classPrefix">
    <li :class="metaTitle" v-html="title" />
    <li :class="metaBottom">
      <span
        v-if="bookmarkHide"
        :class="metaBookmark"
        @click="bookmarking"
      >
        <FAI icon="star" />
      </span>
      <span
        v-if="viewCount !== undefined"
        :class="metaViewCount"
      >
        <FAI icon="eye" /> {{ viewCount }}
      </span>
      <span
        v-if="likeCount !== undefined"
        :class="metaLike"
        @click.prevent="incrementLike"
      >
        <FAI icon="thumbs-up" /> {{ likeCount }}
      </span>
      <span
        v-if="popularPoint !== undefined"
        class="popular-meta__point"
        v-html="`인기도 ${popularPoint}`"
      />
    </li>
  </ul>
</template>
<script>
import { mapState } from 'vuex';
import { VIDEO_LIKE, VIDEO_BOOKMARK } from '@/middleware/store/mutations-type';

const token = state => state.user.token;
const bookmark = state => state.user.bookmark;

export default {
  computed: {
    ...mapState({ token, bookmark }),
    metaTitle() {
      return `${this.classPrefix}__title`;
    },
    metaBottom() {
      return `${this.classPrefix}__bottom`;
    },
    metaBookmark() {
      const { classPrefix, bookmark: list, idx } = this;
      const active = list.find(v => v.idx === idx) ? 'active' : '';
      return `${classPrefix}__bookmark ${active}`;
    },
    metaViewCount() {
      return `${this.classPrefix}__view-count`;
    },
    metaLike() {
      const { classPrefix, userLiked } = this;
      return `${classPrefix}__like ${this.token && userLiked ? 'active' : ''}`;
    },
    bookmarkHide() {
      const hide = this.hide || [];
      return hide.indexOf('bookmark') === -1;
    },
  },
  methods: {
    incrementLike() {
      this.$store.dispatch(VIDEO_LIKE, this.idx);
    },
    bookmarking() {
      this.$store.dispatch(VIDEO_BOOKMARK, this.idx);
    },
  },
  props: ['classPrefix', 'idx', 'title', 'userLiked', 'viewCount', 'likeCount', 'hide', 'popularPoint'],
};
</script>
