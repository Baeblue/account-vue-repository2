<template>
  <div class="list">
    <div class="header">
      <h3>이번 달 지출 목록</h3>
      <div class="totalArea">{{ total }}</div>
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
      <tr v-for="row in accounts" :key="row.id">
        <td>{{ getCustomizedDate(new Date(row.date)) }}</td>
        <td>{{ row.category }}</td>
        <td>{{ row.content }}</td>
        <td>{{ row.method }}</td>
        <td>{{ row.price }}</td>
        <td>
          <router-link :to="`/updateAccount/${row.id}`">
            <button class="btn btn-primary">수정</button>
          </router-link>
          <button @click="deleteRow(row.id)" class="btn btn-danger">삭제</button>
        </td>
      </tr>
      </tbody>
    </table>
    <router-view @refreshData="getAccounts"></router-view>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";

  export default {
    name: "List",
    data() {
      return {
        accounts: [],
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
            this.accounts = res.data;
            // initialize total value.
            this.total = res.data
              .map(obj => obj.price)
              .reduce((price1, price2) => price1 + price2, 0);
          })
          .catch(e => console.log(e));
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
      },
      deleteRow(id) {
        ApiSvc.delete(`/account/${id}`)
          .then(res => {
            //this.account.splice(id, 1)
            console.log(id);
            console.log(res.data);
            this.getAccounts();
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
  };
</script>

<style lang="scss" scoped>

  .list {
    padding-bottom: 200px;

    .header {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 200px;

      .totalArea {
        font-size: 40px;
      }
    }

    table {
      margin: auto;
      font-size: 20px;
      width: 1000px;

      thead {
        border: 1px solid darkgray;
        border-radius: 30px;

        tr:hover {
          background-color: white;
        }

        tr th {
          text-align: center;
        }
      }

      tr {
        height: 70px;

        td {
          text-align: center;
        }
      }

      tr:nth-child(2n) {
        background-color: rgb(245, 245, 245);
      }

      tr:hover {
        background-color: rgb(240, 240, 240);
      }
    }
  }
</style>
