module.exports = {
  title: '주제별 영상제공 웹 서비스',
  description: 'Zuminternet Pilot Project',
  themeConfig: {
    search: false,
    logo: '/img/logo.gif',
    nav: [
      { text: '개요', link: '/' },
      { text: '시나리오', link: '/posts/Scenario' },
      { text: '아키텍쳐', link: '/posts/Architecture' },
      { text: '설계', link: '/posts/Design' },
      { text: 'Server', link: '/posts/Server-side' },
      { text: 'Client', link: '/posts/Client-side' },
      { text: 'Reference', link: '/posts/Reference' },
    ],
    sidebar: 'auto',
    smoothScroll: true
  },
  base: '/zum_pilot/',
  markdown: {
    extendMarkdown: md => {
      md.use(require('markdown-it-plantuml'));
    }
  }
}