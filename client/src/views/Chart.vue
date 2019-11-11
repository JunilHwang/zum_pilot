<template>
  <main class="chart">
    <section class="chart__video">
      <strong>비디오 영역입니다.</strong>
    </section>
    <section class="chart__wrap">
      <Article v-for="(v, k) in music.articles" :key="k" v-bind="{ k, ...v }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import Article from '@/components/chart/Article.vue';
import { FETCH_MUSIC } from '@/store/music/const';

export default {
  components: {
    Article,
  },
  computed: mapState(['music']),
  created() {
    this.$store.dispatch(FETCH_MUSIC);
    // window.removeEventListener('scroll', this.listLoading);
    // window.addEventListener('scroll', this.listLoading);
  },
  methods: {
    listLoading() {
      const { list, initList } = this;
      this.helper.windowBottomSensor(() => {
        list.push(...initList);
        if (list.length >= 100) {
          window.removeEventListener('scroll', this.listLoading);
        }
      });
    },
  },
};
</script>
