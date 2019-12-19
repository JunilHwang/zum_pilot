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
import { VIDEO_FETCH, MUSIC_SELECT } from '@/middleware/store/mutations-type';

export default {
  methods: {
    searchVideo() {
      const { title, artist } = this;
      this.$store.dispatch(VIDEO_FETCH, title);
      this.$store.commit(MUSIC_SELECT, `${title}/${artist}`);
    },
  },
  props: ['k', 'title', 'album', 'artist', 'img'],
};
</script>
