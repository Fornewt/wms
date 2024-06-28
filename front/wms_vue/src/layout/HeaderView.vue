<template>
    <div>
        <div style="display: flex;line-height: 60px;">
            <Breadcrumb />
            <div style="flex: 1;text-align: center;font-size: 34px;">
                <span>仓库管理系统</span>
            </div>
            <el-dropdown>
                <span class="el-dropdown-link">
                    Admin
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <el-dropdown-item>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>

        </div>


    </div>

</template>

<script setup>
import { ArrowDown } from '@element-plus/icons-vue'
import Breadcrumb from '@/layout/BreadCrumb.vue'
</script>

<style scoped>
.el-dropdown-link {
    cursor: pointer;
    display: flex;
    align-items: center;
    margin-right: 15px;
}
</style>