<template>
  <main class="main-content">
    <section class="main-content__section news">
      <NewsWrapper
        v-if="news.headline !== null"
        type="Headline"
        :properties="news.headline"
      />
      <ul class="news-thumbs">
        <NewsWrapper
          v-for="(props, k) in news.populars"
          type="Popular"
          :properties="{ ...props }"
          :key="k"
        />
      </ul>
      <NewsWrapper
        v-for="(props, k) in news.articles"
        type="Article"
        :properties="{ ...props }"
        :key="k"
      />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { CHANGE_TRANSITION } from '@/store/const';
import {
  FETCH_HEADLINE,
  FETCH_POPULAR,
  FETCH_ARTICLES,
} from '@/store/news/const';
import NewsWrapper from '@/components/news/NewsWrapper.vue';

export default {
  components: {
    NewsWrapper,
  },
  computed: mapState(['news']),
  data() {
    return {
      page: 1,
    };
  },
  beforeRouteLeave(to, from, next) {
    if (to.path.indexOf('/news') !== -1) {
      this.$store.commit(CHANGE_TRANSITION, 'slide-left');
    }
    next();
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
