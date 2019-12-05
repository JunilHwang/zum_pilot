import {
  OPEN_MODAL,
  CLOSE_MODAL,
  PROPERTY_MODAL,
} from './const';

export default {
  [OPEN_MODAL]: (state, payload) => {
    state.show = payload;
  },
  [CLOSE_MODAL]: (state) => {
    state.show = false;
  },
  [PROPERTY_MODAL]: (state, props) => {
    state.props = props;
  },
};
