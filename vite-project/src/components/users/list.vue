<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="openAddUser">Add</a-button>

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

      <template v-if="column.dataIndex === 'passwd'">
        {{ record.passwd }}
      </template>
      <template v-if="column.dataIndex === 'status'">
        <a-switch :checked="record.status === 1" @change="(checked) => handleStatusChange(checked, record.id)" />
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <span>
          <a @click="openEditUser(record.id)">Edit </a>
          <a @click="openDeleteUser(record.id)">Delete</a>
        </span>
      </template>
    </template>
  </a-table>

  <!-- ------------新增抽屉------------- -->
  <a-drawer v-model:open="openAdd" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="35%" title=" Add User" placement="right"
    @close="closeAddComp" :destroyOnClose="true">
    <UsersAddComp />
  </a-drawer>
  <!-- ------------修改抽屉------------- -->
  <a-drawer v-model:open="openEdit" class="custom-class" root-class-name="root-class-name"
    :root-style="{ color: 'blue' }" style="color: red" width="35%" title=" Edit User" placement="right"
    @close="closeEditComp" :destroyOnClose="true">
    <UsersEditComp :id="currUpdateID" />
  </a-drawer>

</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { message } from 'ant-design-vue';
import { cloneDeep } from 'lodash-es';
import { ApiUserList, ApiUserDelete } from '../../api/blog.js';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import UsersAddComp from './add.vue'
import UsersEditComp from './edit.vue'
const router = useRouter();
const store = useStore();

const openAdd = ref(false);
const openEdit = ref(false);
const currUpdateID = ref(0);

const openAddUser = () => {
  openAdd.value = true;
};
const openEditUser = (id) => {
  currUpdateID.value = id;
  openEdit.value = true;
};
const openDeleteUser = async (id) => {
  let data = await ApiUserDelete({
    id: id
  });
  console.log(`ApiUserDelete= ${data}`);
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
  { title: 'passwd', dataIndex: 'passwd', },
  { title: 'status', dataIndex: 'status', },
  { title: 'operation', dataIndex: 'operation', },
];
// 表格数据
const dataSource = ref([]);
// 渲染表格数据
const renderUserList = async () => {
  try {
    let data = await ApiUserList({
      page: 1
    });
    dataSource.value = data.data.list
  } catch (error) {
    console.error('Error fetching the list:', error);
  }
};
renderUserList();

// 单元格开关操作
const handleStatusChange = (checked, id) => {
  // 更新 record.status，根据开关状态进行更新
  console.log(`id= ${id}   checked= ${checked}`);
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