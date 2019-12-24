<template>
  <Wrapper>
    <section class="alert" @keydown.enter="close">
      <h3 class="modalTitle">경고</h3>
      <p v-html="props.message || '경고 메세지'" />
      <div class="btnGroup right">
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
import Vue from 'vue';
import Component from 'vue-class-component';
import { State } from 'vuex-class';
import { Wrapper } from './index';
import { MODAL_CLOSE } from '@/middleware/store/mutations-type';

const components = { Wrapper };

@Component({ components })
export default class Alert extends Vue {
  @State(state => state.modal.props) props;

  // ESC 감지 -> Alert 창 닫기
  created() {
    window.addEventListener('keydown', ({ keyCode }) => {
      if (keyCode === 27) {
        this.close();
      }
    });
  }

  // 창 닫기 method
  close() { this.$store.commit(MODAL_CLOSE); }
}
</script>
