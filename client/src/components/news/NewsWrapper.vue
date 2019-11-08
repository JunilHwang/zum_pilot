<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
import Headline from './Headline.vue';
import Popular from './Popular.vue';
import Article from './Article.vue';

export default {
  components: {
    Headline, Popular, Article,
  },
  props: ['type', 'properties'],
};

</script>
