<template>
  <main>
    <VideoPlayer />
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { VIDEO_POPULAR_FETCH } from '@/middleware/store/mutations-type';
import { VideoPlayer } from '@/components/video';

const videoList = state => state.video.videoList;
const components = { VideoPlayer };

export default {
  components,
  computed: mapState({ videoList }),
  created() {
    this.$store.dispatch(VIDEO_POPULAR_FETCH);
  },
};
</script>
