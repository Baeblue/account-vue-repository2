<template>
  <div class="updateAccount">
    <div class="update">
      <div v-if="!submitted">
        <h3>수정사항을 입력하세요.</h3>

        <div class="inputArea">
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

          <input type="text" class="form-control" placeholder="내용 입력" id="content"
                   v-model="account.content"/>
        </div>

        <div class="inputArea">
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

          <input type="text" class="form-control" placeholder="금액 입력" id="price"
                   v-model="account.price"/>
        </div>

        <button @click="updateRow(account.id)" class="btn btn-success">등록</button>
<!--        <router-link to="/list">-->
        <button @click="goBack()" class="btn btn-success">돌아가기</button>    <!-- 그냥 이전 화면으로 -->
<!--        </router-link>-->
      </div>

      <div v-else>
        <h4>수정되었습니다.</h4>
        <br/>
<!--        <router-link to="/list">-->
          <button @click="goBack()" class="btn btn-success">확인</button>   <!-- 다른 달 수정 시 그 달의 리스트로 -->
<!--        </router-link>-->
      </div>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc.js";
  import EventBus from "@js/EventBus";

  export default {
    name: "UpdateAccount",
    props: {
      id: 0,
    },
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
        selectedCategory: '항목 선택',
        CategoryList: ['식비', '교통비', '생필품비', '자기계발비', '경조사비', '기타'],

        selectedMethod: '수단 선택',
        MethodList: ['현금', '체크카드', '신용카드', '상품권', '기타']
      };
    },
    created() {
      this.getAccount(this.id);
    },
    methods: {
      goBack() {
        //window.history.length > 1 ?
          this.$router.go(-1);
          //: this.$router.push('/')
        // EventBus.$on("use-eventBus", accounts => {
        //   this.accounts = accounts;
        // });
      },
      getAccount(id) {
        ApiSvc.get(`/account/${id}`)
          .then(res => {
            this.account = res.data;
            this.selectedCategory = this.account.category;
            this.selectedMethod = this.account.method;
          })
          .catch(e => console.log(e));
      },
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

        ApiSvc.put(`/account/${id}`, requestData)
          .then(res => {
            this.account = res.data;
            console.log("Success! You edited the account.");
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

  .updateAccount {

    .update {
      display: flex;
      justify-content: center;   // 화면 중앙
      text-align: center;
      max-width: 500px;
      margin: 50px auto;

      div {

        .inputArea {
          width: 500px;
          display: flex;   // 옆으로 나란히
          align-items: center; // 위아래 중앙
          margin: 30px 0;
        }

        .btn-success {
          width: 100px;
          font-size: 18px;
          margin: 0px 5px;
        }
      }
    }
  }
</style>
