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
  import UpdateAccount from "./components/UpdateAccount";
  import HeaderComponent from "./components/HeaderComponent";
  import SearchList from "./components/SearchList";

  import VueRouter from 'vue-router';

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/list', component: List},
      {path: '/addAccount', component: AddAccount},
      {path: '/updateAccount/:id', component: UpdateAccount, props: true},
      {path: '/searchList', component: SearchList},
    ]
  });

  export default {
    name: "app",
    router,
    components: {
      List,
      AddAccount,
      UpdateAccount,
      HeaderComponent,
      SearchList
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
