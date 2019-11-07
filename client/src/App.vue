<template>
  <div id="app" :class="{ sticky: isSticky }">
    <SiteHeader />
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
      <transition :name="$store.state.routerTransition">
        <router-view />
      </transition>
    </div>
    <SiteFooter />
  </div>
</template>

<script>
import SiteHeader from '@/components/templates/SiteHeader.vue';
import SiteFooter from '@/components/templates/SiteFooter.vue';

export default {
  components: {
    SiteHeader,
    SiteFooter,
  },
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
    window.removeEventListener('scroll', this.sticky);
    window.addEventListener('scroll', this.sticky);
    this.menuOffestTop = this.$refs.nav.offsetTop;
  },
  methods: {
    sticky() {
      const { scrollY: y } = window;
      const ot = this.menuOffestTop;
      if (y > ot && !this.isSticky) {
        this.isSticky = true;
      } else if (y <= ot && this.isSticky) {
        this.isSticky = false;
      }
    },
  },
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
