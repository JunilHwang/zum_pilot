<template>
  <main>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <VideoArticle
      v-for="(video, k) in videoList"
      :key="k"
      :thumbnail="video.thumbnail"
      :idx="video.idx"
      :title="video.title"
      :id="video.id"
      :class="{ active: selectedVideo && selectedVideo.idx === video.idx }"
      @select="selectVideo(video)"
    />
    <div v-if="videoList.length === 0" class="video-article__none">
      즐겨찾기한 영상이 없습니다.
    </div>
  </main>
</template>
<script>
import Vue from 'vue';
import { State } from 'vuex-class';
import Component from 'vue-class-component';
import { Watch } from 'vue-property-decorator';
import { MODAL_ALERT, USER_AUTH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const components = { VideoPlayer, VideoArticle };
const errorMessage = '로그인 후 이용해주세요';

@Component({ components })
export default class Bookmark extends Vue {
  @State(state => state.user.bookmark) videoList;
  @State(state => state.video.selectedVideo) selectedVideo;
  @State(state => state.user.token) token;

  // token 변경 감지. 즉, login/logout 상태를 감지함
  @Watch('token')
  onTokenChange() {
    if (this.token === null) {
      this.$store.commit(MODAL_ALERT, errorMessage);
      this.$router.push('/sign-in');
    }
  }

  created() { this.$store.dispatch(USER_AUTH); } // 사용자 인증을 통과해야 됨.
  destroyed() { this.$store.commit(VIDEO_SELECT, null); } // 페이지를 벗어나면 비디오 선택 해제
  selectVideo(video) { this.$store.dispatch(VIDEO_SELECT, video); } // 비디오 선택
}
</script>
