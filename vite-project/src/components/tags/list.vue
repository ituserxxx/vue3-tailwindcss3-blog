<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="openAddDrawer">Add</a-button>

  <!-- ------------列表------------- -->
  <a-table :columns="columns" :data-source="dataSource">
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

      <template v-if="column.dataIndex === 'num'">
        {{ record.num }}
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
import { computed, reactive, ref } from 'vue';
import { message } from 'ant-design-vue';
import { ApiTagsList, ApiTagsDelete } from '../../api/tags.js';
import { useRouter } from 'vue-router';
import AddComp from './add.vue'
import EditComp from './edit.vue'
const router = useRouter();


const openAdd = ref(false);
const openEdit = ref(false);
const currUpdateID = ref(0);

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
// 重置新增抽屉
const closeAddComp = () => {
  console.log(" closeAddComp  ")
  openAdd.value = false
  renderTableList();
}
// 重置编辑抽屉
const closeEditComp = () => {
  console.log(" closeEditComp  ")
  currUpdateID.value = 0
  openEdit.value = false
}
// 表格列
const columns = [
  { title: 'id', dataIndex: 'id', },
  { title: 'name', dataIndex: 'name', },
  { title: 'num', dataIndex: 'num', },
  { title: 'operation', dataIndex: 'operation', },
];
// 表格数据
const dataSource = ref([]);
// 渲染表格数据
const renderTableList = async () => {
  try {
    let data = await ApiTagsList({
      page: 1
    });
    dataSource.value = data.data.list
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};
renderTableList();
 



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