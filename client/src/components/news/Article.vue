<template>
  <article class="news-article">
    <figure class="news-article__img">
      <a :href="link" target="_blank">
        <img :src="img" :alt="title">
      </a>
    </figure>
    <div class="news-article__content">
      <h3 class="news-article__title">
        <a :href="link" target="_blank" v-html="title" />
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
