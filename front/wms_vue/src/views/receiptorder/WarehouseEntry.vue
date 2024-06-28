<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="入库单号">
      <el-input v-model="form.entryNumber" disabled></el-input>
    </el-form-item>
    <el-form-item label="供应商" required>
      <el-select v-model="form.supplier" placeholder="请选择供应商" @change="handleSupplierChange">
        <el-option v-for="item in suppliers" :key="item" :label="item" :value="item"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="备注">
      <el-input type="textarea" v-model="form.remark"></el-input>
    </el-form-item>
    <el-button type="primary" @click="openItemListDialog">添加零件</el-button>
    <el-table :data="form.parts" style="width: 100%">
      <el-table-column prop="partNumber" label="零件号"></el-table-column>
      <el-table-column prop="quantity" label="入库数量">
        <template #default="scope">
          <el-input-number v-model="scope.row.quantity" :min="1"
            @change="calculateBoxNumber(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="boxNumber" label="箱数">
        <template #default="scope">
          <span>{{ scope.row.boxNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="packageQty" label="包装容量">
        <template #default="scope">
          <div>
            <el-button @click="decreasePackageQty(scope.row)">~</el-button>
            <span>{{ scope.row.packageQty }}</span>
            <el-button @click="increasePackageQty(scope.row)">~</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="SaveForm">保存</el-button>
    <el-button @click="cancelForm">取消</el-button>

    <!-- ItemList 对话框 -->
    <ItemList v-model="isItemListVisible" :supplierId="supplierId" @select="handleItemListSelection"
      @cancel="handleItemListCancel"></ItemList>
  </el-form>
</template>

<script>
import { ref, onMounted } from 'vue';
import { getBox, getNames, getSupplierIdByName, saveForm } from '@/api/inbound.js';
import ItemList from './ItemList.vue';
import { useRouter, useRoute } from 'vue-router';
import { ElMessageBox, ElMessage } from 'element-plus'
import useTabs from '@/utils/useTabs';
const { addTab, removeTab } = useTabs();

export default {
  components: {
    ItemList
  },
  setup() {
    const router = useRouter();
    const route = useRoute();
    const generateEntryNumber = () => {
      const date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const time = date.toTimeString().slice(0, 8).replace(/:/g, '');
      return `R-${year}-${month}-${day}-${time}`;
    };

    const form = ref({
      wmsInbound: {
        id: generateEntryNumber(),
        inboundNo: '',
        inboundTime: '',
        supplier: '',
      },
      entryNumber: generateEntryNumber(),
      supplier: '',
      remark: '',
      parts: []
    });

    const suppliers = ref([]);
    const supplierId = ref('');
    const isItemListVisible = ref(false);

    const fetchSuppliers = async () => {
      try {
        const names = await getNames();
        suppliers.value = names;
      } catch (error) {
        console.error('Error fetching supplier names:', error);
      }
    };

    const handleSupplierChange = async (supplierName) => {
      try {
        supplierId.value = await getSupplierIdByName(supplierName);
        form.value.wmsInbound.supplier = supplierId;
        console.log(form.value.wmsInbound.supplier);
      } catch (error) {
        console.error('Error fetching supplier ID:', error);
      }
    };

    const openItemListDialog = () => {
      isItemListVisible.value = true;
    };

    const handleItemListSelection = async (selectedItemsData) => {
      if (Array.isArray(selectedItemsData.selectedItems)) {
        for (const item of selectedItemsData.selectedItems) {
          const packageQtyOptions = await getBox({ itemNo: item.itemNo, supplier: selectedItemsData.supplier });
          form.value.parts.push({
            partNumber: item.itemNo,
            quantity: selectedItemsData.quantity,
            boxNumber: Math.ceil(selectedItemsData.quantity / packageQtyOptions[0]), // 计算箱数
            packageQty: packageQtyOptions[0] || 0,
            packageQtyOptions
          });
        }
      }
      isItemListVisible.value = false;
    };

    const handleItemListCancel = () => {
      isItemListVisible.value = false;
    };


    const decreaseQuantity = (row) => {
      if (row.quantity > 1) {
        row.quantity -= row.packageQty; // 每次减少一个单位的入库数量
        row.boxNumber = Math.ceil(row.quantity / row.packageQty); // 重新计算箱数
      }
    };

    const increaseQuantity = (row) => {
      row.quantity += row.packageQty; // 每次增加一个单位的入库数量
      row.boxNumber = Math.ceil(row.quantity / row.packageQty); // 重新计算箱数
    };
    const decreasePackageQty = (row) => {
      const currentIndex = row.packageQtyOptions.indexOf(row.packageQty);
      if (currentIndex > 0) {
        row.packageQty = row.packageQtyOptions[currentIndex - 1];
      }
      row.boxNumber = Math.ceil(row.quantity / row.packageQty);
    };

    const increasePackageQty = (row) => {
      const currentIndex = row.packageQtyOptions.indexOf(row.packageQty);
      if (currentIndex < row.packageQtyOptions.length - 1) {
        row.packageQty = row.packageQtyOptions[currentIndex + 1];
      }
      row.boxNumber = Math.ceil(row.quantity / row.packageQty);
    };

    const calculateBoxNumber = (row) => {
      const packageQty = row.packageQty || 1; // 默认包装容量为1，避免除以0错误
      row.boxNumber = Math.ceil(row.quantity / packageQty); // 计算箱数
    };

    const SaveForm = async () => {
      console.log("删除路由名字", route.path)
      // 构造保存数据的格式
      const postData = {
        wmsInbound: form.value.wmsInbound,
        wmsInboundDetailList: form.value.parts.map(part => ({
          id: `D-${new Date().toISOString().slice(0, 10).replace(/-/g, '')}-${Math.floor(Math.random() * 1000000)}`, // 自动生成 ID
          itemNo: part.partNumber,
          container: part.packageQty, // 可以不写
          planQuantity: part.quantity
        }))
      };
      // 调用后端保存数据的函数
      const response = await saveForm(postData);
      console.log('Form data saved:', response);
      ElMessage({
        type: 'success',
        message: '保存成功!',
      });
      //addTab('/receiptorder', '入库单');
      console.log("删除路由名字", route.path)

      //addTab('/receiptorder', '入库单');
      router.replace('/receiptorder').catch(err => {
        console.error('导航到 /receiptorder 失败:', err)
      });
      removeTab(route.path);



    };

    const cancelForm = () => {
      console.log('Cancelled');
      router.replace('/receiptorder').catch(err => {
        console.error('导航到 /receiptorder 失败:', err)
      });
      removeTab(route.path);
    };

    onMounted(() => {
      fetchSuppliers();
    });

    return {
      form,
      suppliers,
      supplierId,
      isItemListVisible,
      openItemListDialog,
      handleSupplierChange,
      handleItemListSelection,
      handleItemListCancel,
      decreasePackageQty,
      increasePackageQty,
      calculateBoxNumber,
      decreaseQuantity,
      increaseQuantity,
      SaveForm,
      cancelForm
    };
  }
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
