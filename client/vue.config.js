const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const TerserPlugin = require('terser-webpack-plugin');

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
  configureWebpack: config => {
    config.optimization = {
      minimize: true,
      minimizer: [
        new TerserPlugin({
          parallel: 4,
          cache: true
        })
      ]
    }
    if (process.env.NODE_ENV === 'analyze') {
      config.plugins = [new BundleAnalyzerPlugin()];
    }
  },
}
