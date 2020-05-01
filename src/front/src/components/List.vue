<template>
  <div class="container-fluid">
    <div class="list">
      <br/>
      <div class="btnArea">
        <router-link to="/">
          <button class="btn">Go Home</button>
        </router-link>
      </div>
      <br/>
      <h3>이번 달 지출 목록</h3>
      <div class="totalArea">
        {{ total }}
      </div>

      <table>
        <thead>
        <tr>
          <th class="date">날짜</th>
          <th class="category">항목</th>
          <th class="content">내용</th>
          <th class="method">수단</th>
          <th class="price">금액</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="line in account" :key="line.id">
          <td>{{ line.date }}</td>
          <td>{{ line.category }}</td>
          <td>{{ line.content }}</td>
          <td>{{ line.method }}</td>
          <td>{{ line.price }}</td>
        </tr>
        </tbody>
      </table>
      <router-view @refreshData="getAccounts"></router-view>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";

  export default {
    name: "List",
    data() {
      return {
        account: [],
        line: null,
        total: 0,
      }
    },
    mounted() {
      this.getAccounts();
    },
    methods: {
      getAccounts() {
        ApiSvc.get("/list")
          .then(res => {
            this.account = res.data;
            // initialize total value.
            this.total = res.data
              .map(obj => obj.price)
              .reduce((price1, price2) => price1 + price2, 0);
          })
          .catch(e => {
            console.log(e);
          });
      },
    },
  };
</script>

<style lang="scss" scoped>
  .list {
    text-align: center;
  }

  .totalArea {
    font-size: 40px;
  }

  table {
    width: 1000px;
  }
</style>
