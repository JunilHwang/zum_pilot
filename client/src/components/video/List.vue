<template>
  <div class="videoWrap">
    <Flicking class="videoPanels"
              @select="selectVideo"
              :options="{ gap: 2, circular: true, hanger: 0, anchor: 0, zIndex: 10 }">
      <div v-for="(v, k) in videoList"
           class="videoPanel"
           :class="{ active: v.idx === selectedVideo.idx }"
           :key="k">
        <figure class="videoThumbnail">
          <img :src="v.thumbnail" :alt="v.title"  />
        </figure>
        <VideoMeta
          classPrefix="listMeta"
          :idx="v.idx"
          :title="previewTitle(v.title, 50)"
        />
      </div>
    </Flicking>
  </div>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Flicking } from '@egjs/vue-flicking';
import { VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { previewTitle } from '@/helper';
import { VideoMeta } from './index';

const components = { Flicking, VideoMeta };

@Component({ components })
export default class List extends Vue {
  // State 변수 지정
  @State(state => state.video.videoList) videoList;
  @State(state => state.video.selectedVideo) selectedVideo;

  // helper에서 function 가져오기
  previewTitle = previewTitle;

  // 동영상 선택
  selectVideo({ index }) {
    this.$store.dispatch(VIDEO_SELECT, this.videoList[index]);
  }
}
</script>
