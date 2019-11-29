<template>
  <main>
    <VideoPlayer />
    <section class="chart__wrap">
      <header>
        <ul class="chart__category">
          <li v-for="(v, k) in categories" :key="k">
            <a href="#"
               @click.prevent="fetchMusic(k)"
               :class="{active: k === selected}"
               v-html="v" />
          </li>
        </ul>
      </header>
      <ChartArticle v-for="(v, k) in limit" :key="v" v-bind="{ k, ...music.articles[k] }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_MUSIC, INIT_VIDEO } from '@/middleware/store/music/const';
import { ChartArticle, VideoPlayer } from '@/components/chart';
import { eventBus } from '@/helper';

export default {
  components: { ChartArticle, VideoPlayer },
  computed: mapState(['music']),
  data() {
    return {
      limit: 10,
      categories: ['실시간', '일간', '발라드', '댄스', '랩/힙합', 'R&B/Soul', '인디'],
      selected: 0,
    };
  },
  created() {
    this.fetchMusic(0);
    eventBus.$on('chartLoad', this.listLoading);
  },
  methods: {
    fetchMusic(selected) {
      this.selected = selected;
      this.$store.dispatch(FETCH_MUSIC, this.categories[selected]);
      this.$store.commit(INIT_VIDEO);
    },
    listLoading() {
      if (this.limit >= 100) return;
      this.limit += 10;
    },
  },
};
</script>
