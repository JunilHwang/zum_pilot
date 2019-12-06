<template>
  <div class="video__wrap">
    <Flicking class="video__panels"
              @select="selectVideo"
              :options="{ gap: 2, circular: true, hanger: 0, anchor: 0, zIndex: 10 }">
      <div v-for="(v, k) in video.videoList"
           class="video__panel"
           :class="{ active: v === video.selectedVideo }"
           :key="k">
        <figure class="video__thumbnail">
          <img :src="v.thumbnail" :alt="v.title"  />
        </figure>
        <strong class="video__title" v-html="previewTitle(v.title, 50)" />
      </div>
    </Flicking>
  </div>
</template>

<script>
import { Flicking } from '@egjs/vue-flicking';
import { mapState } from 'vuex';
import { VIDEO_SELECT } from '@/middleware/store/mutations-type';
import { previewTitle } from '@/helper';

export default {
  components: { Flicking },
  computed: mapState(['video']),
  methods: {
    previewTitle,
    selectVideo({ index }) {
      this.$store.dispatch(VIDEO_SELECT, this.video.videoList[index]);
    },
  },
};
</script>
