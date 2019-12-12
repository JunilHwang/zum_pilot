<template>
  <section v-if="video !== null">
    <div class="video-player" ref="container">
      <Youtube :video-id="video.videoId"
               :player-vars="options"
               width="100%"
               height="100%"
               ref="playerWrap" />
      <VideoControls />
    </div>
    <VideoMeta classPrefix="video-meta" v-bind="video" :hide="['popular']" />
    <slot></slot>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import { Youtube } from 'vue-youtube';
import { VideoControls, VideoMeta } from './index';

const video = state => state.video.selectedVideo;
const components = { Youtube, VideoControls, VideoMeta };

export default {
  components,
  computed: mapState({ video }),
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
