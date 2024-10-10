import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      uid: 0,
    };
  },
  mutations: {
    update_uid(state, newVal) {
      state.uid = newVal;
    },

  }
});

export default store;
