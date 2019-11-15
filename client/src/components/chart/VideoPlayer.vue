<template>
  <section class="chart__video">
    <strong v-if="music.selectedVideo === null">음원을 선택해주세요</strong>
    <youtube v-else
             :video-id="music.selectedVideo.videoId"
             player-width="100%"
             player-height="260" />
  </section>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: mapState(['music']),
};
</script>
