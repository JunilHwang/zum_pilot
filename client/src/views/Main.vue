<template>
  <main class="main-content">
    <section class="main-content__section news">
      <NewsWrapper
        v-if="newsHeadline !== null"
        type="Headline"
        :properties="newsHeadline"
      />
      <ul class="news-thumbs">
        <NewsWrapper
          v-for="(props, k) in newsPopulars"
          type="Popular"
          :properties="{ ...props }"
          :key="k"
        />
      </ul>
      <NewsWrapper
        v-for="(props, k) in newsArticles"
        type="Article"
        :properties="{ ...props }"
        :key="k"
      />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import {
  CHANGE_TRANSITION,
  FETCH_ARTICLES,
  FETCH_HEADLINE,
  FETCH_POPULAR,
} from '@/store/const';
import NewsWrapper from '@/components/news/NewsWrapper.vue';

export default {
  components: {
    NewsWrapper,
  },
  computed: mapState(['newsArticles', 'newsHeadline', 'newsPopulars']),
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
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>
