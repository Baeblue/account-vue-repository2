<template>
  <div id="app">
    <header-component/>

    <transition name="component-fade" mode="out-in">
      <router-view :key="key"/>
    </transition>
  </div>
</template>

<script>
  import List from './components/List';
  import AddAccount from './components/AddAccount';
  import UpdateAccount from "./components/UpdateAccount";
  import HeaderComponent from "./components/HeaderComponent";
  import MonthlyGraph from "./components/MonthlyGraph";
  import EventBus from "@js/EventBus";
  import {EVENT} from "@js/Constants";
  import GraphLine3D from 'vue-graph/src/components/line3d.js';
  import NoteWidget from 'vue-graph/src/widgets/note.js';
  import LegendWidget from 'vue-graph/src/widgets/legends.js';

  import VueRouter from 'vue-router';

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/list', component: List},
      {path: '/addAccount', component: AddAccount},
      {path: '/updateAccount/:id', component: UpdateAccount, props: true},
      {path: '/graph', component: MonthlyGraph},
    ]
  });

  export default {
    name: "app",
    data() {
      return {
        key: 0,
      }
    },
    created() {
      console.log(`[Event 등록] ${EVENT.REFRESH_ROUTER_VIEW}`);
      EventBus.$on(EVENT.REFRESH_ROUTER_VIEW, () => {
        console.log(`[On] ${EVENT.REFRESH_ROUTER_VIEW}`);
        this.key++;
      });
    },
    router,
    components: {
      List,
      AddAccount,
      UpdateAccount,
      HeaderComponent,
      MonthlyGraph,
      GraphLine3D,
      NoteWidget,
      LegendWidget
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
