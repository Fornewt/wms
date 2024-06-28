<template>
  <el-table :data="inventoryData" style="width: 100%">
    <el-table-column prop="id" label="ID" width="180"></el-table-column>
    <el-table-column prop="itemNo" label="项目编号" width="180"></el-table-column>
    <el-table-column prop="inventory" label="库存数量" width="180"></el-table-column>
    <el-table-column prop="low" label="最低库存" width="180"></el-table-column>
    <el-table-column prop="high" label="最高库存" width="180"></el-table-column>
  </el-table>
</template>

<script setup>
import { ref } from 'vue';
import { ElTable, ElTableColumn } from 'element-plus';

// 示例数据，实际应用中应从后端接口获取
const inventoryData = ref([
  { id: 'ee', itemNo: 'bbb222', inventory: 98, low: null, high: null },
  { id: 'iv00001', itemNo: 'aaa111', inventory: 116, low: null, high: null },
]);

//   在实际应用中，您应该在这里调用后端接口获取数据
fetch('http://192.168.136.86:8000/wmsInventory/getInventory')
  .then(response => response.json())
  .then(data => {
    inventoryData.value = data;
  })
  .catch(error => {
    console.error('Error fetching inventory data:', error);
  });
</script>

<style scoped>
/* 在这里添加样式 */
</style>