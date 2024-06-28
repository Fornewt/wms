<template>
    <el-container>
        <el-aside class="sidebar-container" style="height: 100vh;" width="200px">
            <MenuView @menu-click="handleMenuClick" />
        </el-aside>
        <el-container class="container">
            <el-header>
                <HeaderView />
            </el-header>
            <el-main>
                <el-tabs v-model="activeTab" type="card" closable @tab-click="handleTabClick" @tab-remove="removeTab">
                    <el-tab-pane v-for="tab in tabs" :key="tab.name" :label="tab.label" :name="tab.name"></el-tab-pane>
                </el-tabs>
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import MenuView from './menu/IndexView.vue';
import HeaderView from './HeaderView.vue';
import useTabs from '@/utils/useTabs';

const router = useRouter();
const route = useRoute();

const { tabs, activeTab, handleTabClick, removeTab, addTab } = useTabs();

const handleMenuClick = (menuPath) => {
    if (!tabs.value.some(tab => tab.name === menuPath)) {
        let menuLabel;
        if (menuPath === '/receiptorder') {
            menuLabel = '入库单';
        }
        if (menuPath === '/shipmentorder') {
            menuLabel = '出库单';
        }
        if (menuPath === '/inventory') {
            menuLabel = '库存';
        }
        tabs.value.push({ name: menuPath, label: menuLabel });
    }
    activeTab.value = menuPath;
    router.push(menuPath);
};

watch(route, (newRoute) => {
    activeTab.value = newRoute.path;
});
</script>

<style scoped>
.el-main {
    padding: 0;
    margin-top: 0px;
}

.el-header {
    padding: 0;
    border-bottom: rgba(168, 168, 168, 0.3) 1px solid;
}
</style>