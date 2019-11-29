<template>
  <section class="chart__video" v-if="video.selectedVideo !== null">
    <div class="video-player" ref="container">
      <Youtube :video-id="video.selectedVideo.videoId"
               :player-vars="options"
               width="100%"
               height="100%"
               ref="playerWrap" />
      <VideoControls />
    </div>
    <VideoList />
  </section>
</template>

<script>
import { mapState } from 'vuex';
import { Youtube } from 'vue-youtube';
import { VideoControls, VideoList } from './index';

export default {
  components: { Youtube, VideoControls, VideoList },
  computed: mapState(['video']),
  data() {
    return {
      options: {
        controls: 0,
        autoplay: 1,
      },
    };
  },
};
</script>
