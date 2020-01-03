<template>
  <article v-if="loaded" class="newsDetail">
    <template v-if="article.title !== null">
      <h1 class="newsDetailTitle" v-html="article.title" />
      <div ref="content" class="newsDetailContent" v-html="removeEnter(article.content)" />
    </template>
    <Spinner v-else />
    <div class="btnGroup center">
      <a href="#" class="btn main" @click.prevent="close">메인으로</a>
    </div>
  </article>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State, Mutation, Action } from 'vuex-class';
import { Spinner } from '@/components/common';
import { NEWS_CONTENT_FETCH, NEWS_CONTENT_VIEW } from '@/middleware/store/mutations-type';

const reg1 = /\s*(<br>|&nbsp;)\s*/g;
const reg2 = /\s*<span>\s*<\/span>\s*/g;
const reg3 = /\s*<p>\s*<\/p>\s*/g;
const components = { Spinner };

@Component({ components })
export default class Detail extends Vue {
  @State(state => state.news.article) article;
  @State(state => state.news.viewState) viewState;
  @Action(NEWS_CONTENT_FETCH) actionFetch;
  @Mutation(NEWS_CONTENT_FETCH) mutationFetch;
  @Mutation(NEWS_CONTENT_VIEW) contentView;
  loaded = false;

  // content 가져오기
  created() {
    this.actionFetch(this.viewState);
    this.loaded = true;
  }

  // 닫기
  close() {
    this.mutationFetch({ title: null, content: null });
    this.contentView(false);
  }

  // 무의미한 공백, 줄바꿈 제거
  removeEnter = content => content.replace(reg1, '').replace(reg2, '').replace(reg3, '');
}
</script>
