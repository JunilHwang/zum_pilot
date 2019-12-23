<template functional>
  <article class="news-article">
    <a href="#" @click.prevent="parent.viewDetail">
      <figure class="news-article__img">
          <img :src="props.img" :alt="props.title">
      </figure>
      <div class="news-article__content">
        <h3 class="news-article__title" v-html="props.title" />
        <p class="news-article__regdate">
          <FAI :icon="['far', 'clock']" />
          {{ props.regDate }}
        </p>
      </div>
    </a>
  </article>
</template>
