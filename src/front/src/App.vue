<template>
  <div id="app" class="container-fluid">
    <transition name="component-fade" mode="out-in">
      <router-view :total="total"></router-view>
    </transition>
  </div>
</template>

<script>
import Home from './components/Home'
import List from './components/List';
import AddAccount from './components/AddAccount';
import MonthlyList from './components/MonthlyList';

import eventBus from './eventBus'
import VueRouter from 'vue-router'

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: Home },
    { path: '/list', component: List },
    { path: '/addAccount', component: AddAccount },
    { path: '/monthlyList', component: MonthlyList }
  ]
});

export default {
  name: 'app',
  router,
  components: { Home, List, AddAccount, MonthlyList },
  data () {
    return {
      total: 0
    }
  },
  created() {
    this.$router.push('/');
    this.initLocalStorage();
  },
  mounted() {
    eventBus.$on('addTotal', (value) => { this.total += value });
    eventBus.$on('refreshLocalStorage', () => { this.refreshLocalStorage() });
  },
  methods: {
    initLocalStorage() {
      if(localStorage.getItem('total')) {
        this.total = JSON.parse(localStorage.getItem('total'));
        console.log('Bring Total');
      } else {
        this.total = 0;
        localStorage.setItem('total', JSON.stringify(this.total));
        console.log('New Total');
      }
    },

    refreshLocalStorage() {
      localStorage.setItem('total', JSON.stringify(this.total));
      console.log('Refresh localStorage.');
    }
  }
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
