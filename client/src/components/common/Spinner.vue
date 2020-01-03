<template>
  <div class="spinner">
    <ClipLoader color="#09F" />
  </div>
</template>
<script>
import Vue from 'vue';
import Component from 'vue-class-component';
import ClipLoader from 'vue-spinner/src/ClipLoader.vue';

const components = { ClipLoader };

@Component({ components })
export default class Spinner extends Vue { }
</script>
