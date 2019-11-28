<template>
  <main>
    <form action="" class="fields" @submit.prevent="loginCheck">
      <fieldset>
        <legend class="legend">로그인</legend>
        <ul>
          <li>
            <label>
              <span class="fields__list">아이디</span>
              <input type="text"
                     class="fields__input full"
                     placeholder="아이디를 입력해주세요"
                     v-model="id"
                     ref="id"
                     required autofocus />
            </label>
          </li>
          <li>
            <label>
              <span class="fields__list">비밀번호</span>
              <input type="password"
                     class="fields__input full"
                     placeholder="비밀번호를 입력해주세요"
                     v-model="pw"
                     required />
            </label>
          </li>
          <li class="fields__buttons">
            <button type="submit" class="btn main full">로그인</button>
            <router-link to="/sign-up" class="btn default full">회원가입</router-link>
          </li>
        </ul>
      </fieldset>
    </form>
  </main>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_USER, NO_MEMBER } from '@/middleware/store/user/const';

export default {
  computed: mapState(['user']),
  data() {
    return { id: '', pw: '' };
  },
  methods: {
    async loginCheck() {
      const { id, pw } = this;
      await this.$store.dispatch(FETCH_USER, { id, pw });
    },
  },
  mounted() {
    if (this.user.permission !== NO_MEMBER) {
      alert('비회원만 접근 가능합니다.');
      this.$router.go(-1);
    }
    this.$refs.id.focus();
  },
  watch: {
    'user.permission': function () {
      this.$router.push('/');
    },
  },
};
</script>
