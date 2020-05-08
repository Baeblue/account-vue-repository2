<template>
  <div class="container-fluid">
    <div class="update">
      <br/>
      <div v-if="!submitted">
        <h3>수정사항을 입력하세요.</h3>

        <div>
          <b-dropdown
            id="dropdown-1" class="m-2" required
            :text="selectedCategory"
          >
            <b-dropdown-item
              v-for="category in CategoryList" v-bind:key="category.id"
              @click="selectCategory(category)"
            >
              {{ category }}
            </b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="content">내용</label>
          <input type="text" class="form-control" placeholder="내용 입력" id="content" v-model="account.content" name="content">
        </div>

        <div>
          <b-dropdown
            id="dropdown-2" class="m-2" required
            :text="selectedMethod"
          >
            <b-dropdown-item
              v-for="method in MethodList" v-bind:key="method.id"
              @click="selectMethod(method)"
            >
              {{ method }}
            </b-dropdown-item>
          </b-dropdown>
        </div>

        <div class="form-group">
          <label for="price">금액</label>
          <input type="text" class="form-control" placeholder="금액 입력" id="price" v-model="account.price" name="price">
        </div>

        <button @click="updateRow(account.id)" class="btn btn-success">등록</button>
      </div>

      <div v-else>
        <h4>수정되었습니다.</h4>
        <router-link to="/list">
          <button class="btn btn-success">확인</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";
  import EventBus from "@js/EventBus";

  export default {
    name: "UpdateAccount",
    //props: ["account"],
    data() {
      return {
        account: {
          id: this.account.id,
          date: this.account.date,
          category: "",
          content: "",
          method: "",
          price: 0,    // this.으로?

        },
        submitted: false,
        selectedCategory: '항목 선택',
        CategoryList: ['식비', '교통비', '생필품비', '자기계발비', '경조사비', '기타'],

        selectedMethod: '수단 선택',
        MethodList: ['현금', '체크카드', '신용카드', '상품권', '기타']
      };
    },
    created() {
      EventBus.$on("use-eventBus", receiveRow => {
        this.account = receiveRow;
        console.log("여기까지 마무리");
      });
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
      updateRow(id) {
        const requestData = {
          category: this.account.category,
          content: this.account.content,
          method: this.account.method,
          price: this.account.price
        };

        ApiSvc.put("/account/" + id, requestData)
          .then(res => {
            this.account = res.data;
            console.log("Success! You edited the account");
          })
          .catch(e => {
            console.log(e)
          });
        this.submitted = true;
      },
    }
  };
</script>

<style lang="scss" scoped>

  .update {
    max-width: 300px;
    margin: auto;
  }
</style>
