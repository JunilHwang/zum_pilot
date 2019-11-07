<template>
  <main class="main-content">
    <section class="main-content__section news">
      <article class="news-main">
        <router-link to="/news/1">
          <figure class="news-main__img">
            <img src="https://img.sbs.co.kr/newsnet/etv/upload/2019/10/29/30000636820.jpg" alt="아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?">
          </figure>
          <h3 class="news-main__title">아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?</h3>
        </router-link>
      </article>
      <ul class="news-thumbs">
        <li v-for="k in 4" :key="k">
          <a href="#">
            <img :src="'https://source.unsplash.com/random/300x'+(300+Math.random()*10)" :alt="titles[k]">
            <h3 class="news-thumbs__title" v-html="helper.previewTitle(titles[k - 1], 30)" />
          </a>
        </li>
      </ul>
      <article class="news-article" v-for="k in 5" :key="k">
        <figure class="news-article__img">
          <a href="#">
            <img :src="'https://source.unsplash.com/random/200x'+(200+Math.random()*10)" alt="아이즈원, 신곡에 엠씨몽 작곡 참여…'불똥' 튈라 팬들은 '우려'">
          </a>
        </figure>
        <div class="news-article__content">
          <h3 class="news-article__title">
            <a href="#">아이즈원, 신곡에 엠씨몽 작곡 참여…'불똥' 튈라 팬들은 '우려'</a>
          </h3>
          <p class="news-article__regdate">
            <FAI :icon="['far', 'clock']" />2019-11-05 10:40:45
          </p>
        </div>
      </article>
    </section>
  </main>
</template>
<script>
export default {
  data() {
    return {
      titles: [
        'Lorem ipsum dolor sit amet',
        'consectetur adipisicing elit',
        'Aliquam deleniti dignissimos ducimus',
        'laudantium maiores officia porro qui',
      ],
    };
  },
  beforeRouteLeave(to, from, next) {
    this.$store.commit('changeRouterTransition', 'slide-left');
    next();
  },
  methods: {
    activeMenu(menuIndex) {
      this.menuActive = menuIndex;
    },
  },
};
</script>
