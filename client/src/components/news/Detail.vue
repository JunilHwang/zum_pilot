<template>
  <article v-if="loaded" class="news-detail">
    <h1 class="news-detail__title" v-html="news.article.title" />
    <div ref="content" class="news-detail__content" v-html="removeEnter(news.article.content)" />
    <div class="btn-group center">
      <a href="#" class="btn main" @click.prevent="close">메인으로</a>
    </div>
  </article>
</template>

<script>
import { mapState } from 'vuex';
import { NEWS_CONTENT_FETCH, NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';

export default {
  computed: mapState(['news']),
  data() {
    return {
      loaded: false,
    };
  },
  created() {
    this.$store.dispatch(NEWS_CONTENT_FETCH, this.news.viewState);
    this.loaded = true;
  },
  methods: {
    close() {
      this.$store.commit(NEWS_CONTENT_VIEW, false);
    },
    removeEnter(content) {
      const reg1 = /\s*(<br>|&nbsp;)\s*/g;
      const reg2 = /\s*<span>\s*<\/span>\s*/g;
      const reg3 = /\s*<p>\s*<\/p>\s*/g;
      return content.replace(reg1, '').replace(reg2, '').replace(reg3, '');
    },
  },
};
</script>
