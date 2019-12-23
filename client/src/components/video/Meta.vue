<template>
  <ul :class="classPrefix">
    <li :class="metaTitle" v-html="title" />
    <li :class="metaBottom">
      <span
        v-if="bookmarkHide"
        :class="metaBookmark"
        @click="bookmarking"
      >
        <FAI icon="star" />
      </span>
      <span
        v-if="viewCount !== undefined"
        :class="metaViewCount"
      >
        <FAI icon="eye" /> {{ viewCount }}
      </span>
      <span
        v-if="likeCount !== undefined"
        :class="metaLike"
        @click.prevent="incrementLike"
      >
        <FAI icon="thumbs-up" /> {{ likeCount }}
      </span>
      <span
        v-if="popularPoint !== undefined && popularPointHide"
        class="popular-meta__point"
        v-html="`인기도 ${popularPoint}`"
      />
    </li>
  </ul>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Prop } from 'vue-property-decorator';
import { VIDEO_LIKE, VIDEO_BOOKMARK } from '@/middleware/store/mutations-type';

@Component
export default class Meta extends Vue {
  // State 변수 지정
  @State(state => state.user.token) token
  @State(state => state.user.bookmark) bookmark
  @State(state => state.video.selectedVideo.idx) selectedVideoIdx

  // Prop 변수 지정
  @Prop() classPrefix;
  @Prop() idx;
  @Prop() title;
  @Prop() userLiked;
  @Prop() viewCount;
  @Prop() likeCount;
  @Prop() hide;
  @Prop() popularPoint;

  // class name 을 위한 computed 변수 지정
  get metaTitle() { return `${this.classPrefix}__title`; }
  get metaBottom() { return `${this.classPrefix}__bottom`; }
  get metaViewCount() { return `${this.classPrefix}__view-count`; }

  // bookmark list 기반으로 active class toggle
  get metaBookmark() {
    const { classPrefix, bookmark: list, idx } = this;
    const active = list.find(v => v.idx === idx) ? 'active' : '';
    return `${classPrefix}__bookmark ${active}`;
  }

  // userLiked 기반으로 active class toggle
  get metaLike() {
    const { classPrefix, userLiked, token } = this;
    const active = token && userLiked ? 'active' : '';
    return `${classPrefix}__like ${active}`;
  }

  // hide property 기반으로 hiding
  get hideList() { return this.hide || []; }
  get bookmarkHide() { return this.hideList.indexOf('bookmark') === -1; }
  get popularPointHide() { return this.hideList.indexOf('popular') === -1; }

  // 좋아요 토글
  incrementLike() {
    if (this.idx === this.selectedVideoIdx) {
      this.$store.dispatch(VIDEO_LIKE, this.idx);
    }
  }

  // 즐겨찾기 토글
  bookmarking() {
    this.$store.dispatch(VIDEO_BOOKMARK, this.idx);
  }
}
</script>
