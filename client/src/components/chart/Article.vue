<template>
  <article class="chart__article">
    <div class="chart__article-wrap" @click="searchVideo">
      <strong class="chart__rank" v-html="k + 1"/>
      <dl class="chart__info">
        <dt v-html="title"/>
        <dd v-html="artist"/>
      </dl>
      <figure class="chart__thumbnail">
        <img :src="img" :alt="title">
      </figure>
    </div>
  </article>
</template>

<script>
import { FETCH_VIDEO } from '@/middleware/store/video/const';
import { SELECT_MUSIC } from '@/middleware/store/music/const';

export default {
  methods: {
    searchVideo() {
      const { title, artist } = this;
      this.$store.dispatch(FETCH_VIDEO, title);
      this.$store.commit(SELECT_MUSIC, `${title}/${artist}`);
    },
  },
  props: ['k', 'title', 'album', 'artist', 'img'],
};
</script>
