<template>
  <div class="modal" @click.self="close">
    <span class="middle"></span>
    <div class="modal__container">
      <a href="#" class="modal__close" @click.prevent="close">
        <span class="middle" /><FAI icon="times" />
      </a>
      <slot />
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { MODAL_CLOSE } from '@/middleware/store/mutations-type';

@Component
export default class Wrapper extends Vue {
  close() { this.$store.commit(MODAL_CLOSE); }
}
</script>
