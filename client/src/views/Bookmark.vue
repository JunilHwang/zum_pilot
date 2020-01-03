<template>
  <main>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <template>
      <VideoArticle
        v-for="(video, k) in videoList"
        :key="k"
        :thumbnail="video.thumbnail"
        :idx="video.idx"
        :title="video.title"
        :id="video.id"
        :class="{ active: selectedVideo && selectedVideo.idx === video.idx }"
        @select="select(video)"
      />
      <div v-if="videoList.length === 0" class="videoArticleNone">
        즐겨찾기한 영상이 없습니다.
      </div>
    </template>
  </main>
</template>
<script>
import Vue from 'vue';
import { State, Mutation, Action } from 'vuex-class';
import Component from 'vue-class-component';
import { Watch } from 'vue-property-decorator';
import { MODAL_ALERT, USER_AUTH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const components = { VideoPlayer, VideoArticle };
const errorMessage = '로그인 후 이용해주세요';

@Component({ components })
export default class Bookmark extends Vue {
  @State(state => state.user.bookmark) videoList;
  @State(state => state.user.token) token;
  @State(state => state.video.selectedVideo) selectedVideo;
  @State(state => state.video.loaded) loaded;
  @Action(USER_AUTH) auth;
  @Action(VIDEO_SELECT) select;
  @Mutation(VIDEO_SELECT) mSelect;
  @Mutation(MODAL_ALERT) alert;

  /**
   * token 변경 감지. 즉, login/logout 상태를 감지함
   */
  @Watch('token')
  onTokenChange() {
    if (this.token === null) {
      this.alert(errorMessage);
      this.$router.push('/sign-in');
    }
  }

  /**
   * 사용자 인증을 통과해야 됨.
   */
  created() { this.auth(); }

  /**
   * 페이지를 벗어나면 비디오 선택 해제
   */
  destroyed() { this.mSelect(null); }
}
</script>
