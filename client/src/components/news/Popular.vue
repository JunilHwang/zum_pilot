<template>
  <li>
    <a @click.prevent="viewDetail">
      <img :src="img" :alt="title">
      <h3 class="newsThumbsTitle" v-html="previewTitle(title, 30)" />
    </a>
  </li>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';
import { previewTitle } from '@/helper';

@Component
export default class Popular extends Vue {
  @Prop() title;
  @Prop() img;
  @Prop() link;
  previewTitle = previewTitle;
  viewDetail() { this.$store.commit(NEWS_CONTENT_VIEW, this.link); }
}
</script>
