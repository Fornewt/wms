<template>
  <el-dialog title="添加零件" v-model="dialogVisible" @close="handleClose">
    <div>
      <!-- 搜索输入框 -->
      <el-row :gutter="20" style="margin-bottom: 20px;">
        <el-col :span="8">
          <el-input v-model="searchQuery" placeholder="搜索" clearable @input="handleSearch" />
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="handleSearch">搜索</el-button>
        </el-col>
      </el-row>

      <!-- 选中记录的详细信息行 -->
      <el-row v-if="selectedItems.length === 1" :gutter="20" style="margin-bottom: 20px;">
        <!-- <el-col :span="4">
          <el-tag>供应商:</el-tag>
        </el-col>
        <el-col :span="8">
          <el-input v-model="selectedItemDetails.supplier" placeholder="输入供应商" disabled />
        </el-col> -->
        <el-col :span="4">
          <el-tag>零件名:</el-tag>
        </el-col>
        <el-col :span="8">
          <el-input v-model="selectedItemDetails.itemName" placeholder="输入零件名" disabled />
        </el-col>
        <el-col :span="4">
          <el-tag>数量:</el-tag>
        </el-col>
        <el-col :span="8">
          <el-input v-model="quantity" placeholder="输入数量" type="number" />
        </el-col>
      </el-row>

      <!-- 物料信息表格 -->
      <el-table ref="multipleTable" :data="paginatedItems" style="width: 100%"
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="itemNo" label="零件号" width="180"></el-table-column>
        <el-table-column prop="itemName" label="零件名" width="180"></el-table-column>
        <!-- <el-table-column prop="supplier" label="供应商"></el-table-column> -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="text" @click="selectItem(scope.row.id)">明细</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination v-model:currentPage="currentPage" :page-size="pageSize" :total="filteredItems.length"
                     layout="prev, pager, next" @current-change="handlePageChange" style="margin-top: 20px; text-align: center;" />

      <!-- 确定按钮 -->
      <el-button type="primary" :disabled="selectedItems.length !== 1" @click="confirmSelection"
                 style="margin-top: 20px;">
        确定
      </el-button>
      <!-- 取消按钮 -->
      <el-button @click="cancelSelection" style="margin-top: 20px;">
        取消
      </el-button>

      <!-- 详情对话框 -->
      <el-dialog v-model:visible="dialogVisible" title="Item Details">
        <el-form :model="selectedItem">
          <el-form-item label="Item No">
            <el-input v-model="selectedItem.itemNo" disabled></el-input>
          </el-form-item>
          <el-form-item label="Item Name">
            <el-input v-model="selectedItem.itemName" disabled></el-input>
          </el-form-item>
          <el-form-item label="Supplier">
            <el-input v-model="selectedItem.supplier" disabled></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <el-button @click="dialogVisible = false">关闭</el-button>
        </template>
      </el-dialog>
    </div>
  </el-dialog>
</template>

<script>
import { fetchItems, fetchItemById, getItemsBySupplier, getItems } from '@/api/outbound.js';

export default {
  // props: {
  //   supplierId: {
  //     type: String,
  //     required: true
  //   }
  // },
  data() {
    return {
      items: [],
      selectedItem: null,
      searchQuery: '',
      dialogVisible: false,
      currentPage: 1,
      pageSize: 10,
      selectedItems: [],
      quantity: '',
      selectedItemDetails: {
        // supplier: '',
        itemName: ''
      }
    };
  },
  computed: {
    filteredItems() {
      if (!this.searchQuery) {
        return this.items;
      }
      const query = this.searchQuery.toLowerCase();
      return this.items.filter(item =>
          item.itemNo.toLowerCase().includes(query) ||
          item.itemName.toLowerCase().includes(query)
      );
    },
    paginatedItems() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.filteredItems.slice(start, end);
    }
  },
  methods: {
    fetchItems() {
      fetchItems().then(data => {
        this.items = data;
      });
    },
    // getItemsBySupplier() {
    //   getItemsBySupplier(this.supplierId).then(data => {
    //     this.items = data;
    //   });
    // },
    // 你懂得
    getItems() {
      getItems().then(data => {
        this.items = data;
      });
    },


    fetchItemDetails(itemId) {
      fetchItemById(itemId).then(data => {
        this.selectedItem = data;
        this.dialogVisible = true;
      });
    },
    selectItem(itemId) {
      this.fetchItemDetails(itemId);
    },
    handleSearch() {
      this.currentPage = 1; // Reset to first page after search
    },
    handlePageChange(page) {
      this.currentPage = page;
    },
    handleSelectionChange(val) {
      this.selectedItems = val;
      if (val.length === 1) {
        // this.selectedItemDetails.supplier = val[0].supplier;
        this.selectedItemDetails.itemName = val[0].itemName;
      } else {
        // this.selectedItemDetails.supplier = '';
        this.selectedItemDetails.itemName = '';
      }
    },
    confirmSelection() {
      if (this.selectedItems.length === 1) {
        const dataToSend = {
          selectedItems: this.selectedItems,
          quantity: this.quantity,
          // supplier: this.selectedItemDetails.supplier,
          itemName: this.selectedItemDetails.itemName
        };

        this.$emit('select', dataToSend);
        this.dialogVisible = false;
        this.searchQuery = ''; // Clear search query
        this.quantity = ''; // Clear quantity
        this.selectedItems = [];
        // this.selectedItemDetails.supplier = ''; // Clear supplier
        this.selectedItemDetails.itemName = ''; // Clear item name
      }
    },

    cancelSelection() {
      this.$emit('cancel');
      this.dialogVisible = false;
    },
    handleClose() {
      this.$emit('cancel');
    }
  },
  mounted() {
    // this.getItemsBySupplier();
    this.getItems();
  },
  // watch: {
  //   supplierId() {
  //     this.getItemsBySupplier();
  //   }
  // }
};
</script>
<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-table {
  margin-top: 20px;
}
</style>
