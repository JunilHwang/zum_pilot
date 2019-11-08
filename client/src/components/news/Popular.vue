<template>
  <li>
    <router-link :to="`/news/${encodeURIComponent(link)}`">
      <img :src="img" :alt="title">
      <h3 class="news-thumbs__title" v-html="helper.previewTitle(title, 30)" />
    </router-link>
  </li>
</template>

<script>
export default {
  props: ['title', 'img', 'link'],
};

</script>
