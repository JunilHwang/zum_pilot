<template>
  <main>
    <VideoPlayer />
    <VideoArticle
      class="video-article"
      v-for="(video, k) in videoList" :key="k"
      v-bind="{ ...video }">
    </VideoArticle>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { VIDEO_POPULAR_FETCH } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const videoList = state => state.video.videoList;
const components = { VideoPlayer, VideoArticle };

export default {
  components,
  computed: mapState({ videoList }),
  created() {
    this.$store.dispatch(VIDEO_POPULAR_FETCH);
  },
};
</script>
