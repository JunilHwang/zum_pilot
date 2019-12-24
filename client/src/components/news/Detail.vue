<template>
  <article v-if="loaded" class="newsDetail">
    <h1 class="newsDetailTitle" v-html="news.article.title" />
    <div ref="content" class="newsDetailContent" v-html="removeEnter(news.article.content)" />
    <div class="btnGroup center">
      <a href="#" class="btn main" @click.prevent="close">메인으로</a>
    </div>
  </article>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { NEWS_CONTENT_FETCH, NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';

const reg1 = /\s*(<br>|&nbsp;)\s*/g;
const reg2 = /\s*<span>\s*<\/span>\s*/g;
const reg3 = /\s*<p>\s*<\/p>\s*/g;

@Component
export default class Detail extends Vue {
  @State news;
  loaded = false;

  // content 가져오기
  created() {
    this.$store.dispatch(NEWS_CONTENT_FETCH, this.news.viewState);
    this.loaded = true;
  }

  // 닫기
  close() { this.$store.commit(NEWS_CONTENT_VIEW, false); }

  // 무의미한 공백, 줄바꿈 제거
  removeEnter = content => content.replace(reg1, '').replace(reg2, '').replace(reg3, '');
}
</script>
