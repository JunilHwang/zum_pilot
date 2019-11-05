module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://0.0.0.0:8080',
      }
    }
  }
}
