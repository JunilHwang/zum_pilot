<template>
  <div id="global-layout">
    <component :is="$page.path ? 'Layout' : 'NotFound'" />
    <footer class="siteFooter">
      <p class="copyright">
        Copyright &copy; 2019 <strong>zuminternet corp.</strong> All Right Reserved
      </p>
    </footer>
  </div>
</template>