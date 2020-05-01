<template>
  <div class="container-fluid">
    <div class="home">
      <h2>안녕 가계부!</h2>
    </div>
    <nav>
      <router-link class="btn btn-primary" to="/addAccount">지출 입력하기</router-link>
      <router-link class="btn btn-primary" to="/list">이번 달 목록 보기</router-link>
      <router-link class="btn btn-primary" to="/monthlyList">월별 지출액 보기</router-link>
    </nav>
    <br/>
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: "Home"
  }
</script>

<style lang="scss" scoped>

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
