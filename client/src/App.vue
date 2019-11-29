<template>
  <div id="app" :class="{ sticky: isSticky }">
    <SiteHeader />
    <div class="content-wrap" ref="wrap">
      <router-view class="main-content" />
    </div>
    <a href="#" class="go-top" v-show="isSticky">
      <FAI icon="chevron-up" />
    </a>
    <SiteFooter />
  </div>
</template>

<script>
import { SiteHeader, SiteFooter } from '@/components/templates';
import { eventBus, windowBottomSensor } from '@/helper';

export default {
  components: { SiteHeader, SiteFooter },
  computed: {
    path() {
      return this.$route.path;
    },
    wrap() {
      return this.$refs.wrap;
    },
  },
  data() {
    return {
      isSticky: false,
    };
  },
  mounted() {
    window.removeEventListener('scroll', this.scrollEvents);
    window.addEventListener('scroll', this.scrollEvents);
  },
  methods: {
    scrollEvents() {
      const sy = window.scrollY;
      const ot = this.wrap.offsetTop - 37;
      this.sticky(sy, ot);
      windowBottomSensor(() => {
        const methodName = {
          '/': 'newsLoad',
          '/news': 'newsLoad',
          '/chart': 'chartLoad',
        }[this.path];
        eventBus.$emit(methodName);
      });
    },
    sticky(sy, ot) {
      switch (true) {
        case sy > ot && !this.isSticky:
          this.isSticky = true;
          break;
        case sy <= ot && this.isSticky:
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
