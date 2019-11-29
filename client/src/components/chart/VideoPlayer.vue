<template>
  <section class="chart__video">
    <strong v-if="music.selectedVideo === null">음원을 선택해주세요</strong>
    <div class="video-player" ref="container" v-else>
      <Youtube :video-id="music.selectedVideo.videoId"
               :player-vars="options"
               width="100%"
               height="260"
               ref="youtube"
               @ready="ready" />
      <VideoControls v-if="showControls" :player="player" />
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import { Youtube } from 'vue-youtube';
import { VideoControls } from './index';

export default {
  components: { Youtube, VideoControls },
  computed: {
    ...mapState(['music']),
    player() {
      return this.$refs.youtube.player;
    },
  },
  data() {
    return {
      options: {
        controls: 0,
        start: 0,
      },
      showControls: false,
    };
  },
  methods: {
    ready() {
      this.showControls = true;
    },
  },
};
</script>
