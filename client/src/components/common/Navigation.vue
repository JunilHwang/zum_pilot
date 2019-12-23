<template>
  <nav class="site-header__nav">
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
import Vue from 'vue';
import Component from 'vue-class-component';
import { mapState } from 'vuex';
import { PUBLIC, NO_MEMBER, IS_MEMBER } from '@/middleware/store/const';
import { USER_LOGOUT } from '@/middleware/store/mutations-type';

const computed = mapState({ user: state => state.user });

@Component({ computed })
export default class Navigation extends Vue {
  IS_MEMBER = IS_MEMBER;
  menu = [
    { title: '뉴스', url: '/', permission: PUBLIC },
    { title: '음원차트', url: '/chart', permission: PUBLIC },
    { title: '인기영상', url: '/popular', permission: PUBLIC },
    { title: '로그인', url: '/sign-in', permission: NO_MEMBER },
    { title: '회원가입', url: '/sign-up', permission: NO_MEMBER },
    { title: '즐겨찾기', url: '/bookmark', permission: IS_MEMBER },
  ];

  // 로그아웃
  logout() { this.$store.commit(USER_LOGOUT); }

  // 권한 검사
  permCheck(permission) {
    return [PUBLIC, this.user.permission].indexOf(permission) !== -1;
  }
}
</script>
