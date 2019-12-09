import Vue from 'vue';
import { MODAL_OPEN, MODAL_PROPERTY, USER_LOGOUT } from '@/middleware/store/mutations-type';

export const previewTitle = (str, len) => (
  str.length < len ? str : `${str.substring(0, len)}...`
);

export const windowBottomSensor = (callback) => {
  const { innerHeight, scrollY } = window;
  const { scrollHeight } = document.body;
  if (scrollHeight - innerHeight - scrollY <= 0) {
    callback();
  }
};

export const eventBus = new Vue();

export const responseProxyWithAuth = (commit, { success, errorMessage, error }, callback) => {
  if (success === false) {
    commit(MODAL_OPEN, 'alert');
    commit(MODAL_PROPERTY, { message: errorMessage });
    if (error === 'tokenError') {
      commit(USER_LOGOUT);
    }
  } else {
    callback();
  }
};

export default { previewTitle, windowBottomSensor };
