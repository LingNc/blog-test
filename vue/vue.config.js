const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    allowedHosts: [
      'lingnc.top', // 允许访问的域名地址
      'vue.lingnc.top'   // .是二级域名的通配符
    ],
  }
})
