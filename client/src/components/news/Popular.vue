<template>
  <li>
    <a @click.prevent="viewDetail">
      <img :src="img" :alt="title">
      <h3 class="news-thumbs__title" v-html="helper.previewTitle(title, 30)" />
    </a>
  </li>
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
