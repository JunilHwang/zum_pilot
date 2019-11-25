<template>
  <li>
    <a @click.prevent="viewDetail">
      <img :src="img" :alt="title">
      <h3 class="news-thumbs__title" v-html="previewTitle(title, 30)" />
    </a>
  </li>
</template>

<script>
import { VIEW_CONTENT } from '@/middleware/store/news/const';
import { previewTitle } from '@/helper';

export default {
  methods: {
    viewDetail() {
      this.$store.commit(VIEW_CONTENT, this.link);
    },
    previewTitle(title, limit) {
      return previewTitle(title, limit);
    },
  },
  props: ['title', 'img', 'link'],
};
</script>
