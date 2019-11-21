<template>
  <div id="app" :class="{ sticky: isSticky }">
    <SiteHeader v-once />
    <div class="content-wrap">
      <nav class="main-content__header" ref="nav">
        <ul>
          <li v-for="(v, k) in menu" :key="k">
            <router-link :to="v.url">
              <span v-html="v.title" />
            </router-link>
          </li>
        </ul>
      </nav>
      <router-view />
    </div>
    <a href="#" class="go-top" v-show="isSticky">
      <FAI icon="chevron-up" />
    </a>
    <SiteFooter v-once />
  </div>
</template>

<script>
const SiteHeader = () => import(/* webpackChunkName: "template" */'@/components/templates/SiteHeader.vue');
const SiteFooter = () => import(/* webpackChunkName: "template" */'@/components/templates/SiteFooter.vue');

export default {
  components: { SiteHeader, SiteFooter },
  data() {
    return {
      menu: [
        { title: '뉴스', url: '/' },
        { title: '음원차트', url: '/chart' },
        { title: '인기영상', url: '/popular' },
      ],
      isSticky: false,
      menuOffestTop: 0,
    };
  },
  mounted() {
    this.menuOffestTop = this.$refs.nav.offsetTop;
    window.removeEventListener('scroll', this.scrollEvents);
    window.addEventListener('scroll', this.scrollEvents);
  },
  methods: {
    scrollEvents() {
      const { scrollY } = window;
      const ot = this.menuOffestTop;
      this.sticky(scrollY, ot);
    },
    sticky(scrollY, ot) {
      switch (true) {
        case scrollY > ot && !this.isSticky:
          this.isSticky = true;
          break;
        case scrollY <= ot && this.isSticky:
          this.isSticky = false;
          break;
        default:
          break;
      }
    },
  },
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
