<template>
  <main class="news-detail">
    <article v-if="loaded">
      <h1 class="news-detail__title" v-html="news.article.title" />
      <div ref="content" class="news-detail__content" v-html="news.article.content" />
      <div class="btn-group center">
        <router-link to="/" class="btn main">메인으로</router-link>
        <a href="#" class="btn default">상단으로</a>
      </div>
    </article>
  </main>
</template>

<script>
import { mapState } from 'vuex';
import { FETCH_CONTENT } from '@/store/news/const';

export default {
  beforeRouteLeave(to, from, next) {
    this.$store.commit('changeRouterTransition', '');
    next();
  },
  computed: mapState(['news']),
  data() {
    return {
      loaded: false,
    };
  },
  created() {
    this.$store.dispatch(FETCH_CONTENT, this.$route.params.url);
    this.loaded = true;
  },
};
</script>
