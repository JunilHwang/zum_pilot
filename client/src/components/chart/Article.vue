<template>
  <article class="chart__article">
    <strong class="chart__rank" v-html="k + 1" />
    <dl class="chart__info">
      <dt v-html="title" />
      <dd>
        {{artist}} / {{album}}
      </dd>
    </dl>
    <figure class="chart__thumbnail">
      <img :src="img" :alt="title">
    </figure>
  </article>
</template>

<script>
export default {
  props: ['idx', 'title', 'album', 'artist', 'img'],
}
</script>
