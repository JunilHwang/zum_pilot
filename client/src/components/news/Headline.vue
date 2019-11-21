<template>
  <article class="news-main">
    <a @click.prevent="viewDetail">
      <figure class="news-main__img">
        <img :src="img" :alt="title">
      </figure>
      <h3 class="news-main__title" v-html="title" />
    </a>
  </article>
</template>

<script>
import { VIEW_CONTENT } from '@/store/news/const';

export default {
  methods: {
    viewDetail() {
      this.$store.commit(VIEW_CONTENT, this.link);
    },
  },
  props: ['title', 'img', 'link'],
};
</script>
