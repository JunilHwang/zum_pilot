<template>
  <article class="news-main">
    <router-link to="/news/1">
      <figure class="news-main__img">
        <img src="https://img.sbs.co.kr/newsnet/etv/upload/2019/10/29/30000636820.jpg" alt="아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?">
      </figure>
      <h3 class="news-main__title" v-html="title" />
    </router-link>
  </article>
</template>

<script>
export default {
  props: ['title'],
};
</script>
