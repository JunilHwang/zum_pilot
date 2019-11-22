<template>
  <div class="video__wrap">
    <flicking class="panels"
              @select="selectVideo"
              :options="{ gap: 2, circular: true, hanger: 0, anchor: 0 }">
      <div v-for="(v, k) in music.video"
           class="panel"
           :key="k">
        <figure class="video__thumbnail">
          <img :src="v.thumbnail" :alt="v.title"  />
        </figure>
        <strong class="video__title" v-html="v.title" />
      </div>
    </flicking>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { SELECT_VIDEO } from '@/store/music/const';

export default {
  computed: mapState(['music']),
  data() {
    return { x: 0, y: 0 };
  },
  methods: {
    selectVideo({ index }) {
      this.$store.commit(SELECT_VIDEO, this.music.video[index]);
    },
  },
};
</script>
