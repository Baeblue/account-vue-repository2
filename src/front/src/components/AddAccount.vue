<template>
  <div class="addForm">
    <div v-if="!submitted">
      <h3>지출을 입력하세요.</h3>
      <div class="form-group">
        <label for="category">항목</label>     <!-- dropdown으로 -->
        <input type="text" class="form-control" id="category" required v-model="account.category" name="category">
      </div>

      <div class="form-group">
        <label for="content">내용</label>
        <input type="text" class="form-control" id="content" required v-model="account.content" name="content">
      </div>

      <div class="form-group">
        <label for="method">수단</label>     <!-- dropdown으로 -->
        <input type="text" class="form-control" id="method" required v-model="account.method" name="method">
      </div>

      <div class="form-group">
        <label for="price">금액</label>
        <input type="number" class="form-control" id="price" required v-model="account.price" name="price">
      </div>

      <button v-on:click="addLine" class="btn btn-success">등록</button>
    </div>

    <div v-else>
      <h4>등록되었습니다.</h4>
      <router-link to="/list">
        <button class="btn btn-success" @on:click="addToTotal(account.price)">확인</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import eventBus from "../eventBus";

export default {
  name: "AddAccount",
  data() {
    return {
      account: {
        id: 0,
        category: "",
        content: "",
        method: "",
        price: 0,
      },
      submitted: false
    };
  },
  methods: {
    addToTotal(price) {
      if(this.account.price > 0) {
        //const date = new Date();

        eventBus.$emit('addTotal', price);
      }
    },
    addLine() {
      var data = {
        category: this.account.category,
        content: this.account.content,
        method: this.account.method,
        price: this.account.price
      };

      eventBus
        .post("/account", data)
        .then(response => {
          this.account.id = response.data.id;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        })

      this.submitted = true;
    }
  }
};
</script>

<style lang="scss">
.addForm {
  max-width: 300px;
  margin: auto;
}
</style>
