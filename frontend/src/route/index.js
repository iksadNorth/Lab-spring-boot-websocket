import { createWebHistory, createRouter } from 'vue-router';

import home from '@/components/view_home.vue'

const routes = [
  { path: '/', component: home, name: 'Home' },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});

export const registerCompoent = {
  install(Vue) {
      Vue.component("view_home", home);
  }
};
