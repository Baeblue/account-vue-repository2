<template>

</template>

<script>
  import { Bar, mixins } from 'vue-chartjs';

  const { reactiveProp } = mixins;

  export default {
    name: "Reactive",
    extends: Bar,
    mixins: [ reactiveProp ],
    data() {
      return {
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              },
              gridLines: {
                display: true
              }
            }],
            xAxes: [{
              gridLine: {
                display: false
              }
            }]
          },
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    mounted() {
      this.renderChart(this.chartData, this.options)
    }
  };
</script>

<style scoped>

</style>
