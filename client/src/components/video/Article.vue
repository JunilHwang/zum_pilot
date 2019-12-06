<template>
  <article>
    <figure>
      <img :src="thumbnail" :alt="title" />
    </figure>
    <VideoMeta classPrefix="popular-meta" v-bind="{ title, viewCount, likeCount }">
      <strong v-html="popularPoint" />
    </VideoMeta>
  </article>
</template>
<script>
import { VideoMeta } from '@/components/video/index';

const components = { VideoMeta };

export default {
  components,
  props: ['idx', 'thumbnail', 'videoId', 'title', 'viewCount', 'likeCount', 'popularPoint'],
};
</script>
