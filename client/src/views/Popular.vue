<template>
  <div>
    Popular
  </div>
</template>
<script>
import { mapState } from 'vuex';
import { USER_FETCH } from '@/middleware/store/mutations-type';

export default {
  computed: mapState(['user']),
  async created() {
    const data = await this.$store.dispatch(USER_FETCH);
    console.log(data);
  },
};
</script>
