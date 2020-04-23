<template>
  <div class="list">
    <div class="btnArea">
      <router-link to="/">
        <button class="btn">Go Home</button>
      </router-link>
    </div>

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
        <tr v-for="line in account.reverse()">
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
</template>

<script>
import VueRouter from 'vue-router'
import eventBus from "../eventBus";

export default {
  name: "List",
  VueRouter,
  props: [ 'total' ],
  data() {
    return {
      account: {
        id: 0,
        category: "",
        content: "",
        method: "",
        price: 0
      },
      list: null
    }
  },
  methods: {
    retrieveAccounts() {
      eventBus
        .get("/list")
        .then(response => {
          this.list = response.data;
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
</style>
