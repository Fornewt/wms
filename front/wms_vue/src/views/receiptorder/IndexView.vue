<template>
    <div>
        <el-form class="ry_form" :inline="true" label-width="100px" size="medium">
            <el-form-item label="入库状态">
                <el-radio-group v-model="searchForm.inboundStatus">
                    <el-radio-button label="全部" value="all" />
                    <el-radio-button label="未入库" value="0" />
                    <el-radio-button label="部分入库" value="1" />
                    <el-radio-button label="完全入库" value="2" />
                    <el-radio-button label="作废" value="3" />
                </el-radio-group>
            </el-form-item>
            <el-form-item label="入库单号">
                <el-input v-model="searchForm.id" clearable="clearable" placeholder="请输入入库单号" size="small"></el-input>
            </el-form-item>
            <el-form-item label="订单号" prop="inboundNo">
                <el-input v-model="searchForm.inboundNo" clearable="clearable" placeholder="请输入订单号"
                    size="small"></el-input>
            </el-form-item>
            <el-form-item label="供应商" prop="supplierId" style="width: 240px">
                <el-select v-model="searchForm.supplier" placeholder="请选择供应商">
                    <el-option v-for="supplier in suppliers" :key="supplier.id" :label="supplier.name"
                        :value="supplier.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item class="flex_one tr">
                <el-button size="mini" type="primary" @click="handleSearch">
                    <el-icon>
                        <Search />
                    </el-icon>
                    搜索</el-button>
                <el-button size="mini" @click="handleReset">
                    <el-icon>
                        <Refresh />
                    </el-icon>重置</el-button>
            </el-form-item>
        </el-form>
        <el-row class="mb8" :gutter="10" style="margin-left: 15px;">
            <el-col :span="1.5">
                <el-button plain="plain" size="mini" type="primary" @click="goToCreate()">
                    <el-icon>
                        <Plus />
                    </el-icon>创建入库单
                </el-button>
            </el-col>

        </el-row>
        <el-table :data="paginatedData" style="width: 100%;margin: 15px;" @selection-change="handleSelectionChange"
            ref="tableRef">
            <el-table-column type="selection" width="55" />
            <el-table-column label="入库单号" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="供应商" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span style="margin-left: 10px">{{ getSupplierName(scope.row.supplier) }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="入库状态" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-tag :type="scope.row.inboundStatus === 2 ? 'success' : 'info'">
                            {{ statusMap[scope.row.inboundStatus] }}
                        </el-tag>
                    </div>
                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="goToInwarehouse(scope.row.id)">
                        <el-icon>
                            <Edit />
                        </el-icon><span>修改</span>
                    </el-button>
                    <el-button link type="primary" size="small" @click="goToInwarehouse(scope.row.id)">
                        <el-icon>
                            <Files />
                        </el-icon><span>入库</span>
                    </el-button>
                    <el-button link type="primary" size="small" @click.stop="handlePrint(scope.row)">
                        打印入库单
                    </el-button>
                    <el-button link type="primary" size="small" @click.stop="handlePrintItem()">
                        打印看板
                    </el-button>

                    <el-button link type="primary" size="small" @click="handleDelete(scope.row.id)">
                        <el-icon>
                            <Delete />
                        </el-icon><span>删除</span>
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next, jumper" :total="filteredData.length" :page-size="pageSize"
            v-model:current-page="currentPage" @current-change="handlePageChange" style="position: fixed;
    bottom: 0;
    padding: 30px 0 30px 20px;
    display: flex;
    justify-content: center;"> </el-pagination>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import { getInboundList, getSupplierNames, deleteInboundItem, getInboundItemInfo } from '@/api/inbound.js'
import router from "@/router/index.js";
import useTabs from '@/utils/useTabs';
import { ElMessageBox, ElMessage } from 'element-plus'

//const router = useRouter();
//const route = useRoute();
const { addTab } = useTabs();
const tableData = ref([])
const searchForm = ref({
    inboundStatus: 'all',
    id: '',
    inboundNo: '',
    supplier: ''
})
const currentPage = ref(1)
const pageSize = ref(10)

// 状态映射表
const statusMap = {
    0: '未入库',
    1: '部分入库',
    2: '完全入库',
    3: '作废',
}
const suppliers = ref([])
const supplierMap = ref({})
const getList = async () => {
    const response = await getInboundList()
    tableData.value = response.data
    console.log(response.data);
}
const getSuppliers = async () => {
    const response = await getSupplierNames()
    const supplierNames = response.data
    const ids = ['su001', 'su002', 'su003', 'su004', 'su005', 'su006', 'su007', 'su008', 'su009', 'su010']
    suppliers.value = ids.map((id, index) => ({ id, name: supplierNames[index] }))
    supplierMap.value = suppliers.value.reduce((map, supplier) => {
        map[supplier.id] = supplier.name
        return map
    }, {})
}
onMounted(() => {
    getSuppliers()
    getList()
})

const filteredData = computed(() => {
    return tableData.value.filter(item => {
        const matchStatus = searchForm.value.inboundStatus === 'all' || item.inboundStatus.toString() === searchForm.value.inboundStatus
        const matchId = !searchForm.value.id || item.id.includes(searchForm.value.id)
        const matchinboundNo = !searchForm.value.inboundNo || item.inboundNo.includes(searchForm.value.inboundNo)
        const matchSupplier = !searchForm.value.supplier || item.supplier.includes(searchForm.value.supplier)
        return matchStatus && matchId && matchinboundNo && matchSupplier
    })
})

const paginatedData = computed(() => {
    const start = (currentPage.value - 1) * pageSize.value
    const end = start + pageSize.value
    return filteredData.value.slice(start, end)
})

const handleSearch = () => {
    // 手动触发过滤
    currentPage.value = 1 // 重置到第一页
}

const handleReset = () => {
    searchForm.value = {
        inboundStatus: 'all',
        id: '',
        inboundNo: '',
        supplier: ''
    }
    currentPage.value = 1 // 重置到第一页
}

const handlePageChange = (page) => {
    currentPage.value = page
}

const handleDelete = async (id) => {
    ElMessageBox.confirm('此操作将永久删除该入库单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(async () => {
        const response = await deleteInboundItem(id)
        if (response.status === 200) {
            tableData.value = tableData.value.filter(item => item.id !== id)
            ElMessage({
                type: 'success',
                message: '删除成功!',
            });
        } else {
            // 处理错误情况
            console.error('删除失败', response.data)
            ElMessage({
                type: 'error',
                message: '删除失败!',
            });
        }
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '已取消删除',
        });
    });
}


const getSupplierName = (id) => {
    return supplierMap.value[id] || id
}

const handlePrint = (row) => {
    // 将要打印的入库单信息格式化
    const printContent = `
        <h3 align="center" >入库单信息</h3>
        <div style="display:flex">
            <div class="col1" style="text-align: center;margin:auto;">供应商: ${getSupplierName(row.supplier)}</div>
      <div class="col1" style="text-align: center;margin:auto;">订单号:${row.id} </div>
            </div>
        <div style="display:flex">
            <div class="col1" style="text-align: center;margin:auto;">入库单号:${row.id}</div>
      <div class="col1" style="text-align: center;margin:auto;">日期: 2024-06-25</div>
            </div>

        <table border="1" cellspacing="0" align="center" style="width:400px">
      <tr style="width:400px">
        <th>零件名</th>
        <th>零件编号</th>
        <th>器具编码</th>
        <th>箱数</th>
      </tr>
      <tr>
        <td>AK47</td>
        <td>ccc333</td>
        <td>it00014</td>
        <td>40</td>
        </tr>
        <tr>
        <td>AK47</td>
        <td>ccc333</td>
        <td>it00015</td>
        <td>60</td>
        </tr>
        <tr>
        <td>歼-20</td>
        <td>aaa222</td>
        <td>it00010</td>
        <td>100</td>
        </tr>
        <tr>
        <td>AK47</td>
        <td>ccc333</td>
        <td>it00014</td>
        <td>40</td>
        </tr>
    </table>`;
    // 创建一个新的窗口
    const printWindow = window.open('', '', 'width=800,height=600');
    printWindow.document.write('<html><head><title>打印入库单</title>');
    printWindow.document.write('</head><body>');
    printWindow.document.write(printContent);
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();
}
const handlePrintItem = () => {
    const printContent = `
        <h3 align="center" >看板信息</h3>
        <table border="1" cellspacing="0" align="center">
                    <tr>
                        <td colspan="2">零件号</td>
                        <td>it00014</td>
                    </tr>
                    <tr>
                        <td>箱数</td>
                        <td>40</td>
                        <td rowspan="7">
                            <vue-qr :size="80" :margin="0" :auto-color="true" :dot-scale="1" :text="wms" />
                            <img src="https://ts1.cn.mm.bing.net/th/id/R-C.b6359b70784d251138d9dc56b650274b?rik=WiMqW3Pnt%2fImmg&riu=http%3a%2f%2fwww.deepp.com%2fimages%2fcode.png&ehk=pWaMA%2fmVIw943tuInsYTSXgBj%2f3oDTqWhx9Hx3hMtRI%3d&risl=&pid=ImgRaw&r=0" alt="">
                        </td>
                    </tr>
                    <tr>
                        <td>器具型号</td>
                        <td>ccc333</td>
                    </tr>
                    <tr>
                        <td>数量</td>
                        <td>40</td>
                    </tr>
                    <tr>
                        <td>转包状态</td>
                        <td>未转包</td>
                    </tr>
                    <tr>
                        <td>看板号</td>
                        <td>it00014</td>
                    </tr>
                </table>`;
    // 创建一个新的窗口
    const printWindow = window.open('', '', 'width=800,height=600');
    printWindow.document.write('<html><head><title>打印入库单</title>');
    printWindow.document.write('</head><body>');
    printWindow.document.write(printContent);
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();

}

const goToInwarehouse = (inboundId) => {
    router.push({ path: `/InWarehouse/${inboundId}` })
    addTab(`/InWarehouse/${inboundId}`, '修改入库单')
}
const goToCreate = () => {
    //router.push({ path: `/InWarehouse/${id}` })
    addTab('/create', '创建入库单');
}
</script>

<style scoped></style>
