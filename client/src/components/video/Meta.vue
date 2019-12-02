<template>
  <ul class="video-meta">
    <li class="video-meta__title" v-html="video.selectedVideo.title" />
    <li class="video-meta__bottom">
      <span class="video-meta__view-count">
        <FAI icon="eye" />
        {{ video.selectedVideo.viewCount }}
      </span>
      <span
        class="video-meta__like"
        :class="{ active: video.selectedVideo.userLiked }"
      >
        <FAI icon="thumbs-up" />
        {{ video.selectedVideo.likeCount }}
      </span>
    </li>
  </ul>
</template>
<script>
import { mapState } from 'vuex';

export default {
  computed: mapState(['video']),
};
</script>
