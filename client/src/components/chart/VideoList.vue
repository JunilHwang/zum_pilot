<template>
  <Carousel class="video__wrap" :paginationEnabled="false">
    <Slide class="video__slide" v-for="(v, k) in music.video" :key="k">
      <figure class="video__thumbnail">
        <img :src="v.thumbnail" :alt="v.title"  />
      </figure>
      <strong class="video__title" v-html="v.title" />
    </Slide>
  </Carousel>
</template>

<script>
import {
  Carousel,
  Slide,
} from 'vue-carousel';
import { mapState } from 'vuex';

export default {
  components: {
    Carousel,
    Slide,
  },
  computed: mapState(['music']),
};
</script>
