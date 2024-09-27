import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      menu_1:"article",
      menu_2:"menuArticleList",
      uid: 0,
    };
  },
  mutations: {
    update_uid(state,newVal) {
      state.uid = newVal;
    },
    update_menu_1(state,newVal) {
      state.menu_1 = newVal;
    },
    update_menu_2(state,newVal) {
      state.menu_2 = newVal;
    },
  }
});

export default store;
