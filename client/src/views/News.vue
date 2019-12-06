<template>
  <main>
    <section class="main-content__section news">
      <Flicking
        class="news__panels"
        :options="{ gap: 5, hanger: 0, anchor: 0, zIndex: 10 }"
        @select="viewDetail"
      >
        <div class="news__panel">
          <NewsWrapper
            v-if="news.headline !== null"
            type="NewsHeadline"
            :properties="news.headline"
          />
        </div>
        <div class="news__panel" v-for="(props, k) in news.populars" :key="k">
          <NewsWrapper
            type="NewsHeadline"
            :properties="{ ...props }"
          />
        </div>
      </Flicking>
      <NewsWrapper
        v-for="(props, k) in news.articles"
        type="NewsArticle"
        :properties="{ ...props }"
        :key="k"
      />
    </section>
    <transition name="page-up">
      <NewsDetail v-if="news.viewState !== false" />
    </transition>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { Flicking } from '@egjs/vue-flicking';
import { NEWS_HEADLINE_FETCH, NEWS_POPULAR_FETCH, NEWS_ARTICLES_FETCH } from '@/middleware/store/mutations-type';
import { NewsWrapper, NewsDetail } from '@/components/news';
import { eventBus } from '@/helper';

export default {
  components: { NewsWrapper, NewsDetail, Flicking },
  computed: mapState(['news']),
  data() {
    return {
      page: 1,
    };
  },
  created() {
    this.$store.dispatch(NEWS_HEADLINE_FETCH);
    this.$store.dispatch(NEWS_POPULAR_FETCH);
    this.$store.dispatch(NEWS_ARTICLES_FETCH);
    eventBus.$on('newsLoad', this.listLoading);
  },
  methods: {
    viewDetail({ index, currentTarget }) {
      currentTarget.$children[index].viewDetail();
    },
    listLoading() {
      if (this.page >= 5) return;
      this.page += 1;
      this.$store.dispatch(NEWS_ARTICLES_FETCH, this.page);
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>
