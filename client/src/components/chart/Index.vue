<template>
  <main class="chart">
    <VideoPlayer />
    <section class="chart__wrap">
      <ChartArticle v-for="(v, k) in len" :key="v" v-bind="{ k, ...music.articles[k] }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_MUSIC } from '@/store/music/const';
import { ChartArticle, VideoPlayer } from '@/components/chart';

export default {
  components: { ChartArticle, VideoPlayer },
  computed: mapState(['music']),
  data() {
    return {
      len: 10,
    };
  },
  created() {
    this.$store.dispatch(FETCH_MUSIC);
  },
  methods: {
    listLoading() {
      this.len += 10;
      if (this.len >= 100) {
        window.removeEventListener('scroll', this.listLoading);
      }
    },
  },
};
</script>
