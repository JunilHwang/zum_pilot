<template>
  <main class="chart">
    <section class="chart__video">
      <strong>비디오 영역입니다.</strong>
    </section>
    <section class="chart__wrap">
      <article class="chart__article" v-for="k in 10" :key="k">
        <strong class="chart__rank" v-html="k" />
        <dl class="chart__info">
          <dt>Music title {{k}}</dt>
          <dd>Artist {{k}}</dd>
        </dl>
        <figure class="chart__thumbnail">
          <img :src="'https://source.unsplash.com/random/100x'+(100+Math.random()*10)" alt="music title">
        </figure>
      </article>
    </section>
  </main>
</template>
