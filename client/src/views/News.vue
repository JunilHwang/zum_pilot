<template>
  <main>
    <section class="mainContentSection news">
      <Flicking
        class="newsPanels"
        :options="{ gap: 5, hanger: 0, anchor: 0, zIndex: 10 }"
        @select="viewDetail"
        v-if="news.populars.length">
        <div class="newsPanel" v-for="(props, k) in news.populars" :key="k">
          <NewsWrapper
            type="NewsHeadline"
            :properties="{ ...props }"
          />
        </div>
      </Flicking>
      <ClipLoader v-else color="#09F" class="spinner" />
      <NewsWrapper
        v-for="(props, k) in news.articles"
        type="NewsArticle"
        :properties="{ ...props }"
        :key="k"
      />
      <ClipLoader v-if="news.articles.length === 0" color="#09F" class="spinner" />
    </section>
    <transition name="page-up">
      <NewsDetail v-if="news.viewState !== false" />
    </transition>
  </main>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Flicking } from '@egjs/vue-flicking';
import ClipLoader from 'vue-spinner/src/ClipLoader.vue';
import { NEWS_POPULAR_FETCH, NEWS_ARTICLES_FETCH } from '@/middleware/store/mutations-type';
import { NewsWrapper, NewsDetail } from '@/components/news';
import { eventBus } from '@/helper';

const components = { NewsWrapper, NewsDetail, Flicking, ClipLoader };

@Component({ components })
export default class News extends Vue {
  @State news;
  page = 1; // infinity load 횟수

  // Server에서 Crawling한 News 가져오기
  created() {
    this.$store.dispatch(NEWS_POPULAR_FETCH);
    this.$store.dispatch(NEWS_ARTICLES_FETCH);

    // App.vue에서 보내는 newsLoad emit을 감지하여 loading
    // 즉, infinite load를 감지하여 이벤트를 실행한다.
    eventBus.$on('newsLoad', this.listLoading);
  }

  // Mounted 시점에 스크롤을 최상단으로 보낸다. Vue Route로 페이지 변경시 자연스럽게 보이기 위함
  mounted = () => window.scrollTo(0, 0);

  /**
   * Popular News를 select 할 때 발생
   * @param index => panel index number
   * @param currentTarget => 선택한 panel
   */
  viewDetail = ({ index, currentTarget }) => { currentTarget.$children[index].viewDetail(); }

  // infinity load를 통해 최신 뉴스 5 페이지 까지 불러온다.
  listLoading() {
    if (this.page >= 5) return;
    this.page += 1;
    this.$store.dispatch(NEWS_ARTICLES_FETCH, this.page);
  }
}
</script>
