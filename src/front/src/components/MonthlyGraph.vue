<template>
  <div class="graph">
    <div class="header">
      <div class="subject">
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
        <note :text="'월별 지출액 추이'"></note>
        <tooltip :names="names" :position="'right'"></tooltip>
        <legends :names="names"></legends>
        <guideline :tooltip-y="true"></guideline>
      </graph-line>
    </div>
  </div>
</template>

<script>
  export default {
    name: "MonthlyGraph",
    data() {
      return {
        labels: [ '2020. 02.', '2020. 03.', '2020. 04.', '2020. 05.' ],    // -> 월
        names: [ "누적 지출액" ],    // -> 누적액
        values: [
          [ 200000, 300000, 250000, 300000 ],                     // -> 월별 누적액이 하나씩.
        ]
      }
    }
  };

  // export default {
  //   name: "MonthlyGraph",
  //   data() {
  //     return {
  //       accounts: [],
  //       total: 0,
  //       selectedDate: {
  //         year: "",
  //         month: "",
  //       },
  //       labels: [ this.year + '/' + this.month ],
  //       names: ['총액'],
  //       values: [ this.total ]
  //     }
  //   },
  // };
</script>

<style scoped>

  .graph {
    text-align: center;
  }
</style>
