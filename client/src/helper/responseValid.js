import { MODAL_ALERT, USER_LOGOUT } from '@/middleware/store/mutations-type';
import { store } from '@/middleware';

export default function responseValid({ success, msg, code, result }, callback) {
  const { commit } = store;
  if (success === false) {
    commit(MODAL_ALERT, msg);
    if (code === -1) {
      commit(USER_LOGOUT);
    }
  } else {
    callback(result);
  }
}
