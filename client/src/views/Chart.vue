<template>
  <main class="chart">
    <VideoPlayer />
    <section class="chart__wrap">
      <CharArticle v-for="(v, k) in len" :key="v" v-bind="{ k, ...music.articles[k] }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_MUSIC } from '@/store/music/const';

const CharArticle = () => import('@/components/chart/Article.vue');
const VideoPlayer = () => import('@/components/chart/VideoPlayer.vue');

export default {
  components: { CharArticle, VideoPlayer },
  computed: mapState(['music']),
  data() {
    return {
      len: 10,
    };
  },
  created() {
    this.$store.dispatch(FETCH_MUSIC);
    window.removeEventListener('scroll', this.listLoading);
    window.addEventListener('scroll', this.listLoading);
  },
  methods: {
    listLoading() {
      this.helper.windowBottomSensor(() => {
        this.len += 10;
        if (this.len >= 100) {
          window.removeEventListener('scroll', this.listLoading);
        }
      });
    },
  },
};
</script>
