<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="openAddDrawer">Add</a-button>

  <!-- ------------列表------------- -->
  <a-table :columns="columns" :data-source="dataSource" :pagination="pageConfig" @change="handleTableChange">
    <template #headerCell="{ column }">
      {{ column.title }}
    </template>

    <template #bodyCell="{ column, record, index }">

      <template v-if="column.dataIndex === 'status'">
        <a-switch :checked="record.status === 1" @change="(checked) => handleStatusChange(checked, record.id, index)" />
      </template>

      <template v-else-if="column.dataIndex === 'operation'">
        <span>
          <a @click="openEditDrawer(record.id)">Edit </a>
          <a @click="openDelete(record.id)">Delete</a>
        </span>
      </template>
    </template>
  </a-table>


  <!-- ------------新增抽屉------------- -->
  <a-drawer v-model:open="openAdd" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="100%" title=" Add Article" placement="right"
    :maskClosable="false" @close="closeAddComp" :destroyOnClose="true">
    <AddComp />
  </a-drawer>
  <!-- ------------修改抽屉------------- -->
  <a-drawer v-model:open="openEdit" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="100%" title=" Edit Article" placement="right"
    :maskClosable="false" @close="closeEditComp" :destroyOnClose="true">
    <EditComp :id="currUpdateID" />
  </a-drawer>

</template>

<script setup>
import { ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import { ApiArticlesList, ApiArticlesDelete, ApiArticlesChangeStatus } from '../../api/articles.js';
import diyfunc from '../../utils/diyfunc.js';
import AddComp from './add.vue'
import EditComp from './edit.vue'

import { useStore } from 'vuex';
const store = useStore();
console.log("qg===>", store.state.uid)
const pageConfig = ref({
  current: 1,
  pageSize: 10,
  total: 0,
  position: ["bottomLeft"],
})
const openAdd = ref(false);
const openEdit = ref(false);
const currUpdateID = ref(0);
// 表格列
const columns = [
  { title: 'id', dataIndex: 'id', },
  { title: 'title', dataIndex: 'title', },
  // { title: 'content', dataIndex: 'content', },
  { title: 'commentsSum', dataIndex: 'commentsSum', },
  { title: 'viewSum', dataIndex: 'viewSum', },
  { title: 'createTime', dataIndex: 'createTime', customRender: (text) => diyfunc.formatDate(text), },
  { title: 'updateTime', dataIndex: 'updateTime', customRender: (text) => diyfunc.formatDate(text), },
  { title: 'status', dataIndex: 'status', },
  { title: 'operation', dataIndex: 'operation', },
];
// 表格数据
const dataSource = ref([]);
const openAddDrawer = () => {
  openAdd.value = true;
};
const openEditDrawer = (id) => {
  currUpdateID.value = id;
  openEdit.value = true;
};
const openDelete = async (id) => {
  let data = await ApiArticlesDelete({
    id: id
  });
  if (data.code === 0) {
    message.success('success');
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
// 重置编辑抽屉
const closeEditComp = () => {
  console.log(" closeEditComp  ")
  currUpdateID.value = 0
  openEdit.value = false
  renderTableList(pageConfig.value.current, pageConfig.value.pageSize);
}

// 渲染表格数据
const renderTableList = async (page, pageSize) => {
  try {
    let data = await ApiArticlesList({
      page: page,
      pageSize: pageSize,
    });
    if (data.code == 0) {
      dataSource.value = data.data.list
      pageConfig.value.total = data.data.total
    }
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};
renderTableList(pageConfig.value.current, pageConfig.value.pageSize);

// 单元格开关操作
const handleStatusChange = async (checked, id, index) => {
  // 更新 record.status，根据开关状态进行更新
  console.log(`id= ${id}   checked= ${checked},  index=${index}`);
  //1发布，2草稿
  let data = await ApiArticlesChangeStatus({
    id: id,
    status: checked ? 1 : 2,
  });
  if (data.code == 0) {
    dataSource.value[index].status = checked ? 1 : 0;
    message.success('success');
  }
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