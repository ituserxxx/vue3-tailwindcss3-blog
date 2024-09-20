<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="handleAdd">Add</a-button>
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

      <template v-else-if="column.dataIndex === 'operation'">
        <span>
          <a>Edit </a>
          <a>Delete</a>
        </span>
      </template>
    </template>
  </a-table>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { cloneDeep } from 'lodash-es';
import { ApiUserList } from '../../api/blog.js';
import { useRouter } from 'vue-router';
const router = useRouter();
const columns = [
  { title: 'id', dataIndex: 'id', },
  { title: 'name', dataIndex: 'name', },
  { title: 'passwd', dataIndex: 'passwd', },
  { title: 'operation', dataIndex: 'operation', },
];
const dataSource = ref([
  // {
  //   id: 0,
  //   name: 'xxx0',
  //   passwd: 'sssss',
  // },
  // {
  //   key: 1,
  //   name: 'xxx1',
  //   passwd: 'sssss',
  // },
]);

const Init = async () => {
  renderUserList();
}

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
const handleAdd = () => {
  router.push('/usersAdd');
};
Init()

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