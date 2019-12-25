<template>
  <v-app id="app">
    <router-view/>
  </v-app>
</template>

<script>
  export default {
    name: 'App',
    data () {
      return {
        image: require('./assets/background.png'),
      }
    }
  }
</script>

<style lang="scss">
  #app {
    font-family: 'rose';
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    background-image: url('./assets/logo.png');
    background-color: #009999;
  }
</style>