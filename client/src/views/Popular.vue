<template>
  <main>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <VideoArticle
      v-for="(video, k) in videoList"
      :title="video.title"
      :thumbnail="video.thumbnail"
      :viewCount="video.viewCount"
      :likeCount="video.likeCount"
      :popularPoint="video.popularPoint"
      :key="k"
      :class="{ active: selectedVideo && selectedVideo.idx === video.idx }"
      @select="selectVideo(video)"
    />
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { VIDEO_POPULAR_FETCH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const videoList = state => state.video.videoList;
const selectedVideo = state => state.video.selectedVideo;
const components = { VideoPlayer, VideoArticle };

export default {
  components,
  computed: mapState({ videoList, selectedVideo }),
  created() {
    this.$store.dispatch(VIDEO_POPULAR_FETCH);
  },
  methods: {
    selectVideo(video) {
      this.$store.dispatch(VIDEO_SELECT, video);
    },
  },
  destroyed() {
    this.$store.commit(VIDEO_SELECT, null);
  },
};
</script>
