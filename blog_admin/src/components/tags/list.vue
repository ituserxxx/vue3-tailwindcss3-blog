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

      <template v-if="column.dataIndex === 'article_sum'">
        {{ record.article_sum }}
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
    :root-style="{ color: 'blue' }" style="color: red" width="35%" title=" Add Tags" placement="right"
    @close="closeAddComp" :destroyOnClose="true">
    <AddComp />
  </a-drawer>
  <!-- ------------修改抽屉------------- -->
  <a-drawer v-model:open="openEdit" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="35%" title=" Edit Tags" placement="right"
    @close="closeEditComp" :destroyOnClose="true">
    <EditComp :id="currUpdateID" />
  </a-drawer>

</template>

<script setup>
import { computed, reactive, ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import { ApiTagsList, ApiTagsDelete } from '../../api/tags.js';
import { useRouter } from 'vue-router';
import AddComp from './add.vue'
import EditComp from './edit.vue'
const router = useRouter();

const pageConfig = ref({
  current: 1,
  pageSize: 10,
  total: 50,
  position: ["bottomLeft"],
})
const openAdd = ref(false);
const openEdit = ref(false);
// 当前更新记录id
const currUpdateID = ref(0);
// 表格列
const columns = [
  { title: 'id', dataIndex: 'id', },
  { title: 'name', dataIndex: 'name', },
  { title: 'article_sum', dataIndex: 'article_sum', },
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
  let data = await ApiTagsDelete({
    id: id
  });
  if (data.code === 0) {
    message.success('This is a success message');
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
}

// 渲染表格数据
const renderTableList = async (page, pageSize) => {
  let data = await ApiTagsList({
    page: page,
    pageSize: pageSize,
  });

  dataSource.value = data.data.list
  pageConfig.value.total = data.data.total
};

renderTableList(pageConfig.value.current, pageConfig.value.pageSize);


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