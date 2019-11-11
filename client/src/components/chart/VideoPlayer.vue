<template>
  <section class="chart__video">
    <video ref="videoPlayer" class="video-js" height="200"></video>
  </section>
</template>

<script>
import videojs from 'video.js';

export default {
  data() {
    return {
      player: null,
    };
  },
  mounted() {
    this.player = videojs(this.$refs.videoPlayer, this.options, function onPlayerReady() {
      console.log('onPlayerReady', this);
    });
  },
  beforeDestroy() {
    if (this.player) {
      this.player.dispose();
    }
  },
};

</script>
