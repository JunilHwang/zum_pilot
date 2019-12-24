<template>
  <article class="videoArticle" @click="selectVideo">
    <figure class="videoArticleThumbnail">
      <img :src="thumbnail" :alt="title" />
    </figure>
    <div class="videoArticleMeta">
      <VideoMeta
        classPrefix="popularMeta"
        v-bind="this.$props"
        :hide="['bookmark']" />
    </div>
  </article>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { VideoMeta } from '@/components/video/index';

const components = { VideoMeta };

@Component({ components })
export default class Article extends Vue {
  @Prop() idx;
  @Prop() thumbnail;
  @Prop() title;
  @Prop() viewCount;
  @Prop() likeCount;
  @Prop() popularPoint;

  // 동영상 선택
  selectVideo(event) { this.$emit('select', event); }
}
</script>
