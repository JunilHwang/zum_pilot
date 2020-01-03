<template>
  <div id="global-layout">
    <component :is="$page.path ? 'Layout' : 'NotFound'" />
    <footer class="siteFooter" v-if="$page.path">
      <div class="container">
        <p class="copyright">
          Copyright &copy; 2019 <strong>zuminternet corp.</strong> All Right Reserved
        </p>
      </div>
    </footer>
  </div>
</template>