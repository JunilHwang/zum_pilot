<template>
  <article v-if="loaded" class="news-detail">
    <h1 class="news-detail__title" v-html="news.article.title" />
    <div ref="content" class="news-detail__content" v-html="news.article.content" />
    <div class="btn-group center">
      <a href="#" class="btn main" @click.prevent="close">메인으로</a>
    </div>
  </article>
</template>

<script>
import { mapState } from 'vuex';
import { FETCH_CONTENT, VIEW_CONTENT } from '@/store/news/const';

export default {
  computed: mapState(['news']),
  data() {
    return {
      loaded: false,
    };
  },
  created() {
    this.$store.dispatch(FETCH_CONTENT, this.news.viewState);
    this.loaded = true;
  },
  methods: {
    close() {
      this.$store.commit(VIEW_CONTENT, false);
    },
  },
};
</script>
