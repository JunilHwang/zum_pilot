<template>
  <main class="chart">
    <section class="chart__video">
      <strong>비디오 영역입니다.</strong>
    </section>
    <section class="chart__wrap">
      <article class="chart__article" v-for="(v, k) in list" :key="k">
        <strong class="chart__rank" v-html="k + 1" />
        <dl class="chart__info">
          <dt v-html="v" />
          <dd>Artist {{k}}</dd>
        </dl>
        <figure class="chart__thumbnail">
          <img :src="'https://source.unsplash.com/random/100x'+(100+Math.random()*10)" alt="music title">
        </figure>
      </article>
    </section>
  </main>
</template>
<script>
export default {
  data() {
    return {
      initList: [
        'Lorem ipsum dolor.',
        'At, excepturi, fuga?',
        'Est, obcaecati ratione.',
        'Accusamus, aperiam, optio!',
        'Natus, provident quae!',
        'At eaque, tenetur.',
        'Cupiditate totam, unde!',
        'Adipisci laboriosam, rerum.',
        'Blanditiis, cum incidunt?',
        'Dolore, ipsam porro.',
      ],
      list: [],
    };
  },
  created() {
    this.list.push(...this.initList);
    window.addEventListener('scroll', this.listLoading);
  },
  methods: {
    listLoading() {
      const { innerHeight, scrollY } = window;
      const { scrollHeight } = document.body;
      if (scrollHeight - innerHeight - scrollY === 0) {
        this.list.push(...this.initList);
        if (this.list.length >= 100) {
          window.removeEventListener('scroll', this.listLoading);
        }
      }
    },
  },
};
</script>
