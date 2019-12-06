<template>
  <main>
    <form action="" class="fields" @submit.prevent="signUp">
      <fieldset>
        <legend class="legend">회원가입</legend>
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
          <li>
            <label>
              <span class="fields__list">이름</span>
              <input type="text"
                     class="fields__input full"
                     placeholder="아이디를 입력해주세요"
                     v-model="name"
                     required />
            </label>
          </li>
          <li class="fields__buttons">
            <button type="submit" class="btn main full">회원가입</button>
            <router-link to="/sign-up" class="btn default full">로그인</router-link>
          </li>
        </ul>
      </fieldset>
    </form>
  </main>
</template>
<script>
import { USER_SIGN_UP } from '@/middleware/store/mutations-type';

export default {
  data() {
    return {
      id: '',
      pw: '',
      name: '',
    };
  },
  mounted() {
    this.idFocus();
  },
  methods: {
    idFocus() {
      this.$refs.id.focus();
    },
    async signUp() {
      const { id, pw, name } = this;
      const { data } = await this.$store.dispatch(USER_SIGN_UP, { id, pw, name });
      if (data.success) {
        alert('회원가입이 완료되었습니다.');
        this.$router.push('/sign-in');
      } else {
        alert('이미 중복된 아이디가 있습니다.');
        this.idFocus();
      }
    },
  },
};
</script>
