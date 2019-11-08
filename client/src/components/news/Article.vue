<template>
  <article class="news-article">
    <figure class="news-article__img">
      <a href="#">
        <img :src="'https://source.unsplash.com/random/200x'+(200+Math.random()*10)" :alt="title">
      </a>
    </figure>
    <div class="news-article__content">
      <h3 class="news-article__title">
        <a href="#" v-html="title" />
      </h3>
      <p class="news-article__regdate">
        <FAI :icon="['far', 'clock']" />2019-11-05 10:40:45
      </p>
    </div>
  </article>
</template>

<script>
export default {
  props: ['title'],
};

</script>
