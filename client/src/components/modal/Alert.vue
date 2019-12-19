<template>
  <Wrapper>
    <section class="alert" @keydown.enter="close">
      <h3 class="modal__title">경고</h3>
      <p v-html="props.message || '경고 메세지'" />
      <div class="btn-group right">
        <a href="#"
           class="btn main"
           @click="close"
           ref="closeBtn"
           v-html="'확인'"
        />
      </div>
    </section>
  </Wrapper>
</template>
<script>
import { mapState } from 'vuex';
import { Wrapper } from './index';
import { MODAL_CLOSE } from '@/middleware/store/mutations-type';

const props = state => state.modal.props;

export default {
  components: { Wrapper },
  computed: mapState({ props }),
  created() {
    window.addEventListener('keydown', ({ keyCode }) => {
      if (keyCode === 27) {
        this.close();
      }
    });
  },
  methods: {
    close() {
      this.$store.commit(MODAL_CLOSE);
    },
  },
};
</script>
