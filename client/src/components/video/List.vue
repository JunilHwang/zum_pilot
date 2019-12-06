<template>
  <div class="video__wrap">
    <Flicking class="video__panels"
              @select="selectVideo"
              :options="{ gap: 2, circular: true, hanger: 0, anchor: 0, zIndex: 10 }">
      <div v-for="(v, k) in videoList"
           class="video__panel"
           :class="{ active: v.idx === selectedVideo.idx }"
           :key="k">
        <figure class="video__thumbnail">
          <img :src="v.thumbnail" :alt="v.title"  />
        </figure>
        <VideoMeta
          classPrefix="list-meta"
          :title="previewTitle(v.title, 50)"
        />
      </div>
    </Flicking>
  </div>
</template>

<script>
import { Flicking } from '@egjs/vue-flicking';
import { mapState } from 'vuex';
import { VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { previewTitle } from '@/helper';
import { VideoMeta } from './index';

const videoList = state => state.video.videoList;
const selectedVideo = state => state.video.selectedVideo;
const components = { Flicking, VideoMeta };

export default {
  components,
  computed: mapState({ videoList, selectedVideo }),
  methods: {
    previewTitle,
    selectVideo({ index }) {
      this.$store.dispatch(VIDEO_SELECT, this.videoList[index]);
    },
  },
};
</script>
