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
import { mapState } from 'vuex';
import { MODAL_ALERT, USER_AUTH, VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { VideoPlayer, VideoArticle } from '@/components/video';

const videoList = state => state.user.bookmark;
const token = state => state.user.token;
const selectedVideo = state => state.video.selectedVideo;
const components = { VideoPlayer, VideoArticle };
const errorMessage = '로그인 후 이용해주세요';

export default {
  components,
  computed: mapState({ videoList, selectedVideo, token }),
  created() {
    this.$store.dispatch(USER_AUTH);
  },
  methods: {
    selectVideo(video) {
      this.$store.dispatch(VIDEO_SELECT, video);
    },
  },
  watch: {
    token() {
      if (this.token === null) {
        this.$store.commit(MODAL_ALERT, errorMessage);
        this.$router.push('/sign-in');
      }
    },
  },
  destroyed() {
    this.$store.commit(VIDEO_SELECT, null);
  },
};
</script>
