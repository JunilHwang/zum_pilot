<template>
  <main>
    <form action="" class="fields" @submit.prevent="signUp">
      <fieldset>
        <legend class="legend">회원가입</legend>
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
          <li>
            <label>
              <span class="fieldsList">이름</span>
              <input type="text"
                     class="fieldsInput full"
                     placeholder="아이디를 입력해주세요"
                     v-model="name"
                     required />
            </label>
          </li>
          <li class="fieldsButtons">
            <button type="submit" class="btn main full">회원가입</button>
            <router-link to="/sign-in" class="btn default full">로그인</router-link>
          </li>
        </ul>
      </fieldset>
    </form>
  </main>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { USER_SIGN_UP } from '@/middleware/store/mutations-type';

@Component
export default class Join extends Vue {
  id = '';
  pw = '';
  name = '';

  // 포커싱 처리
  mounted() { this.idFocus(); }
  idFocus() { this.$refs.id.focus(); }

  // 사용자 입력에 대한 sign up validation
  signUp() { this.$store.dispatch(USER_SIGN_UP, this.$data); }
}
</script>
