<template>
  <Carousel class="video__wrap" :paginationEnabled="false">
    <Slide class="video__slide"
           v-for="(v, k) in music.video"
           :key="k"
           @slide-click="selectVideo(v)">
      <figure class="video__thumbnail">
        <img :src="v.thumbnail" :alt="v.title"  />
      </figure>
      <strong class="video__title" v-html="v.title" />
    </Slide>
  </Carousel>
</template>

<script>
import { Carousel, Slide } from 'vue-carousel';
import { mapState } from 'vuex';
import { SELECT_VIDEO } from '@/store/music/const';

export default {
  components: { Carousel, Slide },
  computed: mapState(['music']),
  data() {
    return { x: 0, y: 0 };
  },
  methods: {
    selectVideo(video) {
      this.$store.commit(SELECT_VIDEO, video);
    },
  },
};
</script>
