<template>
  <a-layout>
    <!-- <a-layout-header class="header">
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys1" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header> -->


    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu v-model:selectedKeys="selectedKeys2" v-model:openKeys="openKeys" mode="inline"
          :style="{ height: '100%', borderRight: 0 }">
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                <user-outlined />
                Article
              </span>
            </template>
            <a-menu-item key="menuArticleAdd">add</a-menu-item>
            <a-menu-item key="menuArticleList">list</a-menu-item>
          </a-sub-menu>

          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <laptop-outlined />
                Admin
              </span>
            </template>
            <a-menu-item key="menuAdminAdd">add</a-menu-item>
            <a-menu-item key="menuAdminList">list</a-menu-item>
 \
          </a-sub-menu>

          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <notification-outlined />
                Leave Message
              </span>
            </template>
            <a-menu-item key="menuLeaveMessageList">list</a-menu-item>

          </a-sub-menu>

        </a-menu>
      </a-layout-sider>

      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
          v-if="selectedKeys2 == menuArticleAdd">
          <ArticleAddComp />
        </a-layout-content>

        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
          v-if="selectedKeys2 == menuArticleList">
          <ArticleListComp />
        </a-layout-content>


        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
          v-if="selectedKeys2 == menuAdminAdd">
          <AdminAddComp />
        </a-layout-content>
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
          v-if="selectedKeys2 == menuAdminList">
          <AdminListComp />
        </a-layout-content>
      </a-layout>
    </a-layout>

  </a-layout>
</template>
<script setup>
import ArticleAddComp from './article/add.vue'
import ArticleListComp from './article/list.vue'
import AdminAddComp from './admin/add.vue'
import AdminListComp from './admin/list.vue'

import { ref } from 'vue';

// const selectedKeys1 = ref([1]);
const selectedKeys2 = ref(['2']);
const openKeys = ref(['sub1']);
</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);

}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>