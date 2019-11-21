<template>
  <article class="news-article">
    <figure class="news-article__img">
      <a @click.prevent="viewDetail">
        <img :src="img" :alt="title">
      </a>
    </figure>
    <div class="news-article__content">
      <h3 class="news-article__title">
        <a @click.prevent="viewDetail" v-html="title" />
      </h3>
      <p class="news-article__regdate">
        <FAI :icon="['far', 'clock']" />
        {{ regDate }}
      </p>
    </div>
  </article>
</template>

<script>
import { VIEW_CONTENT } from '@/store/news/const';

export default {
  methods: {
    viewDetail() {
      this.$store.commit(VIEW_CONTENT, this.link);
    },
  },
  props: ['title', 'regDate', 'img', 'link'],
};

</script>
