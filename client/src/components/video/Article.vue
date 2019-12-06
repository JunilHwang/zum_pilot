<template>
  <article>
    <figure>
      <img :src="thumbnail" :alt="title" />
    </figure>
    <VideoMeta v-bind="{ title, viewCount, likeCount }">

    </VideoMeta>
  </article>
</template>
<script>
export default {
  props: ['idx', 'thumbnail', 'videoId', 'title', 'viewCount', 'likeCount', 'popularPoint'],
};
</script>
