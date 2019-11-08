<template>
  <li>
    <a href="#">
      <img :src="img" :alt="title">
      <h3 class="news-thumbs__title" v-html="helper.previewTitle(title, 30)" />
    </a>
  </li>
</template>

<script>
export default {
  props: ['title', 'img', 'link'],
};

</script>
