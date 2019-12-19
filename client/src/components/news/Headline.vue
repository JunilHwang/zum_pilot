<template>
  <article class="news-main">
    <a href="#" @click.prevent>
      <figure class="news-main__img">
        <img :src="img" :alt="title">
      </figure>
      <h3 class="news-main__title" v-html="title" />
    </a>
  </article>
</template>

<script>
export default {
  props: ['title', 'img', 'link'],
};
</script>
