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
    <transition name="modal-fade">
      <Alert v-if="isAlertShow" />
    </transition>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { SiteHeader, SiteFooter } from '@/components/common';
import { Alert } from '@/components/modal';
import { eventBus, windowBottomSensor } from '@/helper';

const components = { SiteHeader, SiteFooter, Alert };
const isAlertShow = state => state.modal.show === 'alert';

export default {
  components,
  computed: {
    ...mapState({ isAlertShow }),
    path() { return this.$route.path; },
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
    },
    sticky(sy, ot) {
      if (sy > ot && !this.isSticky) {
        this.isSticky = true;
      } else if (sy <= ot && this.isSticky) {
        this.isSticky = false;
      }
    },
  },
};

</script>

<style lang="scss" src="@/assets/scss/common.scss"></style>
