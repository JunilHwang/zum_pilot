<template>
  <section class="chart__video">
    <strong v-if="music.selectedVideo === null">음원을 선택해주세요</strong>
    <div class="video-player" ref="container" v-else>
      <Youtube :video-id="music.selectedVideo.videoId"
               :player-vars="options"
               width="100%"
               height="260"
               ref="playerWrap" />
      <VideoControls :playerWrap="$refs.playerWrap" :container="$refs.container" />
    </div>

  </section>
</template>

<script>
import { mapState } from 'vuex';
import { Youtube } from 'vue-youtube';
import { VideoControls } from './index';

export default {
  components: { Youtube, VideoControls },
  computed: mapState(['music']),
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
