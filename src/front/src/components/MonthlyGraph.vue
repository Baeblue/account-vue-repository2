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
        :width="1125"
        :height="750"
        :shape="'normal'"
        :axis-min="0"
        :axis-max="400000"
        :axis-full-mode="true"
        :labels="labels"
        :names="names"
        :values="values">
        <note :text="'단위: 원'" :align="'left'"></note>
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
        selectedDate: {
          year: "",
          month: "",
        },
        labels: [],
        names: [ "누적 지출액" ],
        values: [],
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

      // String 아닌 숫자로 하면 경우의 수를 따질 필요가 없음. Calendar CalendarPlugin
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

      //--------------------------------------------------------------------------------------------

      this.getAccountsByDate(_curYear1, _curMonth1-4);
      this.getAccountsByDate(_curYear2, _curMonth2-3);
      this.getAccountsByDate(_curYear3, _curMonth3-2);
      this.getAccountsByDate(_curYear4, _curMonth4-1);
      this.getAccountsByDate(_curYear, _curMonth);
    },
    methods: {
      getAccountsByDate(_curYear, _curMonth) {
        const requestData = {
          year: `${_curYear}`,
          month: `${_curMonth < 10 ? '0':''}${_curMonth}`,
        };

        ApiSvc.post("/monthly", requestData)
          .then(res => {
            this.accounts = res.data;
            this.values.push(this.getTotal(res.data));
          })
          .catch(e => console.log(e));
      },
      getTotal(data) {
        let totalG = data
          .map(obj => obj.price)
          .reduce((price1, price2) => price1 + price2, 0);
        return totalG;
      },
    },
  };
</script>

<style lang="scss" scoped>

  .graph {
      text-align: center;
  }
</style>
