<template functional>
  <article :class="`chartArticle ${props.active}`">
    <div class="chartArticleWrap" @click="listeners.select">
      <strong class="chartRank" v-html="props.k + 1"/>
      <dl class="chartInfo">
        <dt v-html="props.title"/>
        <dd v-html="props.artist"/>
      </dl>
      <figure class="chartThumbnail">
        <img :src="props.img" :alt="props.title">
      </figure>
    </div>
  </article>
</template>
