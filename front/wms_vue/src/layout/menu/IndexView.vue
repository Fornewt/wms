<template>

    <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
        :default-active="defaultActive" text-color="#fff" @open="handleOpen" @close="handleClose" style="height: 100%;"
        router @select="handleMenuSelect">
        <el-menu-item index="/" router>
            <el-icon>
                <setting />
            </el-icon>
            <span> 首页</span>
        </el-menu-item>
        <el-sub-menu>
            <template #title>
                <el-icon>
                    <icon-menu />
                </el-icon>
                <span>出入库</span>
            </template>
            <el-menu-item index="/receiptorder">
                <el-icon>
                    <MessageBox />
                </el-icon>
                <span>入库</span>
            </el-menu-item>
            <el-menu-item index="/shipmentorder">
                <el-icon>
                    <MessageBox />
                </el-icon>
                <span>出库</span>
            </el-menu-item>
        </el-sub-menu>

        <el-menu-item>
            <el-icon>
                <setting />
            </el-icon>
            <span> 零件管理</span>
        </el-menu-item>
        <el-menu-item index="/inventory">
            <el-icon>
                <setting />
            </el-icon>
            <span> 库存</span>
        </el-menu-item>
    </el-menu>
</template>

<script setup>

import { ref, defineEmits, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import {
    Document,
    Menu as IconMenu,
    House,
    MessageBox,
    Setting,
} from '@element-plus/icons-vue';

const emits = defineEmits(['menu-click']);

const defaultActive = ref('/receiptorder');
const router = useRouter();
const route = useRoute();
watch(route, () => {
    defaultActive.value = route.path;
});
const handleMenuSelect = (index) => {
    emits('menu-click', index);
};

</script>

<style lang="scss" scoped></style>