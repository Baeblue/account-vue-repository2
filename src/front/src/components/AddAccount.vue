<template>
  <div class="container-fluid">
    <div class="addForm">
      <br/>
      <div class="btnArea">
        <router-link to="/">
          <button class="btn">Go Home</button>
        </router-link>
      </div>
      <div class="totalArea">
        {{ total }}
      </div>
      <br/>
      <div v-if="!submitted">
        <h3>지출을 입력하세요.</h3>
<!--        <div class="form-group">-->
<!--          <label for="category">항목</label>     &lt;!&ndash; dropdown으로 &ndash;&gt;-->
<!--          <input type="text" class="form-control" id="category" required v-model="account.category" name="category">-->
<!--        </div>-->
        <div>
          <b-dropdown :on-click='selectCategory' :items='category' id="dropdown-1" text="항목 선택" class="m-md-2" required v-model="account.category">
            <b-dropdown-item>식비</b-dropdown-item>
            <b-dropdown-item>교통비</b-dropdown-item>
            <b-dropdown-item>생필품비</b-dropdown-item>
            <b-dropdown-item>자기계발비</b-dropdown-item>
            <b-dropdown-item>경조사비</b-dropdown-item>
            <b-dropdown-item>기타</b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <input type="text" class="form-control" id="content" required v-model="account.content" name="content">
        </div>

<!--        <div class="form-group">-->
<!--          <label for="method">수단</label>     &lt;!&ndash; dropdown으로 &ndash;&gt;-->
<!--          <input type="text" class="form-control" id="method" required v-model="account.method" name="method">-->
<!--        </div>-->
        <div>
          <b-dropdown id="dropdown-1" text="수단 선택" class="m-2" required v-model="account.method">
            <b-dropdown-item>현금</b-dropdown-item>
            <b-dropdown-item>체크카드</b-dropdown-item>
            <b-dropdown-item>신용카드</b-dropdown-item>
            <b-dropdown-item>상품권</b-dropdown-item>
            <b-dropdown-item>기타</b-dropdown-item>
          </b-dropdown>
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
        date: "",
        category: "",
        content: "",
        method: "",
        price: 0,
      },
      submitted: false
    };
  },
  props: [ 'total' ],
  methods: {
    selectCategory() {

    },
    addToTotal(price) {
      if(this.price > 0) {
        eventBus.$emit('addTotal', price);
        eventBus.$emit('refreshLocalStorage');
      }
      this.refreshInputForm();
    },
    formatDate() {
      eventBus
        .post("/account", data)
        .then(response => {
          this.account.date = response.data.date;
          const d = this.account.date;
          const year = d.getFullYear();
          let month = d.getMonth() + 1;
          let day = d.getDate();

          if(month.length < 2) {
            month = '0' + month;
          }
          if(day.length < 2) {
            day = '0' + day;
          }

          return [year, month, day].join('-');
        })
        .catch(e => {
          console.log(e);
        })
    },
    addLine() {
      const data = {
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

      this.formatDate();
    },
    refreshInputForm() {
      this.price = null;
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
