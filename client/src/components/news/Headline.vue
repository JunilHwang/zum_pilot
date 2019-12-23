<template functional>
  <article class="news-main">
    <a href="#" @click.prevent>
      <figure class="news-main__img">
        <img :src="props.img" :alt="props.title">
      </figure>
      <h3 class="news-main__title" v-html="props.title" />
    </a>
  </article>
</template>
