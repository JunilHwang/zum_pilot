<template>
  <main>
    <header class="chartCategory">
      <ul>
        <li v-for="(v, k) in categories" :key="k">
          <a href="#"
             @click.prevent="fetchMusic(k)"
             :class="{active: k === selectedCategory}"
             v-html="v" />
        </li>
      </ul>
    </header>
    <transition name="slide-down">
      <Flicking
        v-if="selectedVideo !== null"
        class="videoPanels"
        :options="{ gap: 10, hanger: 0, anchor: 0, zIndex: 10, defaultIndex: 1 }"
      >
        <div class="videoPanel">
          <VideoPlayer class="chartVideo" :propVideo="prevVideo" />
        </div>
        <div class="videoPanel">
          <VideoPlayer class="chartVideo" autoplay="1" />
        </div>
        <div class="videoPanel">
          <VideoPlayer class="chartVideo" :propVideo="nextVideo" />
        </div>
      </Flicking>
    </transition>
    <section class="chartWrap">
      <ChartArticle
        v-for="(v, k) in limit"
        :key="v"
        :class="{ active: isActive(k) }"
        v-bind="{ k, ...music.articles[k] }"
      />
    </section>
  </main>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Flicking } from '@egjs/vue-flicking';
import { MUSIC_FETCH, MUSIC_SELECT, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { ChartArticle } from '@/components/chart';
import { VideoPlayer } from '@/components/video';
import { eventBus } from '@/helper';

const components = { ChartArticle, VideoPlayer, Flicking };

@Component({ components })
export default class Chart extends Vue {
  @State music;
  @State(state => state.video.selectedVideo) selectedVideo;
  @State(state => state.video.prevVideo) prevVideo;
  @State(state => state.video.nextVideo) nextVideo;
  limit = 10;
  categories = ['실시간', '일간', '발라드', '댄스', '힙합', 'R&B/Soul'];
  selectedCategory = 0;

  // 음원 정보 가져오기
  created() {
    // 0: 실시간 카테고리
    this.fetchMusic(0);

    // App.vue에서 등록한 chartLoad의 emit을 감지. 즉 infinity load를 감지하여 실행함
    eventBus.$on('chartLoad', this.listLoading);
  }

  /**
   * 카테고리에 해당하는 음원 차트 목록을 가져옴
   * @param k => Categories index number
   */
  fetchMusic(k) {
    this.selectedCategory = k;
    this.$store.dispatch(MUSIC_FETCH, this.categories[k]);
  }

  // infinity loading. maximum 100개의 음원을 가져옴
  listLoading() {
    if (this.limit >= 100) return;
    this.limit += 10;
  }

  /**
   * 음원의 활성 상태 여부 판별
   * @param k => 음원의 index number
   * @returns {boolean}
   */
  isActive(k) {
    const { articles, selectedMusic } = this.music;
    if (selectedMusic === null) return false;
    const { title, artist } = articles[k];
    return selectedMusic === `${title}/${artist}`;
  }

  // 페이지 이동시 음원/비디오 활성 상태 해제
  destroyed() {
    this.$store.commit(MUSIC_SELECT, null);
    this.$store.commit(VIDEO_SELECT, null);
  }
}
</script>
