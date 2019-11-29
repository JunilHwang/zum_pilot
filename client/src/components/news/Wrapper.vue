<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
import { NewsHeadline, NewsPopular, NewsArticle } from './index';
import { VIEW_CONTENT } from '@/middleware/store/news/const';

export default {
  components: { NewsHeadline, NewsPopular, NewsArticle },
  methods: {
    viewDetail() {
      this.$store.commit(VIEW_CONTENT, this.properties.link);
    },
  },
  props: ['type', 'properties'],
};
</script>
