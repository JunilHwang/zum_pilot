<template>
  <section class="chart__video">
    <strong v-if="music.selectedVideo === null">음원을 선택해주세요</strong>
    <div class="video-player" ref="container" v-else>
      <youtube :video-id="music.selectedVideo.videoId"
               :player-vars="options"
               width="100%"
               height="260"
               ref="youtube"
               @ready="ready" />
      <div class="controls" v-if="showControls">
        <div class="controls__left">
          <span class="controls__play-toggle">
            <FAI icon="pause" v-if="isPlay" @click="pause" />
            <FAI icon="play" v-else @click="play" />
          </span>
          <span class="controls__mute-toggle">
            <FAI icon="volume-down" size="lg" v-if="isMuted === false" @click="toggleMute" />
            <FAI icon="volume-mute" size="lg" v-else @click="toggleMute" />
          </span>
        </div>
        <div class="controls__right">
          <span class="controls__expand-toggle">
            <FAI icon="compress" @click="exitFullScreen" v-if="isFullScreen" />
            <FAI icon="expand" @click="inFullScreen" v-else />
          </span>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['music']),
    player() {
      return this.$refs.youtube.player;
    },
    isPlay() {
      return [1, 3].indexOf(this.state) !== -1;
    },
  },
  data() {
    return {
      options: {
        controls: 0,
        start: 0,
      },
      showControls: false,
      state: 0,
      isFullScreen: false,
      isMuted: false,
    };
  },
  methods: {
    ready() {
      this.showControls = true;
      this.player.on('stateChange', this.stateChange);
      document.removeEventListener('fullscreenchange', this.screenChange);
      document.addEventListener('fullscreenchange', this.screenChange);
    },
    play() {
      this.player.playVideo();
    },
    pause() {
      this.player.pauseVideo();
    },
    async stateChange({ data }) {
      if (data === -1) {
        this.isMuted = false;
        this.player.unMute();
      }
      this.state = await this.player.getPlayerState();
    },
    inFullScreen() {
      this.$refs.container.requestFullscreen();
    },
    exitFullScreen() {
      document.exitFullscreen();
    },
    screenChange() {
      this.isFullScreen = !this.isFullScreen;
    },
    async toggleMute() {
      this.player[this.isMuted ? 'unMute' : 'mute']();
      this.isMuted = !this.isMuted;
    },
  },
};
</script>
