<template>
  <main>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <VideoArticle
      v-for="(video, k) in videoList"
      :key="k"
      v-bind="video"
      :class="{ active: selectedVideo && selectedVideo.idx === video.idx }"
      @select="selectVideo(video)"
    />
    <div v-if="videoList.length === 0" class="video-article__none">
      인기 영상이 존재하지 않습니다.
    </div>
  </main>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { VIDEO_POPULAR_FETCH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const components = { VideoPlayer, VideoArticle };

@Component({ components })
export default class Popular extends Vue {
  // state에서 변수 할당
  @State(state => state.video.selectedVideo) selectedVideo;
  @State(state => state.video.videoList) videoList;
  created() { this.$store.dispatch(VIDEO_POPULAR_FETCH); }
  destroyed() { this.$store.commit(VIDEO_SELECT, null); }
  selectVideo(video) { this.$store.dispatch(VIDEO_SELECT, video); }
}
</script>
