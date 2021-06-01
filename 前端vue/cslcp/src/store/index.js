import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersisrence from "vuex-persist"
import axios from "axios"
Vue.use(Vuex)
const vuexLocal = new VuexPersisrence({
  storage: window.sessionStorage
})
export default new Vuex.Store({
  state: {
    menu: [],
    isLogin: false,
    userPromission: [],
    userCompanyId: "",
    // 企业管理员：user，平台管理员;admin,平台工作人员：staff
    userCompanyPosition: ""
  },
  mutations: {
    menu(state, newValue) {
      state.menu = newValue
    },
    isLogin(state, newValue) {
      state.isLogin = newValue
    },
    userPromission(state, newValue) {
      state.userPromission = newValue
    },
    userCompanyId(state, newValue) {
      state.userCompanyId = newValue
    },
    userCompanyPosition(state, newValue) {
      state.userCompanyPosition = newValue
    }
  },
  actions: {
    menu(context, newValue) {
      return axios.post("/api/user/menu", {
        userName: newValue
      }).then((res) => {
        context.commit("menu", res.data.resultData)
      })
    },
    userPromission(context, newValue) {
      return axios.post("/api/user/userPermission", {
        userName: newValue
      }).then((res) => {
        context.commit("userPromission", res.data.resultData)
        context.commit("userCompanyId", res.data.userCompanyId)
        context.commit("userCompanyPosition", res.data.userCompanyPosition)
      })
    },

  },
  modules: {},
  plugins: [vuexLocal.plugin]
})