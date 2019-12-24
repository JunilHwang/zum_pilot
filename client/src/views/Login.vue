<template>
  <main v-once>
    <form action="" class="fields" @submit.prevent="loginValid">
      <fieldset>
        <legend class="legend">로그인</legend>
        <ul>
          <li>
            <label>
              <span class="fieldsList">아이디</span>
              <input type="text"
                     class="fieldsInput full"
                     placeholder="아이디를 입력해주세요"
                     v-model="id"
                     ref="id"
                     required autofocus />
            </label>
          </li>
          <li>
            <label>
              <span class="fieldsList">비밀번호</span>
              <input type="password"
                     class="fieldsInput full"
                     placeholder="비밀번호를 입력해주세요"
                     v-model="pw"
                     required />
            </label>
          </li>
          <li class="fieldsButtons">
            <button type="submit" class="btn main full">로그인</button>
            <router-link to="/sign-up" class="btn default full">회원가입</router-link>
          </li>
        </ul>
      </fieldset>
    </form>
  </main>
</template>
<script>
import Vue from 'vue';
import { State } from 'vuex-class';
import Component from 'vue-class-component';
import { Watch } from 'vue-property-decorator';
import { NO_MEMBER } from '@/middleware/store/const';
import { USER_SIGN_IN, MODAL_ALERT } from '@/middleware/store/mutations-type';

@Component
export default class Login extends Vue {
  @State user;
  id = '';
  pw = '';

  // permission이 변경되면(=로그인 됨) main 페이지로 이동
  @Watch('user.permission')
  onUserPermission() { this.$router.push('/'); }

  // 사용자 입력 정보(id, pw)에 대한 login validation
  loginValid() {
    const { id, pw } = this;
    this.$store.dispatch(USER_SIGN_IN, { id, pw });
  }

  // login 상태에서는 접근할 수 없음
  mounted() {
    if (this.user.permission !== NO_MEMBER) {
      this.$store.commit(MODAL_ALERT, '비회원만 접근 가능합니다.');
      this.$router.go(-1);
    }
    this.$refs.id.focus();
  }
}
</script>
