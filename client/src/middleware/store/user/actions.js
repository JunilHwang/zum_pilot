import { $get, $post } from '@/helper/http-wrapper';
import { USER_SIGN_IN, USER_SIGN_UP, USER_AUTH, USER_LOGOUT, MODAL_ALERT } from '../mutations-type';
import { router } from '@/middleware';

export default {
  [USER_SIGN_IN]: async ({ commit }, payload) => {
    const result = await $post('/sign-in', payload);
    commit(USER_SIGN_IN, result);
  },
  [USER_SIGN_UP]: async ({ commit }, payload) => {
    await $post('/sign-up', payload);
    commit(MODAL_ALERT, '회원가입이 완료되었습니다.');
    router.push('/sign-in');
  },
  [USER_AUTH]: async ({ state, commit }) => {
    const headers = { 'X-AUTH-TOKEN': state.token };
    const result = await $get('/user', { headers });
    if (result === 'anonymousUser') {
      commit(USER_LOGOUT);
    }
  },
};
