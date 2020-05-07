<template>
  <div class="container-fluid">
    <div class="list">
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
          <th class="edit">편집</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="row in account" :key="row.id">
          <td>{{ getCustomizedDate(new Date(row.date)) }}</td>
          <td>{{ row.category }}</td>
          <td>{{ row.content }}</td>
          <td>{{ row.method }}</td>
          <td>{{ row.price }}</td>
          <td>
            <button></button>
          </td>
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
        row: null,
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
      getCustomizedDate(date) {
        // 2020-04-22T15:00:00.000+0000
        // const date = new Date(2020-04-22T15:00:00.000+0000)
        // date.getMonth()_월, date.getDate()_일자
        const month = date.getMonth() + 1;
        const day = date.getDate();
        let result = '';

        if (month < 10) {
          result += '0' + month;
        }
        if (day < 10) {
          result += '/0' + day;
        } else {
          result += '/' + day;
        }

        return result;
      }
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
