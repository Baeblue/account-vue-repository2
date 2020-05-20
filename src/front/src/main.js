import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import DateDropdown from 'vue-date-dropdown';
import DatePicker from 'vue2-datepicker';
import VueGraph from 'vue-graph';

Vue.config.devtools = true;
Vue.config.productionTip = false;
Vue.use(VueRouter);
Vue.use(BootstrapVue);
Vue.use(DateDropdown);
Vue.use(DatePicker);
Vue.use(VueGraph);

new Vue({
  VueRouter,
  render: h => h(App)
}).$mount("#app");
