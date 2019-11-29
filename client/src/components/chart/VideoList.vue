<template>
  <div class="video__wrap">
    <Flicking class="panels"
              @select="selectVideo"
              :options="{ gap: 2, circular: true, hanger: 0, anchor: 0, zIndex: 10 }">
      <div v-for="(v, k) in music.video"
           class="panel"
           :key="k">
        <figure class="video__thumbnail">
          <img :src="v.thumbnail" :alt="v.title"  />
        </figure>
        <strong class="video__title" v-html="v.title" />
      </div>
    </Flicking>
  </div>
</template>

<script>
import { Flicking } from '@egjs/vue-flicking';
import { mapState } from 'vuex';
import { SELECT_VIDEO } from '@/middleware/store/music/const';

export default {
  components: { Flicking },
  computed: mapState(['music']),
  methods: {
    selectVideo({ index }) {
      this.$store.commit(SELECT_VIDEO, this.music.video[index]);
    },
  },
};
</script>
