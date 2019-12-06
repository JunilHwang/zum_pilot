<template>
  <div class="controls" :class="{ active: controlsActive }" v-if="showControls">
    <div class="controls__progress">
      <div class="bg" />
      <div class="fill"
           :style="{width: `${(current / duration) * 100}%`}" />
      <input type="range"
             v-model="current"
             step="0.1"
             :max="duration"
             @input="timeUpdate" />
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
</template>

<script>
import { VIDEO_VIEW } from '@/middleware/store/mutations-type';

export default {
  computed: {
    isPlay() {
      return [1, 3].indexOf(this.state) !== -1;
    },
    player() {
      return this.$parent.$refs.playerWrap.player;
    },
  },
  created() {
    this.$parent.$refs.playerWrap.$on('ready', this.ready);
  },
  data() {
    return {
      state: 0,
      isFullScreen: false,
      isMuted: false,
      duration: 100,
      current: 0,
      timer: null,
      controlsActive: true,
      showControls: false,
    };
  },
  methods: {
    ready() {
      this.showControls = true;
      document.removeEventListener('fullscreenchange', this.screenChange);
      document.addEventListener('fullscreenchange', this.screenChange);
      this.player.on('stateChange', this.stateChange);
    },
    clear() {
      clearTimeout(this.timer);
    },
    videoInit() {
      this.isMuted = false;
      this.current = 0;
      this.duration = 100;
      this.timer = null;
      this.player.unMute();
    },
    async stateChange({ data }) {
      this.controlsActive = true;
      switch (data) {
        case -1:
          this.videoInit();
          break;
        case 1:
          this.controlsActive = false;
          this.timeCheck();
          break;
        case 0:
          await this.$store.dispatch(VIDEO_VIEW);
          break;
        default:
          this.clear();
          break;
      }
      this.state = await this.player.getPlayerState();
    },
    inFullScreen() {
      this.$parent.$refs.container.requestFullscreen();
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
      this.timer = setInterval(async () => {
        this.current = await this.player.getCurrentTime();
      }, 100);
    },
    timeUpdate({ target }) {
      this.player.seekTo(target.value);
    },
  },
};
</script>