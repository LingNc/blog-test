<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>{{ title }}</p>
    <div v-html="htm"></div>
    <ul>
      <li v-for="item in 3" :key="item.id" >{{item}}</li>
    </ul>
    <p v-show="flag">dhaisuhdiahbsiudh</p>
    <button v-on:click="change">change</button>
    <!-- <p v-for="item in 5" :key="item.id">this</p> -->
    <p class="tim" :title="time" @mouseover="start" @mouseout="stop">this is time</p>
    <button @click="t">{{ time }}</button>
  </div>
</template>

<script>
export default {
  name: 'HW',
  data() {
    return {
      title: 'HHH',
      htm: '<a href="https://www.baidu.com">百度</a>',
      flag: true,
      time: new Date().getTime(),
      timer: null
    }
  },
  methods: {
    output() {
      console.log('123')
      return 'output'
    },
    change() {
      this.flag = !this.flag
    },
    t() {
      this.time = new Date().getTime()
    },
    start() {
      this.stop()
      this.timer = setInterval(this.t, 1)
    },
    stop() {
      clearInterval(this.timer)
    }
  },
  watch: {
    title(newValue, oldValue) {
      console.log(newValue, oldValue);
    },

  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
