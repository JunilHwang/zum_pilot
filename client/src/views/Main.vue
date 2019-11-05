<template>
  <main class="content-wrap">
    <header class="content-header">
      <ul>
        <li v-for="(v, k) in menu" :key="k">
          <a href="#" :class="{active: k === menuActive}" @click.prevent="activeMenu(k)">
            <span v-html="v.title" />
          </a>
        </li>
      </ul>
    </header>
    <section class="content-section news">
      <article class="news-main">
        <a href="#">
          <figure class="news-main__img">
            <img src="https://img.sbs.co.kr/newsnet/etv/upload/2019/10/29/30000636820.jpg" alt="아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?">
          </figure>
          <h3 class="news-main__title">아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?</h3>
        </a>
      </article>
      <article class="news-article" v-for="k in 5" :key="k">
        <figure class="news-article__img">
          <a href="#">
            <img src="https://img.sbs.co.kr/newsnet/etv/upload/2019/10/25/30000636581_16v9.jpg" alt="아이즈원, 신곡에 엠씨몽 작곡 참여…'불똥' 튈라 팬들은 '우려'">
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
      menu: [
        { title: '뉴스' },
        { title: '음원차트' },
        { title: '인기영상' },
      ],
      menuActive: 0,
    };
  },
  methods: {
    activeMenu(menuIndex) {
      this.menuActive = menuIndex;
    },
  },
};
</script>
