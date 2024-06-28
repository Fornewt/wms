<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="出库单号">
      <el-input v-model="form.entryNumber" disabled></el-input>
    </el-form-item>
    <el-form-item label="客户" required>
      <el-select v-model="form.client" placeholder="请选择客户" @change="handleClientChange">
        <el-option key="A" label="华南理工大学" value="华南理工大学"></el-option>
        <el-option key="B" label="中山大学" value="中山大学"></el-option>
        <el-option key="C" label="华南师范大学" value="华南师范大学"></el-option> </el-select>
    </el-form-item>
    <el-form-item label="备注">
      <el-input type="textarea" v-model="form.remark"></el-input>
    </el-form-item>
    <el-button type="primary" @click="openItemListDialog">添加零件</el-button>
    <el-table :data="form.parts" style="width: 100%">
      <el-table-column prop="partNumber" label="零件号"> </el-table-column>
      <el-table-column prop="quantity" label="出库数量">
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
// import { getBox1, getNames1, getSupplierIdByName, saveForm1 } from '@/api/outbound.js';
import { saveForm1 } from '@/api/outbound.js';
import ItemList from './OutItemList.vue';
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
      return `O-${year}-${month}-${day}-${time}`;
    };

    const form = ref({
      wmsOutbound: {
        id: generateEntryNumber(),
        outboundNo: '',
        outboundTime: '',
        // supplier: '',
        client: ""
      },
      entryNumber: generateEntryNumber(),
      client: "",
      // supplier: '',
      remark: '',
      parts: []
    });

    // const suppliers = ref([]);
    // const supplierId = ref('');
    const isItemListVisible = ref(false);

    // const fetchSuppliers = async () => {
    //   try {
    //     const names = await getNames1();
    //     suppliers.value = names;
    //   } catch (error) {
    //     console.error('Error fetching supplier names:', error);
    //   }
    // };

    const handleClientChange = async (clientName) => {
      try {
        console.log(clientName);
        // supplierId.value = await getSupplierIdByName(supplierName);
        form.value.wmsOutbound.client = clientName;

      } catch (error) {
        console.error('Error fetching client', error);
      }
    };


    const openItemListDialog = () => {
      isItemListVisible.value = true;
    };

    const handleItemListSelection = async (selectedItemsData) => {
      if (Array.isArray(selectedItemsData.selectedItems)) {
        for (const item of selectedItemsData.selectedItems) {
          // const packageQtyOptions = await getBox1({ itemNo: item.itemNo, supplier: selectedItemsData.supplier });
          const packageQtyOptions = [50, 100, 150];// 此处设置了出货物料包装只能选择这几种
          form.value.parts.push({
            partNumber: item.itemNo,
            quantity: selectedItemsData.quantity,
            boxNumber: Math.ceil(selectedItemsData.quantity / packageQtyOptions[0]), // 计算箱数
            // packageQty: packageQtyOptions[0] || 0,
            packageQty: 50,
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
      // 构造保存数据的格式
      const postData = {
        wmsOutbound: form.value.wmsOutbound,
        wmsOutboundDetailList: form.value.parts.map(part => ({
          id: `O-${new Date().toISOString().slice(0, 10).replace(/-/g, '')}-${Math.floor(Math.random() * 1000000)}`, // 自动生成 ID
          itemNo: part.partNumber,
          container: part.packageQty, // 可以不写
          planQuantity: part.quantity
        }))
      };

      // 调用后端保存数据的函数
      const response = await saveForm1(postData);
      console.log('Form data saved:', response);
      ElMessage({
        type: 'success',
        message: '保存成功!',
      });
      //addTab('/receiptorder', '入库单');
      console.log("删除路由名字", route.path)

      //addTab('/receiptorder', '入库单');
      router.replace('/shipmentorder').catch(err => {
        console.error('导航到 /shipmentorder 失败:', err)
      });
      removeTab(route.path);
    };

    const cancelForm = () => {
      console.log('Cancelled');
      router.replace('/shipmentorder').catch(err => {
        console.error('导航到 /shipmentorder 失败:', err)
      });
      removeTab(route.path);
    };

    // onMounted(() => {
    //   fetchSuppliers();
    // });

    return {
      form,
      // suppliers,
      // supplierId,
      handleClientChange,
      isItemListVisible,
      openItemListDialog,
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
