<template>
  <div id="app" class="container-fluid">
    <transition-group name="component-fade" mode="out-in">
      <div class="home" key="0">
        <h2>안녕 가계부!</h2>
      </div>
      <nav key="1">
        <router-link class="btn btn-primary" to="/addAccount">지출 입력하기</router-link>
        <router-link class="btn btn-primary" to="/list">이번 달 목록 보기</router-link>
        <router-link class="btn btn-primary" to="/searchList">월별 지출액 보기</router-link>
      </nav>
      <br key="2"/>
      <router-view key="3"/>


    </transition-group>
  </div>
</template>

<script>
  import List from './components/List';
  import AddAccount from './components/AddAccount';
  import MonthlyList from './components/MonthlyList';
  import UpdateAccount from "./components/UpdateAccount";
  import SearchList from "./components/SearchList";

  import VueRouter from 'vue-router';

  const router = new VueRouter({
    mode: 'history',
    routes: [
      {path: '/list', component: List},
      {path: '/addAccount', component: AddAccount},
      {path: '/monthlyList', component: MonthlyList},
      {path: '/updateAccount', component: UpdateAccount},
      {path: '/searchList', component: SearchList}
    ]
  });

  export default {
    name: 'app',
    router,
    components: {List, AddAccount, MonthlyList, UpdateAccount, SearchList},
  }
</script>

<style lang="scss" scoped>

  .component-fade-enter-active, .component-fade-leave-active {
    transition: opacity .5s ease;
  }

  .component-fade-enter, .component-fade-leave-to {
    opacity: 0;
  }

  .container-fluid {
    text-align: center;

    .home {
      color: #00ed7c;
      margin: 20px;
    }

    nav {

      .btn-primary {
        margin-right: 10px;
      }
    }
  }
</style>
