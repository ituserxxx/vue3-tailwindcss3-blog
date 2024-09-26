import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      menu_1:"users",
      menu_2:"menuUsersList",
      isLoggedIn: false,
    };
  },
  mutations: {
    update_menu_1(state,newVal) {
      state.menu_1 = newVal;
    },
    update_menu_2(state,newVal) {
      state.menu_2 = newVal;
    },
  }
});

export default store;
