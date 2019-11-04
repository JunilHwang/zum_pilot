<template>
  <div id="app">
    <SiteHeader />
    <router-view />
  </div>
</template>

<script>
import SiteHeader from '@/components/templates/SiteHeader.vue';

export default {
  components: {
    SiteHeader,
  },
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
