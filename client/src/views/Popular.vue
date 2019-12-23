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
  @State(state => state.video.selectedVideo)
  selectedVideo;

  @State(state => state.video.videoList)
  videoList;

  /**
   * 인기 영상을 가져옴
   */
  created() {
    this.$store.dispatch(VIDEO_POPULAR_FETCH);
  }

  /**
   * 비디오 리스트 초기화
   */
  destroyed() {
    this.$store.commit(VIDEO_SELECT, null);
  }

  /**
   * 비디오 선택
   * @param video
   */
  selectVideo(video) {
    this.$store.dispatch(VIDEO_SELECT, video);
  }
}
</script>
