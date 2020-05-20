<template>
  <section class="container">
      <router-link to="/chartjs">vue-chartjs</router-link>
      <router-link to="/charts">vue-charts</router-link>
      <router-link to="/chartKick">vue-chartKick</router-link>
    <div class="column">
      <h3>Bar Chart....</h3>
      <bar-chart></bar-chart>
    </div>

  </section>
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
