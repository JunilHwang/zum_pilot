<template>
  <section class="chart__video">
    <strong v-if="music.selected === null">음원을 선택해주세요</strong>
    <youtube v-else
             :video-id="music.selected.videoId"
             player-width="100%"
             player-height="200" />
  </section>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: mapState(['music']),
};
</script>
