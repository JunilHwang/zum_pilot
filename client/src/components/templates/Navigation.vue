<template>
  <nav class="main-content__header" ref="nav">
    <ul>
      <template v-for="(v, k) in menu">
        <li :key="k" v-if="permCheck(v.permission)">
          <router-link :to="v.url">
            <span v-html="v.title" />
          </router-link>
        </li>
      </template>
      <li v-if="user.permission === IS_MEMBER">
        <a href="#" @click.prevent="logout">
          <span>로그아웃</span>
        </a>
      </li>
    </ul>
  </nav>
</template>
<script>
import { mapState } from 'vuex';
import {
  PUBLIC,
  NO_MEMBER,
  IS_MEMBER,
  LOGOUT,
} from '@/middleware/store/user/const';

export default {
  computed: mapState(['user']),
  data() {
    return {
      menu: [
        { title: '뉴스', url: '/', permission: PUBLIC },
        { title: '음원차트', url: '/chart', permission: PUBLIC },
        { title: '인기영상', url: '/popular', permission: PUBLIC },
        { title: '로그인', url: '/sign-in', permission: NO_MEMBER },
        { title: '회원가입', url: '/sign-up', permission: NO_MEMBER },
      ],
      IS_MEMBER,
    };
  },
  methods: {
    permCheck(permission) {
      return [PUBLIC, this.user.permission].indexOf(permission) !== -1;
    },
    logout() {
      this.$store.commit(LOGOUT);
    },
  },
};
</script>
