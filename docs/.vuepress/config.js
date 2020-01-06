module.exports = {
  port: 8000,
  title: '주제별 영상제공 웹 서비스',
  description: 'Zuminternet Pilot Project',
  themeConfig: {
    logo: '/img/logo.gif',
    nav: [
      { text: 'Repository', link: 'https://github.com/JunilHwang/zum_pilot/' }
    ],
    sidebar: [
      { title: '개요', path: '/' },
      { title: '결과물 소개', path: '/Result' },
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
  },
  plugins: ['img-lazy']
}