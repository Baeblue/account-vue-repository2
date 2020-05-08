<template>
  <div class="container-fluid">
    <div class="search">
      <br/>
      <h3>검색할 년/월을 입력하세요.</h3>

      <div class="form-group">
        <input type="date" class="form-control" id="date" required v-model="date" name="date">
      </div>

      <div class="btn-group">
        <button @click="searchList" class="btn btn-success">검색</button>
      </div>

      <br/>
      <br/>
      <h3>지출 목록</h3>
      <div class="totalArea">
        {{ total }}
      </div>
      <br/>
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
        <tr v-for="(row, index) in account.reverse()" :key="index">
          <td>{{ row.date }}</td>
          <td>{{ row.category }}</td>
          <td>{{ row.content }}</td>
          <td>{{ row.method }}</td>
          <td>{{ row.price }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";

  export default {
    name: "SearchDate",
    data() {
      return {
        date: "",
        account: []
      };
    },
    methods: {
      searchList() {
        ApiSvc.get("/monthly/" + this.date)
          .then(res => {
            this.account = res.data;
          })
          .catch(e => {
            console.log(e);
          });
      }
    }
  };
</script>

<style lang="scss" scoped>

  .search {
    text-align: center;
  }

  .totalArea {
    font-size: 40px;
  }

  table {
    margin: auto;
    font-size: 20px;
    width: 1000px;
  }
</style>
