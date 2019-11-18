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
        <div class="controls__progress">
          <div class="bg" />
          <div class="fill"
               :style="{width: `${(current / duration) * 100}%`}" />
          <input type="range"
                 v-model="current"
                 step="0.1"
                 :max="duration"
                 @change="timeUpdated"
                 @input="timeUpdating" />
        </div>
        <div class="controls__left">
          <span class="controls__play-toggle">
            <FAI icon="pause" v-if="isPlay" @click="player.pauseVideo" />
            <FAI icon="play" v-else @click=" player.playVideo" />
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
import bus from '@/eventBus';

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
  created() {
    bus.$on('selectVideo', this.selectVideo);
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
      duration: 100,
      current: 0,
      timer: null,
    };
  },
  methods: {
    ready() {
      this.showControls = true;
      this.player.on('stateChange', this.stateChange);
      document.removeEventListener('fullscreenchange', this.screenChange);
      document.addEventListener('fullscreenchange', this.screenChange);
    },
    clear() {
      clearTimeout(this.timer);
    },
    videoInit() {
      this.isMuted = false;
      this.player.unMute();
      this.current = 0;
      this.duration = 100;
      this.timer = null;
    },
    async stateChange({ data }) {
      console.log(data);
      if (data === -1) {
        this.videoInit();
      } else if (data === 1) {
        this.timeCheck();
      } else {
        this.clear();
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
    async timeCheck() {
      this.clear();
      this.duration = await this.player.getDuration();
      this.timer = setInterval(() => {
        this.current = (this.current + 0.1) % this.duration;
      }, 100);
    },
    timeUpdating({ target }) {
      this.player.seekTo(target.value);
    },
    async timeUpdated() {
      this.current = await this.player.getCurrentTime();
      this.timeCheck();
    },
  },
};
</script>
