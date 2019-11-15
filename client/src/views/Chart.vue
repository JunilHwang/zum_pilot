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
import CharArticle from '@/components/chart/Article.vue';
import VideoPlayer from '@/components/chart/VideoPlayer.vue';
import { FETCH_MUSIC } from '@/store/music/const';

export default {
  components: {
    CharArticle,
    VideoPlayer,
  },
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
