<template>
  <div class="container-fluid">
    <div class="addForm">
      <br/>
      <div v-if="!submitted">
        <h3>지출을 입력하세요.</h3>

        <div>
          <b-dropdown
            id="dropdown-1" class="m-md-2" required
            :text="selectedCategory1"
          >
            <b-dropdown-item @click="selectCategory1('식비')">식비</b-dropdown-item>
            <b-dropdown-item @click="selectCategory1('교통비')">교통비</b-dropdown-item>
            <b-dropdown-item @click="selectCategory1('생필품비')">생필품비</b-dropdown-item>
            <b-dropdown-item @click="selectCategory1('자기계발비')">자기계발비</b-dropdown-item>
            <b-dropdown-item @click="selectCategory1('경조사비')">경조사비</b-dropdown-item>
            <b-dropdown-item @click="selectCategory1('기타')">기타</b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <input type="text" class="form-control" id="content" v-model="account.content" name="content">
        </div>

        <div>
          <b-dropdown
            id="dropdown-1" class="m-2" required
            :text="selectedCategory2"
          >
            <b-dropdown-item
              v-for="category in secondCategoryList"
              @click="selectCategory2(category)"
            >
              {{ category }}
            </b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="price">금액</label>
          <input type="number" class="form-control" id="price" v-model="account.price" name="price">
        </div>

        <button @click="addLine" class="btn btn-success">등록</button>
      </div>

      <div v-else>
        <h4>등록되었습니다.</h4>
        <router-link to="/list">
          <button class="btn btn-success">확인</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";

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
        submitted: false,
        selectedCategory1: '항목 선택',

        selectedCategory2: '지출 선택',
        secondCategoryList: ['현금', '체크카드', '신용카드', '상품권', '기타'],
      };
    },
    methods: {
      selectCategory1(selectedItem) {
        this.selectedCategory1 = selectedItem;
      },
      selectCategory2(selectedItem) {
        this.selectedCategory2 = selectedItem;
      },
      addLine() {
        const requestData = {
          category: this.account.category,
          content: this.account.content,
          method: this.account.method,
          price: this.account.price
        };

        ApiSvc.post("/account", requestData)
          .then(res => {
            this.account.id = res.data.id;
          })
          .catch(e => {
            console.log(e);
          });

        this.submitted = true;
      },
    }
  };
</script>

<style lang="scss" scoped>

  .addForm {
    max-width: 300px;
    margin: auto;
  }
</style>
