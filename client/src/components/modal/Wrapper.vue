<template>
  <div class="modal" @click.self="close">
    <span class="middle"></span>
    <div class="modal__container">
      <a href="#" class="modal__close" @click.prevent="close">
        <span class="middle"></span>
        <FAI icon="times" />
      </a>
      <slot></slot>
    </div>
  </div>
</template>
<script>
import { MODAL_CLOSE } from '@/middleware/store/mutations-type';

export default {
  methods: {
    close() {
      this.$store.commit(MODAL_CLOSE);
    },
  },
};
</script>
