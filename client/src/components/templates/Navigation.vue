<template>
  <nav class="main-content__header" ref="nav">
    <ul>
      <li v-for="(v, k) in menu" :key="k">
        <router-link :to="v.url">
          <span v-html="v.title" />
        </router-link>
      </li>
    </ul>
  </nav>
</template>
<script>
export default {
  data() {
    return {
      menu: [
        { title: '뉴스', url: '/' },
        { title: '음원차트', url: '/chart' },
        { title: '인기영상', url: '/popular' },
      ],
    };
  },
};
</script>
