<template functional>
  <article class="newsMain">
    <a href="#" @click.prevent>
      <figure class="newsMainImg">
        <img :src="props.img" :alt="props.title">
      </figure>
      <h3 class="newsMainTitle" v-html="props.title" />
    </a>
  </article>
</template>
