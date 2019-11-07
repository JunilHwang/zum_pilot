<template>
  <div class="news-detail">
    hello
  </div>
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
