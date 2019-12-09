import { MODAL_OPEN, MODAL_CLOSE, MODAL_PROPERTY, MODAL_ALERT } from '../mutations-type';

export default {
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
    state.show = 'alert';
    state.props = { message };
  },
};
