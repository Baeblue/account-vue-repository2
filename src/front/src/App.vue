<template>
  <div id="app">
    <header-component/>

    <transition name="component-fade" mode="out-in">
      <router-view/>
    </transition>
  </div>
</template>

<script>
  import List from './components/List';
  import AddAccount from './components/AddAccount';
  import MonthlyList from './components/MonthlyList';
  import UpdateAccount from "./components/UpdateAccount";
  import SearchList from "./components/SearchList";
  import HeaderComponent from "./components/HeaderComponent";

  import VueRouter from 'vue-router';

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/list', component: List},
      {path: '/addAccount', component: AddAccount},
      {path: '/monthlyList', component: MonthlyList},
      {path: '/updateAccount/:id', component: UpdateAccount, props: true},
      {path: '/searchList', component: SearchList}
    ]
  });

  export default {
    name: "app",
    router,
    components: {
      List,
      AddAccount,
      MonthlyList,
      UpdateAccount,
      SearchList,
      HeaderComponent
    },
  }
</script>

<style lang="scss" scoped>

  #app {
    height: 900px;
  }

  .component-fade-leave-active {
    opacity: 0;
  }

  .component-fade-enter, .component-fade-leave-to {
    opacity: 0;
  }

  .component-fade-enter-active {
    transition: opacity .5s ease;
  }
</style>
