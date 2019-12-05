<template>
  <header class="site-header">
    <h3 class="site-header__logo">
      <router-link to="/"><img src="@/assets/logo.gif" alt="줌인터넷"></router-link>
    </h3>
    <Navigation />
  </header>
</template>
<script>
import { Navigation } from './index';

export default {
  components: { Navigation },
};
</script>
