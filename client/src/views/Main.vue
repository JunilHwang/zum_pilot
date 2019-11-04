<template>
  <div class="content-wrap">
    <section>
      <header class="content-header">
        <ul>
          <li v-for="(v, k) in menu" :key="k">
            <a href="#" :class="{active: k === menuActive}" @click.prevent="activeMenu(k)">
              <span v-html="v.title" />
            </a>
          </li>
        </ul>
      </header>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menu: [
        {
          title: '최신뉴스',
        },
        {
          title: '음원차트',
        },
        {
          title: '인기영상',
        },
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
