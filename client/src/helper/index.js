import Vue from 'vue';
import { MODAL_ALERT, USER_LOGOUT } from '@/middleware/store/mutations-type';

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
    commit(MODAL_ALERT, errorMessage);
    if (error === 'tokenError') {
      commit(USER_LOGOUT);
    }
  } else {
    callback();
  }
};

export default { previewTitle, windowBottomSensor };
