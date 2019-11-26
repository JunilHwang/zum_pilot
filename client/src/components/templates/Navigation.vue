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
    </ul>
  </nav>
</template>
<script>
import { mapState } from 'vuex';
import { PUBLIC, NO_MEMBER, IS_MEMBER } from '@/middleware/store/user/const';

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
        { title: '로그아웃', url: '/logout', permission: IS_MEMBER },
      ],
    };
  },
  methods: {
    permCheck(permission) {
      return [PUBLIC, this.user.permission].indexOf(permission) !== -1;
    },
  },
};
</script>
