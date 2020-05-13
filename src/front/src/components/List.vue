<template>
  <div class="list">
    <div class="header">
      <div class="subject">
        <h3>이번 달 지출 목록</h3>
        <div class="totalArea">{{ total }} 원</div>
      </div>

      <div class="search">
        <h3>검색할 년/월을 입력하세요.</h3>
        <div class="content">
          <b-dropdown
            id="dropdown-3" class="m-2" required
            :text="selectedYear"
          >
            <b-dropdown-item
              v-for="year in YearList" :key="year.id"
              @click="selectYear(year)"
            >
              {{ year }}
            </b-dropdown-item>
          </b-dropdown>
          <b-dropdown
            id="dropdown-4" class="m-2" required
            :text="selectedMonth"
          >
            <b-dropdown-item
              v-for="month in MonthList" :key="month.id"
              @click="selectMonth(month)"
            >
              {{ month }}
            </b-dropdown-item>
          </b-dropdown>
          <button @click="getAccountsByDate()" class="btn btn-success">검색</button>
        </div>
      </div>
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
        <td>{{ formatPrice(row.price) }}</td>
        <td>
          <router-link :to="`/updateAccount/${row.id}`">
            <button class="btn btn-primary">수정</button>
          </router-link>
          <button @click="deleteRow(row.id)" class="btn btn-danger">삭제</button>
        </td>
      </tr>
      </tbody>
    </table>
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
        selectedDate: {
          year: "",
          month: "",
        },
        selectedYear: '년도 선택',
        YearList: ['2020', '2021', '2022', '2023', '2024', '2025'],

        selectedMonth: '월 선택',
        MonthList: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
      }
    },
    mounted() {
      this.getAccounts();
    },
    methods: {
      selectYear(selectedItem) {
        this.selectedYear = selectedItem;
        this.selectedDate.year = this.selectedYear;
      },
      selectMonth(selectedItem) {
        this.selectedMonth = selectedItem;
        this.selectedDate.month = this.selectedMonth;
      },
      getAccounts() {
        ApiSvc.get("/list")
          .then(res => {
            this.accounts = res.data;
            // initialize total value.
            this.total = res.data
              .map(obj => obj.price)
              .reduce((price1, price2) => price1 + price2, 0)
              .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
          })
          .catch(e => console.log(e));
      },
      getAccountsByDate() {
        const requestData = {
          year: this.selectedDate.year,
          month: this.selectedDate.month
        };

        ApiSvc.post("/monthly", requestData)
          .then(res => {
            this.accounts = res.data;
            // initialize total value.
            this.total = res.data
              .map(obj => obj.price)
              .reduce((price1, price2) => price1 + price2, 0)
              .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
      formatPrice(price) {
        let formatPrice = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

        return formatPrice;
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
      width: 1000px;
      margin: 0 auto;
      display: flex;
      align-items: center;
      justify-content: space-between;

      .subject {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 200px;

        .totalArea {
          font-size: 40px;
        }
      }

      .search {

        .content {
          display: flex;
          justify-content: space-between;

          .month {
            width: 300px;      //width: 73%;
            padding: 6px 0;
            font-size: 20px;
          }

          .btn {
            width: 24%;
            height: 50px;
            font-size: 20px;
          }
        }
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
