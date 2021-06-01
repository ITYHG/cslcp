<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          :default-active="$route.path"
          class="el-menu-vertical-demo"
          background-color="#2b395d"
          text-color="black"
          active-text-color="#FFFFFF"
          router
        >
          <template v-for="navMenu in navMenus">
            <el-menu-item :index="navMenu.path" :key="navMenu.path">
              <i :class="navMenu.icon"></i>
              <span slot="title">{{ navMenu.title }}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Asisd",
  methods: {},
  props: {
    menus: Array,
  },
  computed: {
    navMenus() {
      return this.$store.state.menu;
    },
  },
};
</script>

<style scoped lang="less">
.el-menu {
  * {
    font-size: 16px;
    font-weight: bold;
    color: white;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
      "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    text-align: left;
  }
  .el-menu-item:hover:not(.is-active) {
    background-color: #366a9e !important;
  }
}
.el-menu-item.is-active {
  border-left: 3px #2789ee solid;
  color: #fff;
  span {
    color: #fff !important;
  }
}
</style>
