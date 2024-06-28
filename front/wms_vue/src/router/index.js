import { createRouter, createWebHistory } from "vue-router";
import InventoryTable from '../views/inventory/InventoryTable.vue'; // 导入您的库存表格组件
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "首页",
      component: () => import("@/layout/IndexView.vue"),
      redirect: "/receiptorder",
      children: [
        {
          path: "/receiptorder",
          name: "入库单",

          component: () => import("@/views/receiptorder/IndexView.vue"),
        },
        {
          path: "/shipmentorder",
          name: "出库单",

          component: () => import("@/views/shipmentorder/IndexView.vue"),
        },
        {
          path: "/create",
          name: "创建入库单",
          component: () => import("@/views/receiptorder/WarehouseEntry.vue"),
        },
        {
          path: "InWarehouse/:inboundID",
          name: "入库",
          component: () => import("@/views/receiptorder/InWarehouse.vue"),
        },
        {
          path: "OutWarehouse/:outboundID",
          name: "出库",
          component: () => import("@/views/shipmentorder/OutWarehouse.vue"),
        },
        {
          path: "OutCreate",
          name: "创建出库单",
          component: () =>
            import("@/views/shipmentorder/OutWarehouseEntry.vue"),
        },
        {
          path: '/inventory',
          name: '库存',
          component: InventoryTable
        }
      ],
    },
    
  ],
});

export default router;
