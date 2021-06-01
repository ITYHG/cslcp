import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store"
Vue.use(VueRouter)

const routes = [{
    path: '',
    redirect: {
      name: 'Login'
    }
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('../components/Login/Login.vue'),
    meta: {
      title: "登录",
      isCache: false
    }

  },
  {
    path: '/MyCenter',
    name: 'MyCenter',
    component: () => import('../components/MyCenter/MyCenter.vue'),
    meta: {
      title: "公司数据",
      isCache: false
    }

  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    children: [{
        path: 'HomePage',
        name: 'HomePage',
        component: () => import('../components/HomePage/HomePage.vue'),
        meta: {
          title: "统计信息",
          isCache: false
        }

      },
      {
        path: 'CompanyType',
        name: 'CompanyType',
        component: () => import('../components/CompanyType/CompanyType.vue'),
        meta: {
          title: "公司类型",
          isCache: false
        }
      },
      {
        path: 'UserDetails',
        name: 'UserDetails',
        component: () => import('../components/EnterpriseInformation/UserDetails.vue'),
        meta: {
          title: "公司详情",
          isCache: false
        }
      },
      {
        path: 'Details',
        name: 'Details',
        component: () => import('../components/EnterpriseInformation/Details.vue'),
        meta: {
          title: "公司详情",
          isCache: false
        }
      },
      {
        path: 'CustumerLevel',
        name: 'CustumerLevel',
        component: () => import('../components/CustumerLevel/CustumerLevel.vue'),
        meta: {
          title: "客户级别",
          isCache: false
        }

      },
      {
        path: 'Courier',
        name: 'Courier',
        component: () => import('../components/Courier/Courier.vue'),
        meta: {
          title: "快递公司",
          isCache: false
        }
      },
      {
        path: 'Members',
        name: 'Members',
        component: () => import('../components/Members/Members.vue'),
        meta: {
          title: "会员类型",
          isCache: false
        }
      },
      {
        path: 'PortInformation',
        name: 'PortInformation',
        component: () => import('../components/PortInformation/PortInformation.vue'),
        meta: {
          title: "港口信息",
          isCache: false
        }
      },
      {
        path: 'EnterpriseInformation',
        name: 'EnterpriseInformation',
        component: () => import('../components/EnterpriseInformation/EnterpriseInformation.vue'),
        meta: {
          title: "企业信息",
          isCache: false
        }
      },
      {
        path: 'UserSeal',
        name: 'UserSeal',
        component: () => import('../components/Seal/UserSeal.vue'),
        meta: {
          title: "印章",
          isCache: false
        }
      },
      {
        path: 'Seal',
        name: 'Seal',
        component: () => import('../components/Seal/Seal.vue'),
        meta: {
          title: "印章",
          isCache: false
        }
      },
      {
        path: 'LadingLogo',
        name: 'LadingLogo',
        component: () => import('../components/LadingLogo/LadingLogo.vue'),
        meta: {
          title: "提单logo",
          isCache: false
        }
      },
      {
        path: 'UserLadingLogo',
        name: 'UserLadingLogo',
        component: () => import('../components/LadingLogo/UserLadingLogo.vue'),
        meta: {
          title: "提单logo",
          isCache: false
        }
      },
      {
        path: 'ServiceOpen',
        name: 'ServiceOpen',
        component: () => import('../components/ServiceOpen/ServiceOpen.vue'),
        meta: {
          title: "服务开通",
          isCache: false
        },
      },
      {
        path: 'UserServiceOpen',
        name: 'UserServiceOpen',
        component: () => import('../components/ServiceOpen/UserServiceOpen.vue'),
        meta: {
          title: "服务开通",
          isCache: false
        },
      },
      {
        path: 'Review',
        name: 'Review',
        component: () => import('../components/ServiceOpen/Review.vue'),
        meta: {
          title: "审核详情",
          isCache: false
        }
      },
    ]
  }

]

const router = new VueRouter({
  routes,
  mode: 'history',
})
export default router
//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
router.beforeEach((to, from, next) => {
  // 清空缓存
  if (to.path.indexOf("/Login") != -1) {
    sessionStorage.removeItem("state")
    window.sessionStorage.clear();
    store.commit("isLogin", false)
    store.commit("menu", [])
    store.commit("userPromission", [])
    store.commit("userCompanyId", "")
    store.commit("userCompanyPosition", "")
  }
  next()
})

router.beforeEach((to, from, next) => {
  if (to.path.indexOf("/Login") == 0) {
    next()
  } else if (!store.state.isLogin) {
    Vue.prototype.$message({
      showClose: true,
      message: "您尚未登录或长时间未操作，请重新登录！",
      type: "error"
    })
    next("/Login")
  } else if (!store.state.userPromission.includes(to.path)) {
    router.back()
    Vue.prototype.$message({
      showClose: true,
      message: "无权访问该菜单",
      type: "error"
    })
  } else {
    next()
  }
})