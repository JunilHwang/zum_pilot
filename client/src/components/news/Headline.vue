<template>
  <article class="news-main">
    <router-link to="/news/1">
      <figure class="news-main__img">
        <img :src="img" :alt="title">
      </figure>
      <h3 class="news-main__title" v-html="title" />
    </router-link>
  </article>
</template>

<script>
export default {
  props: ['title', 'img'],
};
</script>
