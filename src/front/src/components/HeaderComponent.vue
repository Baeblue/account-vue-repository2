<template>
  <div class="headerComponent">
    <div class="home">
      <div>안녕 가계부!</div>
    </div>
    <nav>
      <router-link class="btn btn-primary" to="/addAccount">지출 입력하기</router-link>
      <router-link class="btn btn-primary" to="/list">이번 달 목록 보기</router-link>
      <router-link class="btn btn-primary" to="/searchList">월별 지출액 보기</router-link>
    </nav>
    <br/>
  </div>
</template>

<script>
  export default {
    name: "HeaderComponent"
  }
</script>

<style lang="scss" scoped>

  .headerComponent {
    min-width: 1000px;
    width: auto;
    height: 350px;
    border-bottom: 1px solid gray;
    background-image: url("../../../front/src/assets/logo.png");

    .home {
      display: flex;
      justify-content: center; // align width
      align-items: center; // align height
      height: 250px;
      color: rgb(62, 62, 62);
      font-size: 50px;
    }

    nav {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100px;

      .btn-primary {
        margin: 0 20px;
        color: black;
        background-color: white;
        font-size: 22px;
      }

      .btn-primary:hover {
        background-color: darkgray;
      }
    }
  }
</style>
