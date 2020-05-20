<!--<template>-->
<!--  <div class="container">-->
<!--    <div class="Chart">-->
<!--      <h1>Bar chart</h1>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import { Bar } from '../BaseCharts'-->

<!--  export default {-->
<!--    name: "Graph",-->
<!--    data() {-->
<!--      return {-->
<!--        labels: [ 'January', 'February' ],-->
<!--        datasets: [-->
<!--          {-->
<!--            label: 'Create Bar Graph',-->
<!--            backgroundColor: '#f87979',-->
<!--            data: [ 40, 20 ]-->
<!--          }-->
<!--        ]-->
<!--      }-->
<!--    },-->
<!--    extends: Bar,-->
<!--    mounted() {-->
<!--      this.renderChart({-->
<!--        labels: [ 'January', 'February', ],-->
<!--        datasets: [-->
<!--          {-->
<!--            label: 'Create Bar Graph',-->
<!--            backgroundColor: '#f87979',-->
<!--            data: [ 40, 20, ]-->
<!--          }-->
<!--        ]-->
<!--      }, {responsive: true, maintainAspectRatio: false})-->
<!--    },-->
<!--  };-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->

<template>
  <bar-chart></bar-chart>
</template>

<script>
  import BarChart from "./BarChart";

  export default {
    name: "VueChartJS",
    components: {
      BarChart,
    },

  }
</script>

<style scoped>

</style>
