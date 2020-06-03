<template>
  <div class="headerComponent">
    <div class="home">
      <div>안녕 가계부!</div>
    </div>
    <nav>
      <router-link class="btn" to="/addAccount">지출 입력하기</router-link>
      <div @click="refreshRouterView()">
        <router-link class="btn" to="/list">이번 달 목록 보기</router-link>
      </div>
      <router-link class="btn" to="/graph">그래프 보기</router-link>
    </nav>
  </div>
</template>

<script>
  import EventBus from "@js/EventBus";
  import {EVENT} from "@js/Constants";

  export default {
    name: "HeaderComponent",
    methods: {
      refreshRouterView() {
        console.log(`[Emit] ${EVENT.REFRESH_ROUTER_VIEW}`);
        EventBus.$emit(EVENT.REFRESH_ROUTER_VIEW);
      }
    }
  }
</script>

<style lang="scss" scoped>

  .headerComponent {
    min-width: 1000px;
    height: 360px;
    background-image: url("../../../front/src/assets/pig2.jpg");

    .home {
      display: flex;
      justify-content: center;   // align width
      align-items: center;   // align height
      height: 250px;
      color: rgb(62, 62, 62);
      font-size: 50px;
    }

    nav {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100px;

      .btn {
        margin: 0 20px;
        color: white;
        background-color: #ff797d;
        box-shadow: .7px .7px 1px 1px gray;   // x-position y-position blur spread color
        font-size: 22px;
      }

      .btn:hover {
        background-color: gray;

      }
    }
  }
</style>
