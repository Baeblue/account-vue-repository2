<template>
  <div class="container-fluid">
    <div class="addForm">
      <br/>
      <div v-if="!submitted">
        <h3>지출을 입력하세요.</h3>

        <div>
          <b-dropdown
            id="dropdown-1" class="m-2" required
            :text="selectedCategory"
          >
            <b-dropdown-item
              v-for="category in CategoryList" :key="category.id"
              @click="selectCategory(category)"
            >
              {{ category }}
            </b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <input type="text" class="form-control" placeholder="내용 입력" id="content"
                 v-model="account.content"/>
        </div>

        <div>
          <b-dropdown
            id="dropdown-2" class="m-2" required
            :text="selectedMethod"
          >
            <b-dropdown-item
              v-for="method in MethodList" :key="method.id"
              @click="selectMethod(method)"
            >
              {{ method }}
            </b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="price">금액</label>
          <input type="text" class="form-control" placeholder="금액 입력" id="price"
                 v-model="account.price"/>
        </div>

        <button @click="addRow" class="btn btn-success">등록</button>
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
  import EventBus from "@js/EventBus.js";

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
          price: null,
        },
        submitted: false,
        selectedCategory: '항목 선택',
        CategoryList: ['식비', '교통비', '생필품비', '자기계발비', '경조사비', '기타'],

        selectedMethod: '수단 선택',
        MethodList: ['현금', '체크카드', '신용카드', '상품권', '기타'],
      };
    },
    methods: {
      selectCategory(selectedItem) {
        this.selectedCategory = selectedItem;
        this.account.category = this.selectedCategory;
      },
      selectMethod(selectedItem) {
        this.selectedMethod = selectedItem;
        this.account.method = this.selectedMethod;
      },
      addRow() {
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
