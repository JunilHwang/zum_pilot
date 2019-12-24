<template>
  <div class="controls" :class="{ active: controlsActive }" v-if="showControls">
    <div class="controlsProgress">
      <div class="bg" />
      <div class="fill"
           :style="{width: `${(current / duration) * 100}%`}" />
      <input type="range"
             v-model="current"
             step="0.1"
             :max="duration"
             @input="timeUpdate" />
    </div>
    <div class="controlsLeft">
      <span class="controlsPlayToggle">
        <FAI icon="pause" v-if="isPlay" @click="player.pauseVideo" />
        <FAI icon="play" v-else @click=" player.playVideo" />
      </span>
      <span class="controlsMuteToggle">
        <FAI icon="volume-down" size="lg" v-if="isMuted === false" @click="toggleMute" />
        <FAI icon="volume-mute" size="lg" v-else @click="toggleMute" />
      </span>
    </div>
    <div class="controlsRight">
      <span class="controlsExpandToggle">
        <FAI icon="compress" @click="exitFullScreen" v-if="isFullScreen" />
        <FAI icon="expand" @click="inFullScreen" v-else />
      </span>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { VIDEO_VIEW } from '@/middleware/store/mutations-type';

@Component
export default class Controls extends Vue {
  // 기본 변수 정의
  state = 0;
  isFullScreen = false;
  isMuted = false;
  duration = 100;
  current = 0;
  timer = null;
  controlsActive = true;
  showControls = false;

  // computed 변수. play 상태에 대해 나타냄
  get isPlay() { return [1, 3].indexOf(this.state) !== -1; }
  get player() { return this.$parent.$refs.playerWrap.player; }

  // controls가 만들어지는 순간 ready 상태로 변경
  created() { this.$parent.$refs.playerWrap.$on('ready', this.ready); }

  // 창모드/전체모드 감지 및 state 변경 감지
  ready() {
    this.showControls = true;
    document.removeEventListener('fullscreenchange', this.screenChange);
    document.addEventListener('fullscreenchange', this.screenChange);
    this.player.on('stateChange', this.stateChange);
  }

  // timer 해제 ( seek 변경 감지 해제 )
  clear() { clearTimeout(this.timer); }

  // 동영상 선택시 초기화
  videoInit() {
    this.isMuted = false;
    this.current = 0;
    this.duration = 100;
    this.timer = null;
    this.player.unMute();
  }

  // state 변경 감지 후, 별개 처리
  // https://developers.google.com/youtube/iframe_api_reference#Playback_status
  async stateChange({ data }) {
    this.controlsActive = true;
    switch (data) {
      // unstarted
      case -1: this.videoInit(); break;

      // ended
      case 0: await this.$store.dispatch(VIDEO_VIEW); break;

      // playing
      case 1: this.controlsActive = false; this.timeCheck(); break;

      // 나머지 상태들
      default: this.clear(); break;
    }

    // state 받아오기
    this.state = await this.player.getPlayerState();
  }

  // 전체화면
  inFullScreen() { this.$parent.$refs.container.requestFullscreen(); }

  // 전체화면 해제
  exitFullScreen = () => document.exitFullscreen();

  // 전체화면 상태 변경 감지
  screenChange() { this.isFullScreen = !this.isFullScreen; }

  // 음소거 토글
  async toggleMute() {
    this.player[this.isMuted ? 'unMute' : 'mute']();
    this.isMuted = !this.isMuted;
  }

  // 0.1 간격으로 seek time 동기화
  async timeCheck() {
    this.clear();
    this.duration = await this.player.getDuration();
    this.timer = setInterval(async () => {
      this.current = await this.player.getCurrentTime();
    }, 100);
  }

  // controls 를 통한 seek time 동기화
  timeUpdate({ target }) { this.player.seekTo(target.value); }
}
</script>
