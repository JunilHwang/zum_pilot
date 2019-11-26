<template>
  <main>
    <form action="" class="fields">
      <fieldset>
        <legend class="legend">회원가입</legend>
        <ul>
          <li>
            <label>
              <span class="fields__list">아이디</span>
              <input type="text"
                     class="fields__input full"
                     placeholder="아이디를 입력해주세요"
                     v-bind="id"
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
                     v-bind="pw"
                     required />
            </label>
          </li>
          <li>
            <label>
              <span class="fields__list">이름</span>
              <input type="text"
                     class="fields__input full"
                     placeholder="아이디를 입력해주세요"
                     v-bind="name"
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
export default {
  data() {
    return {
      id: '',
      pw: '',
      name: '',
    };
  },
  mounted() {
    this.$refs.id.focus();
  },
};
</script>
