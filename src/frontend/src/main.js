import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import {store} from "./store";

import moment from 'moment';
import VueMoment from 'vue-momentjs';

Vue.config.productionTip = false;

Vue.use(VueMoment, moment);

new Vue({
  router,
  vuetify,
  store: store,
  render: h => h(App)
}).$mount('#app')
