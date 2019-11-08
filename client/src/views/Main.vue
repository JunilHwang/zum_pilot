<template>
  <main class="main-content">
    <section class="main-content__section news">
      <NewsWrapper
        type="Headline"
        :properties="{ title: '아이유 광주 콘서트, 관객 퇴장 조치 논란…무슨 일?' }"
      />
      <ul class="news-thumbs">
        <NewsWrapper
          v-for="k in 4"
          type="Popular"
          :properties="{ title: titles[k - 1] }"
          :key="k"
        />
      </ul>
      <NewsWrapper
        v-for="k in 5"
        type="Article"
        :properties="{ title: titles2[k - 1] }"
        :key="k"
      />
    </section>
  </main>
</template>
<script>
import { CHANGE_TRANSITION } from '@/store/const';
import NewsWrapper from '@/components/news/NewsWrapper.vue';

export default {
  components: {
    NewsWrapper,
  },
  data() {
    return {
      titles: [
        'Lorem ipsum dolor sit amet',
        'consectetur adipisicing elit',
        'Aliquam deleniti dignissimos ducimus',
        'laudantium maiores officia porro qui',
      ],
      titles2: [
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit.',
        'Adipisci animi at deleniti dignissimos doloremque dolores ea et fuga',
        'Aliquam blanditiis cupiditate, delectus error ex, fugit itaque',
        'Aliquam corporis cumque eaque eligendi, fugit hic illum ipsam',
        'Consequuntur culpa dicta dolores, magni nisi recusandae voluptas?',
      ],
    };
  },
  beforeRouteLeave(to, from, next) {
    if (to.path.indexOf('/news') !== -1) {
      this.$store.commit(CHANGE_TRANSITION, 'slide-left');
    }
    next();
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>
