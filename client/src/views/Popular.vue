<template>
  <div>
    Popular
  </div>
</template>
<script>
import { mapState } from 'vuex';
import { FETCH_USER } from '@/middleware/store/user/const';

export default {
  computed: mapState(['user']),
  async created() {
    const data = await this.$store.dispatch(FETCH_USER);
    console.log(data);
  },
};
</script>
