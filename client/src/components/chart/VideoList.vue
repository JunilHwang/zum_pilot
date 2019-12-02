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
        <ul class="video__meta">
          <li class="video__title" v-html="previewTitle(v.title, 50)" />
          <li class="video__meta-bottom">
            <span class="video__view-count">
              <FAI icon="eye" />
              {{ v.viewCount }}
            </span>
            <span class="video__like">
              <FAI icon="thumbs-up" />
              0
            </span>
          </li>
        </ul>
      </div>
    </Flicking>
  </div>
</template>

<script>
import { Flicking } from '@egjs/vue-flicking';
import { mapState } from 'vuex';
import { SELECT_VIDEO } from '@/middleware/store/video/const';
import { previewTitle } from '@/helper';

export default {
  components: { Flicking },
  computed: mapState(['video']),
  methods: {
    previewTitle,
    selectVideo({ index }) {
      this.$store.commit(SELECT_VIDEO, this.video.videoList[index]);
    },
  },
};
</script>
