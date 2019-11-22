<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
import { NewsHeadline, NewsPopular, NewsArticle } from './index';

export default {
  components: { NewsHeadline, NewsPopular, NewsArticle },
  props: ['type', 'properties'],
};
</script>
