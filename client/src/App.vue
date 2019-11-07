<template>
  <div id="app">
    <SiteHeader />
    <div class="content-wrap">
      <nav class="main-content__header">
        <ul>
          <li v-for="(v, k) in menu" :key="k">
            <a href="#" :class="{active: k === menuActive}" @click.prevent="activeMenu(k)">
              <span v-html="v.title" />
            </a>
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
        { title: '뉴스' },
        { title: '음원차트' },
        { title: '인기영상' },
      ],
      menuActive: 0,
    };
  },
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
