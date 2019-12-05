import {
  MODAL_OPEN,
  MODAL_CLOSE,
  MODAL_PROPERTY,
} from './const';

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
};
