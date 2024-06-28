<template>
  <el-form-item label="出库单号">
    {{ parts.length > 0 ? parts[0].outboundId : '' }} <!-- 假设出库单号位于parts数组的第2个对象中 -->
  </el-form-item>
  <el-table :data="parts" style="width: 100%">
    <el-table-column prop="id" label="零件号"></el-table-column>
    <el-table-column prop="itemNo" label="物料编号"></el-table-column>
    <el-table-column prop="realQuantity" label="实际数量">
      <template #default="scope">
        <el-input-number v-model="scope.row.realQuantity" :min="0" :max="scope.row.planQuantity"></el-input-number>
      </template>
    </el-table-column>
    <el-table-column prop="planQuantity" label="计划数量"></el-table-column>
  </el-table>
  <el-button type="primary" @click="saveForm2">保存</el-button>
  <el-button @click="cancelForm2">取消</el-button>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { ElMessageBox, ElMessage } from 'element-plus'
import useTabs from '@/utils/useTabs';
const { addTab, removeTab } = useTabs();
import axios from 'axios';
import { fetchPartsByid, saveform2 } from '@/api/outbound.js'

export default {
  setup() {
    const parts = ref([]);
    const router = useRouter();
    const route = useRoute();

    const fetchPartsById = async (outboundID) => {
      try {
        const response = await fetchPartsByid(outboundID);
        parts.value = response.data;
        console.log('Response from server:', response.data);
      } catch (error) {
        console.error('Error fetching parts:', error);
        parts.value = [];
      }
    };

    // const saveForm2 = async () => {
    //   for (const singleDetail of parts.value) {
    //     try {
    //       const response = await saveform2(singleDetail)
    //       console.log(response.data);

    //       alert(JSON.stringify(response.data));
    //       // console.log(`Data saved successfully for id ${singleDetail.id}`);
    //       // 可以在保存成功后进行一些反馈或页面跳转
    //     } catch (error) {
    //       // console.error(`Error saving data for id ${singleDetail.id}:`, error);
    //       // 可以显示错误消息或者进行其他处理
    //     }
    //   }
    // };
    const saveForm2 = async () => {
      try {
        const savePromises = parts.value.map(singleDetail => saveform2(singleDetail));

        const responses = await Promise.all(savePromises);

        const responseData = responses.map(response => response.data);

        // 所有请求完成后进行alert
        ElMessageBox.alert(JSON.stringify(responseData), '保存成功', {
          confirmButtonText: '确定',
          type: 'success',
          dangerouslyUseHTMLString: true, // 可以使用 HTML 字符串显示内容
          callback: action => {
            // 点击确定按钮后的回调，可以在这里进行页面跳转等操作
            console.log('Data saved successfully for all parts.');
            router.replace('/shipmentorder').catch(err => {
              console.error('导航到 /shipmentorder 失败:', err);
            });
            removeTab(route.path);
          }
        });
      } catch (error) {
        console.error('Error saving data:', error);
        // 可以显示错误消息或者进行其他处理
      }
    };

    const cancelForm2 = () => {
      // 取消编辑，重新获取数据
      fetchPartsById(route.params.outboundID);
    };

    const decreaseRealQty = (row) => {
      if (row.realQuantity > 1) {
        row.realQuantity -= 1; // 每次减少一个单位的实际数量
      }
    };

    const increaseRealQty = (row) => {
      row.realQuantity += 1; // 每次增加一个单位的实际数量
    };

    onMounted(() => {
      // 组件挂载时，从路由参数获取outboundID并获取数据
      fetchPartsById(route.params.outboundID); // Access outboundID from route params
    });

    return {
      parts,
      decreaseRealQty,
      increaseRealQty,
      saveForm2,
      cancelForm2,
    };
  },
};
</script>

<style scoped>
el-table {
  margin-top: 20px;
}
</style>
<style scoped>
el-table {
  margin-top: 20px;
}
</style>


<style scoped>
el-table {
  margin-top: 20px;
}
</style>
