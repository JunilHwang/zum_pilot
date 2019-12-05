<template>
  <ul class="video-meta">
    <li class="video-meta__title" v-html="video.title" />
    <li class="video-meta__bottom">
      <span class="video-meta__view-count">
        <FAI icon="eye" />
        {{ video.viewCount }}
      </span>
      <span
        class="video-meta__like"
        :class="{ active: video.userLiked }">
        <FAI icon="thumbs-up"
             :class="{ active: video.userLiked }"
             @click.prevent="incrementLike"
        />
        {{ video.likeCount }}
      </span>
    </li>
  </ul>
</template>
<script>
import { mapState } from 'vuex';
import { MODAL_OPEN, MODAL_PROPERTY } from '@/middleware/store/modal/const';

const video = state => state.video.selectedVideo;
const user = state => state.user;
const message = '로그인 후 이용해주세요';

export default {
  computed: mapState({ video, user }),
  methods: {
    incrementLike() {
      if (this.user.token === null) {
        this.$store.commit(MODAL_OPEN, 'alert');
        this.$store.commit(MODAL_PROPERTY, { message });
      }
    },
  },
};
</script>
