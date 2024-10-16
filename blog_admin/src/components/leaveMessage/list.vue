<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="openAddDrawer">Add</a-button>

  <!-- ------------列表------------- -->
  <a-table :columns="columns" :data-source="dataSource" :pagination="pageConfig" @change="handleTableChange">
    <template #headerCell="{ column }">
      {{ column.title }}
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'id'">
        {{ record.id }}
      </template>

      <template v-if="column.dataIndex === 'name'">
        {{ record.name }}
      </template>

      <template v-if="column.dataIndex === 'content'">
        {{ record.content }}
      </template>

      <template v-if="column.dataIndex === 'ip'">
        {{ record.ip }}
      </template>
      <template v-if="column.dataIndex === 'ip_addr'">
        {{ record.ip_addr }}
      </template>
      <template v-if="column.dataIndex === 'create_time'">
        {{ record.create_time }}
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <span>
          <a @click="openDelete(record.id)">Delete</a>
        </span>
      </template>
    </template>
  </a-table>

  <!-- ------------新增抽屉------------- -->
  <a-drawer v-model:open="openAdd" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="35%" title=" Add LeaveMessage" placement="right"
    @close="closeAddComp" :destroyOnClose="true">
    <AddComp />
  </a-drawer>


</template>

<script setup>
import { ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import { ApiLeaveMessageDelete, ApiLeaveMessageList } from '../../api/leaveMessage.js';
import AddComp from './add.vue'


const pageConfig = ref({
  current: 1,
  pageSize: 10,
  total: 50,
  position: ["bottomLeft"],
})
const openAdd = ref(false);


// 表格列
const columns = [
  { title: 'id', dataIndex: 'id', },
  { title: 'name', dataIndex: 'name', },
  { title: 'content', dataIndex: 'content', },
  { title: 'ip', dataIndex: 'ip', },
  { title: 'ipAddr', dataIndex: 'ipAddr', },
  {
    title: 'createTime',
    dataIndex: 'createTime',
    customRender: (text) => formatDate(text),
  },
  { title: 'operation', dataIndex: 'operation', },
];
// 表格数据
const dataSource = ref([]);
const openAddDrawer = () => {
  openAdd.value = true;
};

const openDelete = async (id) => {
  let data = await ApiLeaveMessageDelete({
    id: id
  });

  if (data.code === 0) {
    message.success('This is a success message');
    renderTableList(pageConfig.value.current, pageConfig.value.pageSize);
  } else {
    message.error(data.data.msg);
  }

};

const handleTableChange = (pagination, filters, sorter, { currentDataSource }) => {
  console.log('Pagination:', pagination);
  // console.log('Filters:', filters);
  // console.log('Sorter:', sorter);
  // console.log('Current Data Source:', currentDataSource);
  pageConfig.value = pagination
};
watch(pageConfig, (newValue, oldValue) => {
  console.log('Page configuration changed:', newValue);

  renderTableList(pageConfig.value.current, pageConfig.value.pageSize);
});


// 重置新增抽屉
const closeAddComp = () => {
  console.log(" closeAddComp  ")
  openAdd.value = false
  renderTableList(pageConfig.value.current, pageConfig.value.pageSize);
}


// 渲染表格数据
const renderTableList = async (page, pageSize) => {
  try {
    let data = await ApiLeaveMessageList({
      page: page,
      pageSize: pageSize,
    });
    dataSource.value = data.data.list
    pageConfig.value.total = data.data.total
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};
renderTableList(pageConfig.value.current, pageConfig.value.pageSize);

// 格式化日期的方法
const formatDate = (dateString) => {
  const date = new Date(dateString);
  const yy = String(date.getFullYear()).slice(-2); // 获取年份的后两位
  const mm = String(date.getMonth() + 1).padStart(2, '0'); // 月份（0-11）
  const dd = String(date.getDate()).padStart(2, '0'); // 日期
  const H = String(date.getHours()).padStart(2, '0'); // 小时
  const i = String(date.getMinutes()).padStart(2, '0'); // 分钟
  const s = String(date.getSeconds()).padStart(2, '0'); // 秒
  return `${yy}-${mm}-${dd} ${H}:${i}:${s}`;
};
</script>

<style scoped>
.editable-cell {
  position: relative;

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    margin-top: 4px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>