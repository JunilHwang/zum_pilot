const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://0.0.0.0:8080',
      }
    }
  },
  outputDir: '../server/src/main/resources/static',
  configureWebpack: {
    plugins: [
      new BundleAnalyzerPlugin({
        // analyzerMode: 'disabled'
      })
    ]
  }
}
