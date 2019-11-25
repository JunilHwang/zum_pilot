<template>
  <main class="main-content">
    <Flicking class="main-content__panels"
              :options="flickOption"
              @change="pageChange"
              ref="flicking">
      <div class="main-content__panel">
        <News />
      </div>
      <div class="main-content__panel">
        <Chart />
      </div>
    </Flicking>
    <transition name="page-up">
      <NewsDetail v-if="news.viewState !== false" />
    </transition>
  </main>
</template>
<script>
import { Flicking } from '@egjs/vue-flicking';
import { mapState } from 'vuex';
import { News, NewsDetail } from '@/components/news';
import { Chart } from '@/components/chart';
import eventBus from '@/eventBus';

export default {
  components: {
    News,
    NewsDetail,
    Chart,
    Flicking,
  },
  computed: {
    ...mapState(['news']),
    path() {
      return this.$route.path;
    },
    flicking() {
      return this.$refs.flicking;
    },
  },
  created() {
    window.addEventListener('scroll', this.listLoading);
  },
  data() {
    return {
      flickOption: {
        classPrefix: 'main-content__flick',
        hanger: 0,
        anchor: 0,
        zIndex: 10,
        gap: 10,
      },
    };
  },
  methods: {
    pageChange({ index }) {
      const nextPath = ['/', '/chart'][index];
      if (nextPath !== this.path) {
        this.$router.push(nextPath);
      }
    },
    move() {
      const index = { '/': 0, '/chart': 1 }[this.path];
      this.flicking.moveTo(index);
      this.autoHeight();
    },
    listLoading() {
      this.helper.windowBottomSensor(() => {
        const idx = this.flicking.getIndex();
        console.log(idx);
      });
    },
    autoHeight() {
      setTimeout(() => {
        const { getIndex: idx, getElement: el } = this.flicking;
        const { clientHeight: h } = el().querySelector(`.main-content__panel:nth-child(${idx() + 1})`);
        el().querySelector('.main-content__flick-viewport').style.height = `${h}px`;
      }, 100);
    },
  },
  mounted() {
    this.move();
    eventBus.$on('resize', this.autoHeight);
  },
  watch: {
    path() {
      this.move();
    },
  },
};
</script>
