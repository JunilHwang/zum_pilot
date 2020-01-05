module.exports = {
  port: 8000,
  title: '주제별 영상제공 웹 서비스',
  description: 'Zuminternet Pilot Project',
  themeConfig: {
    logo: '/img/logo.gif',
    sidebar: [
      { title: '개요', path: '/' },
      { title: '시나리오', path: '/Scenario' },
      { title: '아키텍쳐', path: '/Architecture' },
      { title: '설계', path: '/Design' },
      { title: 'Server', path: '/Server-side' },
      { title: 'Client', path: '/Client-side' },
      { title: 'Reference', path: '/Reference' },
    ],
    smoothScroll: true
  },
  base: '/zum_pilot/',
  markdown: {
    extendMarkdown: md => {
      md.use(require('markdown-it-plantuml'));
    }
  }
}