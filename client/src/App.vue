<template>
  <div id="app">
    <SiteHeader />
    <transition name="component-fade" mode="out-in">
      <router-view />
    </transition>
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
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
