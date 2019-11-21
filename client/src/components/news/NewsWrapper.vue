<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
const Headline = () => import('./Headline.vue');
const Popular = () => import('./Popular.vue');
const Article = () => import('./Article.vue');

export default {
  components: { Headline, Popular, Article },
  props: ['type', 'properties'],
};
</script>
