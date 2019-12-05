<template>
  <article class="news-article">
    <a href="#" @click.prevent="$parent.viewDetail">
      <figure class="news-article__img">
          <img :src="img" :alt="title">
      </figure>
      <div class="news-article__content">
        <h3 class="news-article__title" v-html="title" />
        <p class="news-article__regdate">
          <FAI :icon="['far', 'clock']" />
          {{ regDate }}
        </p>
      </div>
    </a>
  </article>
</template>

<script>
export default {
  props: ['title', 'regDate', 'img', 'link'],
};

</script>
