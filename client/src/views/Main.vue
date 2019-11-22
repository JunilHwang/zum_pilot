<template>
  <main class="main-content">
    <section class="main-content__section news">
      <NewsWrapper
        v-if="news.headline !== null"
        type="NewsHeadline"
        :properties="news.headline"
      />
      <ul class="news-thumbs">
        <NewsWrapper
          v-for="(props, k) in news.populars"
          type="NewsPopular"
          :properties="{ ...props }"
          :key="k"
        />
      </ul>
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
import { FETCH_HEADLINE, FETCH_POPULAR, FETCH_ARTICLES } from '@/store/news/const';
import { NewsWrapper, NewsDetail } from '@/components/news';

export default {
  components: { NewsWrapper, NewsDetail },
  computed: mapState(['news']),
  data() {
    return {
      page: 1,
    };
  },
  created() {
    this.$store.dispatch(FETCH_HEADLINE);
    this.$store.dispatch(FETCH_POPULAR);
    this.$store.dispatch(FETCH_ARTICLES);
    window.removeEventListener('scroll', this.listLoading);
    window.addEventListener('scroll', this.listLoading);
  },
  methods: {
    listLoading() {
      this.helper.windowBottomSensor(() => {
        this.page += 1;
        this.$store.dispatch(FETCH_ARTICLES, this.page);
        if (this.page >= 5) {
          window.removeEventListener('scroll', this.listLoading);
        }
      });
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>
