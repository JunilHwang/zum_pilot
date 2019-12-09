<template>
  <article class="video-article" @click="selectVideo">
    <figure class="video-article__thumbnail">
      <img :src="thumbnail" :alt="title" />
    </figure>
    <div class="video-article__meta">
      <VideoMeta
        classPrefix="popular-meta"
        v-bind="{ idx, title, viewCount, likeCount }"
        :hide="['bookmark']">
        <span class="popular-meta__point" v-html="`인기도 ${popularPoint}`" />
      </VideoMeta>
    </div>
  </article>
</template>
<script>
import { VideoMeta } from '@/components/video/index';

const components = { VideoMeta };

export default {
  components,
  methods: {
    selectVideo(event) {
      this.$emit('select', event);
    },
  },
  props: ['idx', 'thumbnail', 'title', 'viewCount', 'likeCount', 'popularPoint'],
};
</script>
