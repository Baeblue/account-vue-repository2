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
      <router-view @refreshData="refreshList"></router-view>
    </div>
  </div>
</template>

<script>
import eventBus from "../eventBus";

export default {
  name: "List",
  props: [ 'total' ],
  data() {
    return {
      account: [],
      line: null
    }
  },
  methods: {
    retrieveAccounts() {
      eventBus
        .get("/list")
        .then(response => {
          this.account = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveAccounts();
    }
  },
  mounted() {
    this.retrieveAccounts();
  }
};
</script>

<style lang="scss">
.list {
  text-align: center;
}

.totalArea {
  font-size: 40px;
}

table {
  width: 600px;
}
</style>
