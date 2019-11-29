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
import { mapState } from 'vuex';
import { FETCH_VIDEO } from '@/middleware/store/music/const';

export default {
  computed: mapState(['music']),
  methods: {
    searchVideo() {
      this.$store.dispatch(FETCH_VIDEO, {
        selected: this.k,
        q: this.title,
      });
      this.$emit('select', this.k);
    },
  },
  props: ['k', 'title', 'album', 'artist', 'img'],
};
</script>
