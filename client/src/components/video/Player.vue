<template>
  <section v-if="video !== null">
    <div class="video-player" ref="container">
      <Youtube
        :video-id="video.videoId"
        :player-vars="options"
        width="100%"
        height="100%"
        ref="playerWrap" />
      <VideoControls />
    </div>
    <VideoMeta classPrefix="video-meta" v-bind="video" :hide="['popular']" />
    <slot />
  </section>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Youtube } from 'vue-youtube';
import { VideoControls, VideoMeta } from './index';

const components = { Youtube, VideoControls, VideoMeta };

@Component({ components })
export default class Player extends Vue {
  @State(state => state.video.selectedVideo) video;
  options = { controls: 0, autoplay: 1 }
}
</script>
