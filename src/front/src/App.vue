<template>
  <div id="app" class="container-fluid">
    <transition name="component-fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
  import Home from './components/Home'
  import List from './components/List';
  import AddAccount from './components/AddAccount';
  import MonthlyList from './components/MonthlyList';

  import VueRouter from 'vue-router';

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/', component: Home},
      {path: '/list', component: List},
      {path: '/addAccount', component: AddAccount},
      {path: '/monthlyList', component: MonthlyList}
    ]
  });

  export default {
    name: 'app',
    router,
    components: {Home, List, AddAccount, MonthlyList},
    created() {
      this.$router.push('/');
    },
  }
</script>

<style lang="scss">

  .component-fade-enter-active, .component-fade-leave-active {
    transition: opacity .5s ease;
  }

  .component-fade-enter, .component-fade-leave-to {
    opacity: 0;
  }
</style>
