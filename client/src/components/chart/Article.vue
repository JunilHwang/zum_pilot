<template>
  <article class="chartArticle">
    <div class="chartArticle-wrap" @click="searchVideo">
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
import { VIDEO_FETCH, MUSIC_SELECT } from '@/middleware/store/mutations-type';

@Component
export default class Article extends Vue {
  // props 지정
  @Prop() k;
  @Prop() title;
  @Prop() album;
  @Prop() artist;
  @Prop() img;

  // 음원 선택 시 Youtube에서 동영상 검색
  searchVideo() {
    const { title, artist } = this;
    this.$store.dispatch(VIDEO_FETCH, title);
    this.$store.commit(MUSIC_SELECT, `${title}/${artist}`);
  }
}
</script>
