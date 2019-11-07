<template>
  <main class="news-detail">
    <h1>아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?</h1>
    <figure>
      <img src="https://img.sbs.co.kr/newsnet/etv/upload/2019/10/29/30000636820.jpg" alt="아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?">
    </figure>
  </main>
</template>

<script>
export default {
  beforeRouteLeave(to, from, next) {
    if (to.path === '/') {
      this.$store.commit('changeRouterTransition', 'slide-right');
    }
    next();
  },
};
</script>
