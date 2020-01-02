<template>
  <article class="chartArticle">
    <div class="chartArticleWrap" @click="$emit('select', k)">
      <strong class="chartRank" v-html="k + 1"/>
      <dl class="chartInfo">
        <dt v-html="title"/>
        <dd v-html="artist"/>
      </dl>
      <figure class="chartThumbnail">
        <img :src="img" :alt="title">
      </figure>
    </div>
  </article>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';

@Component
export default class Article extends Vue {
  // props 지정
  @Prop() k;
  @Prop() title;
  @Prop() album;
  @Prop() artist;
  @Prop() img;
}
</script>
