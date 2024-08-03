import { createRouter, createWebHistory } from "vue-router";
import AgendaView from "@/views/AgendaView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: AgendaView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
