<template>
  <main>
    <header class="chart__category">
      <ul>
        <li v-for="(v, k) in categories" :key="k">
          <a href="#"
             @click.prevent="fetchMusic(k)"
             :class="{active: k === selectedCategory}"
             v-html="v" />
        </li>
      </ul>
    </header>
    <transition name="slide-down">
      <VideoPlayer />
    </transition>
    <section class="chart__wrap">
      <ChartArticle
        v-for="(v, k) in limit"
        :key="v"
        :class="{ active: selectedArticle === k }"
        v-bind="{ k, ...music.articles[k] }"
        @select="selectArticle"
      />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_MUSIC, SELECT_VIDEO } from '@/middleware/store/music/const';
import { ChartArticle, VideoPlayer } from '@/components/chart';
import { eventBus } from '@/helper';

export default {
  components: { ChartArticle, VideoPlayer },
  computed: mapState(['music']),
  data() {
    return {
      limit: 10,
      categories: ['실시간', '일간', '발라드', '댄스', '힙합', 'R&B/Soul', '인디'],
      selectedCategory: 0,
      selectedArticle: null,
    };
  },
  created() {
    this.fetchMusic(0);
    eventBus.$on('chartLoad', this.listLoading);
  },
  methods: {
    fetchMusic(k) {
      this.selectedCategory = k;
      this.$store.dispatch(FETCH_MUSIC, this.categories[k]);
    },
    listLoading() {
      if (this.limit >= 100) return;
      this.limit += 10;
    },
    selectArticle(k) {
      this.selectedArticle = k;
    },
  },
  destroyed() {
    this.$store.commit(SELECT_VIDEO, null);
  },
};
</script>
