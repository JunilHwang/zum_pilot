<template>
  <main class="main-content">
    <VideoPlayer />
    <section class="chart__wrap">
      <ChartArticle v-for="(v, k) in limit" :key="v" v-bind="{ k, ...music.articles[k] }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_MUSIC } from '@/middleware/store/music/const';
import { ChartArticle, VideoPlayer } from '@/components/chart';
import { eventBus } from '@/helper';

export default {
  components: { ChartArticle, VideoPlayer },
  computed: mapState(['music']),
  data() {
    return {
      limit: 10,
    };
  },
  created() {
    this.$store.dispatch(FETCH_MUSIC);
    eventBus.$on('chartLoad', this.listLoading);
  },
  methods: {
    listLoading() {
      if (this.limit >= 100) return;
      this.limit += 10;
    },
  },
};
</script>
