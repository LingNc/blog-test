import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
// console.log(vm)
// var HW = vm.$children[0].$children[0].$data
// console.log(HW)
// var a = {
//   name: 'a',
//   $name: 'b'
// }
// console.log(a)