<template functional>
  <article class="newsArticle">
    <a href="#" @click.prevent="parent.viewDetail">
      <figure class="newsArticleImg">
          <img :src="props.img" :alt="props.title">
      </figure>
      <div class="newsArticleContent">
        <h3 class="newsArticleTitle" v-html="props.title" />
        <p class="newsArticleRegdate">
          <FAI :icon="['far', 'clock']" />
          {{ props.regDate }}
        </p>
      </div>
    </a>
  </article>
</template>
