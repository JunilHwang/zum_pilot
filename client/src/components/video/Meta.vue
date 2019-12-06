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
import { VIDEO_LIKE } from '@/middleware/store/mutations-type';

export default {
  computed: mapState({
    video: state => state.video.selectedVideo,
  }),
  methods: {
    incrementLike() {
      this.$store.dispatch(VIDEO_LIKE);
    },
  },
};
</script>
