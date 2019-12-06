<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
import { NewsHeadline, NewsPopular, NewsArticle } from './index';
import { NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';

export default {
  components: { NewsHeadline, NewsPopular, NewsArticle },
  methods: {
    viewDetail() {
      this.$store.commit(NEWS_CONTENT_VIEW, this.properties.link);
    },
  },
  props: ['type', 'properties'],
};
</script>
