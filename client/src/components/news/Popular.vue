<template>
  <li>
    <a href="#">
      <img :src="'https://source.unsplash.com/random/300x'+(300+Math.random()*10)" :alt="title">
      <h3 class="news-thumbs__title" v-html="helper.previewTitle(title, 30)" />
    </a>
  </li>
</template>

<script>
export default {
  props: ['title'],
};

</script>
