<template>
  <section v-if="video !== null">
    <div class="videoPlayer" ref="container">
      <Youtube
        :video-id="video.videoId"
        :player-vars="options"
        width="100%"
        height="100%"
        ref="playerWrap" />
      <VideoControls />
    </div>
    <VideoMeta classPrefix="videoMeta" v-bind="video" :hide="['popular']" />
    <slot />
  </section>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { State } from 'vuex-class';
import { Youtube } from 'vue-youtube';
import { VideoControls, VideoMeta } from './index';

const components = { Youtube, VideoControls, VideoMeta };

@Component({ components })
export default class Player extends Vue {
  @State(state => state.video.selectedVideo) selectedVideo;
  @Prop() propVideo;
  options = { controls: 0, autoplay: 1 }

  // property로 넘긴 video 정보가 있다면 해당 정보를 사용하고, 없다면 selectedVideo를 사용
  get video() {
    return this.propVideo || this.selectedVideo;
  }
}
</script>
