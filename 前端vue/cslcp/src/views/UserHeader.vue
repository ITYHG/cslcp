<template>
  <div class="UserHeader">
    <div class="HeaderLogo">
      <img :src="LogoUrlx" alt="这是本系统小Logo" style="height:50px;" />
    </div>
    <el-button icon="el-icon-s-fold" circle></el-button>
    <el-col :span="24">
      <img :src="LogoUrlb" alt="这是本系统名称" style="height:50px;" />
    </el-col>
    <el-col :span="1">
      <el-dropdown trigger="click">
        <span class="el-dropdown-link">
          <div class="demo-type">
            <el-avatar :size="50" @error="errorHandler">
              <img :src="touxiang" />
            </el-avatar>
          </div>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item
            @click.native="goMyCenter"
            icon="el-icon-circle-plus-outline"
            v-if="this.$store.state.userCompanyId == 1"
            >数据展示中心</el-dropdown-item
          >
          <el-dropdown-item @click.native="goLogin" icon="el-icon-circle-plus"
            >退出</el-dropdown-item
          >
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "UserHeader",
  data() {
    return {
      LogoUrlx: require("../assets/imges/logox.png"),
      LogoUrlb: require("../assets/imges/logob.png"),
      touxiang: require("../assets/imges/touxiang.jpg"),
    };
  },
  methods: {
    errorHandler() {
      return true;
    },
    goLogin() {
      this.$router.push({ path: "/Login" });
    },
    goMyCenter() {
      this.$router.push({ path: "/MyCenter" });
    },
  },
};
</script>

<style lang="less" scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.demonstration {
  display: block;
  color: #8492a6;
  font-size: 14px;
  margin-bottom: 20px;
}
.UserHeader {
  .HeaderLogo {
    position: absolute;
    left: 60px;
    top: 5px;
  }
  .el-button {
    position: absolute;
    left: 200px;
    top: 10px;
  }
}
.el-col {
  position: absolute;
  right: 10px;
  top: 5px;
}
</style>
