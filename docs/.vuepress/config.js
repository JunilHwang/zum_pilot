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
      '/',
      '/Result/',
      '/Timeline/',
      '/Architecture/',
      '/Design/',
      {
        title: '클라이언트 사이드',
        children: [
          '/Client/Components/',
          '/Client/Stores/',
          '/Client/Optimization/',
        ]
      },
      '/Server/',
      '/Reference/',
    ],
    smoothScroll: true
  },
  base: '/zum_pilot/',
  markdown: {
    extendMarkdown: md => {
      md.use(require('markdown-it-plantuml'));
      md.use(require('markdown-it-imsize'));
    }
  }
}