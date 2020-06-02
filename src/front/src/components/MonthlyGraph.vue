<template>
  <div class="graph">
    <div class="header">
      <div class="subject">
        <br/>
        <h3>월별 지출액 그래프</h3> <!-- total을 월별로 따로 가져와야하나 -->
      </div>
    </div>

    <div class="line-chart">
      <graph-line
        :width="1050"
        :height="700"
        :shape="'normal'"
        :axis-min="0"
        :axis-max="500000"
        :axis-full-mode="true"
        :labels="labels"
        :names="names"
        :values="values">
<!--        <note :text="'월별 지출액 추이'"></note>-->
        <tooltip :names="names" :position="'right'"></tooltip>
        <legends :names="names"></legends>
        <guideline :tooltip-y="true"></guideline>
      </graph-line>
    </div>
  </div>
</template>

<script>
  import ApiSvc from "@js/ApiSvc";



  export default {
    name: "MonthlyGraph",
    data() {
      return {
        accounts: [],
        total: 0,
        selectedDate: {
          year: "",
          month: "",
        },
        labels: [],    // -> 월 v-for 사용 앞뒤로 2달
        // '2020. 02.', '2020. 03.', '2020. 04.', '2020. 05.'
        names: [ "누적 지출액" ],    // -> 누적액
        // -> 월별 누적액이 하나씩. [ 200000, 300000, 250000, 300000 ], v-for
        values: [[]],
      }
    },
    created() {
      // this.label
      // labels array 채워넣기
      const _date = new Date();
      console.log(_date);  // Mon Jun 01 2020 09:42:35 GMT+0900 (대한민국 표준시)

      const _dateN = Number(_date);
      console.log(_dateN);

      const _curYear = _date.getFullYear();
      let _curYear1 = _curYear;
      let _curYear2 = _curYear;
      let _curYear3 = _curYear;
      let _curYear4 = _curYear;
      const _curMonth = _date.getMonth() + 1;
      let _curMonth1 = _curMonth;
      let _curMonth2 = _curMonth;
      let _curMonth3 = _curMonth;
      let _curMonth4 = _curMonth;

      if(_curMonth <= 4) {
        _curYear1 = _curYear -1;
        _curMonth1 = _curMonth + 12;
      }
      if(_curMonth <= 3) {
        _curYear2 = _curYear -1;
        _curMonth2 = _curMonth + 12;
      }
      if(_curMonth <= 2) {
        _curYear3 = _curYear -1;
        _curMonth3 = _curMonth + 12;
      }
      if(_curMonth <= 1) {
        _curYear4 = _curYear -1;
        _curMonth4 = _curMonth + 12;
      }
      this.labels.push(_curYear1 + ". " + `${_curMonth1-4 < 10 ? '0':''}${_curMonth1-4}` + ".");
      this.labels.push(_curYear2 + ". " + `${_curMonth2-3 < 10 ? '0':''}${_curMonth2-3}` + ".");
      this.labels.push(_curYear3 + ". " + `${_curMonth3-2 < 10 ? '0':''}${_curMonth3-2}` + ".");
      this.labels.push(_curYear4 + ". " + `${_curMonth4-1 < 10 ? '0':''}${_curMonth4-1}` + ".");
      this.labels.push(_curYear + ". " + `${_curMonth < 10 ? '0':''}${_curMonth}` + "."); // 5개의 string으로 props로 됨.

      // string으로 말고 숫자로 하면 경우의 수를 따질 필요가 없음.
      // Calendar CalendarPlugin



      this.selectedDate = {
        year: `${_curYear}`,
        month: `${_curMonth < 10 ? '0':''}${_curMonth}`,
      };

      console.log("년도 " + this.selectedDate.year);
      console.log("월 " + this.selectedDate.month);


      function getTotal() {
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
            console.log("여기 들어와?");
            console.log(this.total);
          })
          .catch(e => console.log(e));
        //return this.total;
      }

      // let total1 = getTotal(_curYear1, (_curMonth1-4));
      // console.log("total1 " + total1);
      // let total2 = getTotal(_curYear2, (_curMonth2-3));
      // let total3 = getTotal(_curYear3, (_curMonth3-2));
      // let total4 = getTotal(_curYear4, (_curMonth4-1));
      // let total5 = getTotal(_curYear, _curMonth);

      getTotal();
      this.values.push(this.total + "원");
      this.values.push(this.total + "원");
      this.values.push(this.total + "원");
      this.values.push(this.total + "원");
      this.values.push(this.total + "원");

      //let _total = this.getAccountsByDate();
      //this.values.push(`${_total}`);
      //console.log("여기 " + _total)   // 여기가 문제.


    },
    methods: {
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
            console.log("여기 들어와?");
            console.log(this.total);
          })
          .catch(e => console.log(e));
        return this.total;
      },
    },

  };
</script>

<style scoped>

  .graph {
    text-align: center;
  }
</style>
