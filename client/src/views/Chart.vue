<template>
  <main class="chart">
    <VideoPlayer :options="videoOptions" />
    <section class="chart__wrap">
      <Article v-for="(v, k) in music.articles" :key="k" v-bind="{ k, ...v }" />
    </section>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import Article from '@/components/chart/Article.vue';
import VideoPlayer from '@/components/chart/VideoPlayer.vue';
import { FETCH_MUSIC } from '@/store/music/const';

export default {
  components: {
    Article,
    VideoPlayer,
  },
  computed: mapState(['music']),
  data() {
    return {
      videoOptions: {
        autoplay: true,
        controls: true,
        sources: [
          {
            type: 'video/youtube',
            src: 'https://www.youtube.com/watch?v=xjS6SftYQaQ',
          },
        ],
      },
    };
  },
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
