<template>
  <div class="graph">
    <div class="header">
      <div class="subject">
        <br/>
        <h3>월별 지출액 그래프</h3>
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
        graphDate: {
          graphYear: "",
          graphMonth: "",
        },
        labels: [],
        names: [ "누적 지출액" ],   // 왜 안 나오지
        totalList: [],
        values: [
          []
        ],
      }
    },
    created() {

      const _date = new Date();

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
      this.labels.push(_curYear + ". " + `${_curMonth < 10 ? '0':''}${_curMonth}` + ".");

      // String 으로 말고 숫자로 하면 경우의 수를 따질 필요가 없음.
      // Calendar CalendarPlugin
      //------------------------------------------------------------------------------------------------

      let _total1 = this.dateMatch(_curYear1, _curMonth1-4);
      let _total2 = this.dateMatch(_curYear2, _curMonth2-3);
      let _total3 = this.dateMatch(_curYear3, _curMonth3-2);
      let _total4 = this.dateMatch(_curYear4, _curMonth4-1);
      let _total = this.dateMatch(_curYear, _curMonth);

      console.log("_total1값을 알아보자 " + _total1);
      this.values.push(_total1);
      console.log("_total2값을 알아보자 " + _total1);
      this.values.push(_total2);
      console.log("_total3값을 알아보자 " + _total1);
      this.values.push(_total3);
      console.log("_total4값을 알아보자 " + _total1);
      this.values.push(_total4);
      console.log("_total값을 알아보자 " + _total1);
      this.values.push(_total);

    },
    methods: {
      dateMatch(_curYear, _curMonth) {
        this.graphDate = {
          graphYear: `${_curYear}`,
          graphMonth: `${_curMonth < 10 ? '0':''}${_curMonth}`,
        };
        this.getAccountsByDate();
      },
      getAccountsByDate() {
        console.log(this.graphDate.graphYear + ". " + this.graphDate.graphMonth);
        const requestData = {
          graphYear: this.graphDate.graphYear,
          graphMonth: this.graphDate.graphMonth,
        };

        ApiSvc.post("/getTotal", requestData)
          .then(res => {
            this.accounts = res.data;
            this.getTotal(res.data);
          })
          .catch(e => console.log(e));

        console.log("getAccountsByDate() 실행 끝");

      },
      getTotal(data) {
        console.log("getTotal(data) 실행");
        let totalG = data
          .map(obj => obj.price)
          .reduce((price1, price2) => price1 + price2, 0)
          .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        console.log("totalG: " + totalG);
        return totalG;
      },
    },
  };
</script>

<style scoped>

  .graph {
    text-align: center;
  }
</style>
