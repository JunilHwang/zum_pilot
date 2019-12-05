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

const video = state => state.video.selectedVideo;
const user = state => state.user;

export default {
  computed: mapState({ video, user }),
  methods: {
    incrementLike() {
      if (this.user.token === null) {
        alert('로그인 후 이용해주세요');
      }
    },
  },
};
</script>
