<template>
  <article class="news-article">
    <figure class="news-article__img">
      <router-link :to="`/news/${encodeURIComponent(link)}`">
        <img :src="img" :alt="title">
      </router-link>
    </figure>
    <div class="news-article__content">
      <h3 class="news-article__title">
        <router-link :to="`/news/${encodeURIComponent(link)}`" v-html="title" />
      </h3>
      <p class="news-article__regdate">
        <FAI :icon="['far', 'clock']" />
        {{ regDate }}
      </p>
    </div>
  </article>
</template>

<script>
export default {
  props: ['title', 'regDate', 'img', 'link'],
};

</script>
