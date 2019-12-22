import { MODAL_OPEN, MODAL_CLOSE, MODAL_PROPERTY, MODAL_ALERT } from '../mutations-type';
import { store } from '../../index';

export default {
  // 모달창 열기
  [MODAL_OPEN]: (state, payload) => {
    state.show = payload;
  },
  [MODAL_CLOSE]: (state) => {
    state.show = false;
  },
  [MODAL_PROPERTY]: (state, props) => {
    state.props = props;
  },
  [MODAL_ALERT]: (state, message) => {
    const { commit } = store;
    commit(MODAL_OPEN, 'alert');
    commit(MODAL_PROPERTY, { message });
  },
};
