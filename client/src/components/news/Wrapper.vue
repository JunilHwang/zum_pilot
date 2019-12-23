<template>
  <component :is="type" v-bind="{ ...properties }" />
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { NewsHeadline, NewsPopular, NewsArticle } from './index';
import { NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';

const components = { NewsHeadline, NewsPopular, NewsArticle };
@Component({ components })
export default class Wrapper extends Vue {
  @Prop() type;
  @Prop() properties;
  viewDetail() { this.$store.commit(NEWS_CONTENT_VIEW, this.properties.link); }
}
</script>
