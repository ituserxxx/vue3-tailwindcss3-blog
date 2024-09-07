<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys1" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
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
                Users
              </span>
            </template>
            <a-menu-item key="menuUsersAdd">add</a-menu-item>
            <a-menu-item key="menuUsersList">list</a-menu-item>
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

          <a-sub-menu key="sub4">
            <template #title>
              <span>
                <notification-outlined />
                Tags
              </span>
            </template>
            <a-menu-item key="menuTagsList">list</a-menu-item>
          </a-sub-menu>

        </a-menu>
      </a-layout-sider>

      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
          <component v-if="selectedKeys2.length" :is="compKeyMap[selectedKeys2[0]]" />
        </a-layout-content>
      </a-layout>

    </a-layout>

  </a-layout>
</template>
<script setup>
import { ref, watch } from 'vue';

import ArticleAddComp from './article/add.vue'
import ArticleListComp from './article/list.vue'
import UsersAddComp from './users/add.vue'
import UsersListComp from './users/list.vue'
import LeaveMessageListComp from './leaveMessage/list.vue'
import TagsListComp from './tags/list.vue'

const compKeyMap = {
  menuArticleAdd: ArticleAddComp,
  menuArticleList: ArticleListComp,
  menuUsersAdd: UsersAddComp,
  menuUsersList: UsersListComp,
  menuLeaveMessageList: LeaveMessageListComp,
  menuTagsList: TagsListComp
}
const compSubTitle = {
  
}

const selectedKeys2 = ref([]);
const openKeys = ref([]);
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