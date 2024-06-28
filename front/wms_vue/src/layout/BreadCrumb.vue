<template>
    <el-breadcrumb separator="/"
        style="margin-left: 10px;margin-top: 20px;border-bottom: rgba(168, 168, 168, 0.3) 1px solid;font-size: medium;">
        <el-breadcrumb-item v-for="(item, index) in breadcrumbList" :key="item.path">
            <span class="no-redirect" v-if="index === breadcrumbList.length - 1">{{
                item.name
                }}</span>
            <span class="redirect" v-else @click="handleRedirect(item.path)">{{
                item.name
                }}</span>
        </el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script setup>
import { watch, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
const route = useRoute()
const router = useRouter()
console.log(route.matched);
const breadcrumbList = ref([])

const initBreadcrumbList = () => {
    breadcrumbList.value = route.matched
    console.log(route.matched)
}
const handleRedirect = (path) => {
    router.push(path)
}

watch(
    route,
    () => {
        initBreadcrumbList()
    },
    { deep: true, immediate: true }
)
</script>

<style scoped>
.no-redirect {
    color: #97a8be;
    cursor: text;
}

.redirect {
    color: #666;
    font-weight: 600;
    cursor: pointer;

    &:hover {
        color: #304156;
    }
}
</style>