<template>
  <div class="headerComponent">
    <div class="home">
      <div>안녕 가계부!</div>
    </div>
    <nav>
      <router-link class="btn" to="/addAccount">지출 입력하기</router-link>
      <router-link class="btn" to="/list">이번 달 목록 보기</router-link>
      <router-link class="btn" to="/searchList">월별 지출액 보기</router-link>
    </nav>
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
    /*width: auto;*/
    height: 360px;
    /*border-bottom: 1px solid gray;*/
    /*background-image: url("../../../front/src/assets/logo.png");*/
    background-image: url("../../../front/src/assets/pig2.jpg");

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

      .btn {
        margin: 0 20px;
        color: white;
        background-color: #ff797d;
        box-shadow: .7px .7px 1px 1px gray;  // x-position y-position blur spread color
        font-size: 22px;
      }

      /*.btn-primary {*/
      /*  margin: 0 20px;*/
      /*  color: rgba(0, 0, 0, 0.84);*/
      /*  background-color: #ff8fa0;*/
      /*  font-size: 22px;*/
      /*}*/

      .btn:hover {
        background-color: gray;
      }
    }
  }
</style>
