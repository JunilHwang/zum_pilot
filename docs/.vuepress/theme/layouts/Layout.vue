<template>
  <div id="global-layout">
    <ParentLayout />
    <footer class="siteFooter">
      <p class="copyright">
        Copyright &copy; 2019 <strong>zuminternet corp.</strong> All Right Reserved
      </p>
    </footer>
  </div>
</template>
<script>
import ParentLayout from '@parent-theme/layouts/Layout.vue'
export default {
  name: 'Layout',
  components: { ParentLayout }
}
</script>