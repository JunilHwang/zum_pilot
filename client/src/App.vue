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
    <SiteFooter />
    <transition name="modal-fade">
      <Alert v-if="isAlertShow" />
    </transition>
  </div>
</template>

<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { SiteHeader, SiteFooter, Navigation } from '@/components/common';
import { Alert } from '@/components/modal';
import { eventBus, windowBottomSensor } from '@/helper';

const components = { SiteHeader, SiteFooter, Alert, Navigation };

@Component({ components })
export default class App extends Vue {
  @State(state => state.modal.show === 'alert') isAlertShow;
  isSticky = false; // Header sticky 여부
  get path() { return this.$route.path; }

  /**
   * Scroll Event 등록
   */
  mounted() {
    window.removeEventListener('scroll', this.scrollEvents);
    window.addEventListener('scroll', this.scrollEvents);
  }

  /**
   * Scroll Event들을 등록한다.
   * Sticky, Infinity loading 등
   */
  scrollEvents() {
    const sy = window.scrollY; // scroll y축 위치
    const ot = this.$refs.wrap.offsetTop - 37; // Content의 offset
    this.sticky(sy, ot); // sticky 여부 결정

    // infinity loading method 결정
    const methodName = { '/': 'newsLoad', '/news': 'newsLoad', '/chart': 'chartLoad' }[this.path];

    // 하단 감지 후 infinity loading method 실행
    if (methodName !== undefined) {
      windowBottomSensor(() => eventBus.$emit(methodName));
    }
  }

  /**
   * Header의 Sticky 여부 결정
   * @param sy : window의 scroll Y 값
   * @param ot : contnet의 offset top 값
   */
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
