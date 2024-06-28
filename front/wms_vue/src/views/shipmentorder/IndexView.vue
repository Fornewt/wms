<template>
    <div>
        <el-form class="ry_form" :inline="true" label-width="100px" size="medium">
            <el-form-item label="出库状态">
                <el-radio-group v-model="searchForm.outboundStatus">
                    <el-radio-button label="全部" value="all" />
                    <el-radio-button label="未出库" value="0" />
                    <el-radio-button label="部分出库" value="1" />
                    <el-radio-button label="完全出库" value="2" />
                    <el-radio-button label="作废" value="3" />

                </el-radio-group>
            </el-form-item>
            <el-form-item label="出库单号">
                <el-input v-model="searchForm.id" clearable="clearable" placeholder="请输入出库单号" size="small"></el-input>
            </el-form-item>
            <el-form-item label="订单号" prop="outboundNo">
                <el-input v-model="searchForm.outboundNo" clearable="clearable" placeholder="请输入订单号"
                    size="small"></el-input>
            </el-form-item>
            <el-form-item label="客户" prop="clientId" style="width: 240px">
                <el-select v-model="searchForm.client" placeholder="请选择客户">
                    <el-option :key="0" label="华南理工大学" value="华南理工大学">华南理工大学</el-option>
                    <el-option :key="1" label="中山大学" value="中山大学">中山大学</el-option>
                    <el-option :key="2" label="华南师范大学" value="华南师范大学">华南师范大学</el-option>
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
                <el-button plain="plain" size="mini" type="primary" @click="goToOutcreate()">
                    <el-icon>
                        <Plus />
                    </el-icon>创建出库单
                </el-button>
            </el-col>

        </el-row>
        <el-table :data="paginatedData" style="width: 100%;margin: 15px;" @selection-change="handleSelectionChange"
            ref="tableRef">
            <el-table-column type="selection" width="55" />
            <el-table-column label="出库单号" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="出库类型" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-tag type="success">
                            出库
                        </el-tag>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="客户" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <span style="margin-left: 10px">{{ getclientName(scope.row.client) }}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="出库状态" width="180">
                <template #default="scope">
                    <div style="display: flex; align-items: center">
                        <el-tag :type="scope.row.outboundStatus === 2 ? 'success' : 'info'">
                            {{ statusMap[scope.row.outboundStatus] }}
                        </el-tag>
                    </div>
                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small"
                        @click="goToOutwarehouse(scope.row.id, scope.row.client)">
                        <el-icon>
                            <Edit />
                        </el-icon><span>修改</span>
                    </el-button>
                    <el-button link type="primary" size="small" @click="goToOutwarehouse(scope.row.id)">
                        <el-icon>
                            <Files />
                        </el-icon><span>发货/出库</span>
                    </el-button>
                    <el-button link type="primary" size="small" @click.stop="handlePrint(scope.row)">
                        打印出库单
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
// import { getOutboundList, getclientNames, deleteOutboundItem } from '@/api/outbound.js'
import { getOutboundList, deleteOutboundItem } from '@/api/outbound.js'
import { ElMessageBox, ElMessage } from 'element-plus'
import router from "@/router/index.js";
import useTabs from '@/utils/useTabs';
const { addTab } = useTabs();
const tableData = ref([])
const searchForm = ref({
    outboundStatus: 'all',
    id: '',
    outboundNo: '',
    client: ''
})
const currentPage = ref(1)
const pageSize = ref(10)

// 状态映射表
const statusMap = {
    0: '未出库',
    1: '部分出库',
    2: '完全出库',
    3: '作废',
}
const clients = ref([])
const clientMap = ref({})
const getList = async () => {
    const response = await getOutboundList()
    tableData.value = response.data
    console.log(response.data);
}

onMounted(() => {
    // getclients()
    getList()
})

const filteredData = computed(() => {
    return tableData.value.filter(item => {
        const matchStatus = searchForm.value.outboundStatus === 'all' || item.outboundStatus.toString() === searchForm.value.outboundStatus
        const matchId = !searchForm.value.id || item.id.includes(searchForm.value.id)
        const matchoutboundNo = !searchForm.value.outboundNo || item.outboundNo.includes(searchForm.value.outboundNo)
        const matchclient = !searchForm.value.client || item.client.includes(searchForm.value.client)
        return matchStatus && matchId && matchoutboundNo && matchclient
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
        outboundStatus: 'all',
        id: '',
        outboundNo: '',
        client: ''
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
        const response = await deleteOutboundItem(id)
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



const getclientName = (id) => {
    return clientMap.value[id] || id
}
const handlePrint = (row) => {
    // 将要打印的入库单信息格式化
    const printContent = `
        <h3 align="center" >出库单信息</h3>
        <div style="display:flex">
            <div class="col1" style="text-align: center;margin:auto;">客户: 华南理工大学</div>
      <div class="col1" style="text-align: center;margin:auto;">订单号:${row.id} </div>
            </div>
        <div style="display:flex">
            <div class="col1" style="text-align: center;margin:auto;">出库单号:${row.id}</div>
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


const goToOutwarehouse = (outboundId, client) => {
    router.push({ path: `/OutWarehouse/${outboundId}`, query: { client } })
    addTab(`/OutWarehouse/${outboundId}`, '修改出库单');
}
const goToOutcreate = () => {
    addTab('/OutCreate', '创建出库单');
}
</script>

<style scoped></style>
