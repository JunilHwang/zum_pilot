<template>
  <main class="news-detail">
    <article v-if="loaded">
      <h1 class="news-detail__title" v-html="newsArticle.title" />
      <div ref="content" class="news-detail__content" v-html="newsArticle.content" />
      <div class="btn-group center">
        <router-link to="/" class="btn main">메인으로</router-link>
        <a href="#" class="btn default">상단으로</a>
      </div>
    </article>
  </main>
</template>

<script>
import { mapState } from 'vuex';
import { FETCH_CONTENT } from '@/store/const';

export default {
  beforeRouteLeave(to, from, next) {
    this.$store.commit('changeRouterTransition', '');
    next();
  },
  computed: mapState(['newsArticle']),
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
