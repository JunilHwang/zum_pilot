<template>
  <main>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <template v-if="loaded">
      <VideoArticle
        v-for="(video, k) in videoList"
        :key="k"
        v-bind="video"
        :class="{ active: selectedVideo && selectedVideo.idx === video.idx }"
        @select="select(video)"
      />
      <div v-if="videoList.length === 0" class="videoArticleNone">
        인기 영상이 존재하지 않습니다.
      </div>
    </template>
    <Spinner v-else />
  </main>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State, Action, Mutation } from 'vuex-class';
import { VIDEO_POPULAR_FETCH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';
import { Spinner } from '@/components/common';

const components = { VideoPlayer, VideoArticle, Spinner };

@Component({ components })
export default class Popular extends Vue {
  @State(state => state.video.selectedVideo) selectedVideo;
  @State(state => state.video.videoList) videoList;
  @State(state => state.video.loaded) loaded;
  @Action(VIDEO_SELECT) select;
  @Action(VIDEO_POPULAR_FETCH) fetch;
  @Mutation(VIDEO_SELECT) mSelect;
  created() { this.fetch(); }
  destroyed() { this.mSelect(null); }
}
</script>
