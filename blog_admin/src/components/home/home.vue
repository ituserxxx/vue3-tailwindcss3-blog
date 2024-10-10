<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <!--<a-menu v-model:selectedKeys="selectedKeys1" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
         <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu> -->
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu v-model:selectedKeys="selectedKeys2" v-model:openKeys="openKeys" mode="inline"
          :style="{ height: '100%', borderRight: 0 }">

          <a-sub-menu v-for=" itemMenu in compSubTitle" :key=itemMenu.menuKey>
            <template #title>
              <span>
                <!-- <user-outlined /> -->
                {{ itemMenu.title }}
              </span>
            </template>
            <a-menu-item v-for="sub in itemMenu.sub" :key=sub.k>{{ sub.w }}</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>

      <a-layout style="padding: 0 24px 24px">
        <!-- <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb> -->
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
          <component v-if="selectedKeys2.length" :is="compKeyMap[selectedKeys2[0]]" />
        </a-layout-content>
      </a-layout>

    </a-layout>
  </a-layout>
</template>
<script setup>

import { ref } from 'vue';

import ArticleListComp from '../articles/list.vue'
import UsersListComp from '../users/list.vue'
import LeaveMessageListComp from '../leaveMessage/list.vue'
import TagsListComp from '../tags/list.vue'

const compKeyMap = {
  menuArticleList: ArticleListComp,
  menuUsersList: UsersListComp,
  menuLeaveMessageList: LeaveMessageListComp,
  menuTagsList: TagsListComp
}
const compSubTitle = [
  {
    menuKey: "article",
    title: "Article",
    sub: [
      {
        k: "menuArticleList",
        w: "list"
      }
    ]
  },
  {
    menuKey: "tag",
    title: "Tags",
    sub: [
      {
        k: "menuTagsList",
        w: "list"
      }
    ]
  },
  {
    menuKey: "leaveMessage",
    title: "Leave Message",
    sub: [
      {
        k: "menuLeaveMessageList",
        w: "list"
      }
    ]
  },
  {
    menuKey: "users",
    title: "Users",
    sub: [
      {
        k: "menuUsersList",
        w: "list"
      }
    ]
  },
]

const openKeys = ref(["article"]);
const selectedKeys2 = ref(["menuArticleList"]);

</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
  background-image: url('../assets/logo.svg');
  background-size: cover;
  /* 可选，根据需要调整 */
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>