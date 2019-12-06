<template>
  <li>
    <a @click.prevent="viewDetail">
      <img :src="img" :alt="title">
      <h3 class="news-thumbs__title" v-html="previewTitle(title, 30)" />
    </a>
  </li>
</template>

<script>
import { NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';
import { previewTitle } from '@/helper';

export default {
  methods: {
    viewDetail() {
      this.$store.commit(NEWS_CONTENT_VIEW, this.link);
    },
    previewTitle(title, limit) {
      return previewTitle(title, limit);
    },
  },
  props: ['title', 'img', 'link'],
};
</script>
