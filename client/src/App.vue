<template>
  <div id="app" :class="{ sticky: isSticky }">
    <SiteHeader>
      <Navigation />
    </SiteHeader>
    <div class="content-wrap" ref="wrap">
      <router-view class="main-content" />
    </div>
    <a href="#" class="go-top" v-show="isSticky">
      <FAI icon="chevron-up" />
    </a>
    <SiteFooter v-once />
    <transition name="modal-fade">
      <Alert v-if="isAlertShow" />
    </transition>
  </div>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { mapState } from 'vuex';
import { SiteHeader, SiteFooter, Navigation } from '@/components/common';
import { Alert } from '@/components/modal';
import { eventBus, windowBottomSensor } from '@/helper';

const components = { SiteHeader, SiteFooter, Alert, Navigation };
const isAlertShow = state => state.modal.show === 'alert';

@Component({
  components,
  computed: mapState({ isAlertShow }),
})
export default class App extends Vue {
  isSticky = false;

  mounted() {
    window.removeEventListener('scroll', this.scrollEvents);
    window.addEventListener('scroll', this.scrollEvents);
  }

  get path() {
    return this.$route.path;
  }

  scrollEvents() {
    const sy = window.scrollY;
    const ot = this.$refs.wrap.offsetTop - 37;
    this.sticky(sy, ot);
    const methodName = {
      '/': 'newsLoad',
      '/news': 'newsLoad',
      '/chart': 'chartLoad',
    }[this.path];
    if (methodName !== undefined) {
      windowBottomSensor(() => {
        eventBus.$emit(methodName);
      });
    }
  }

  sticky(sy, ot) {
    if (sy > ot && !this.isSticky) {
      this.isSticky = true;
    } else if (sy <= ot && this.isSticky) {
      this.isSticky = false;
    }
  }
}
</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
