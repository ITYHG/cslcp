<template>
  <div class="home">
    <el-container>
      <el-header>
        <user-header></user-header>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <asisd></asisd>
        </el-aside>
        <el-container>
          <el-main style="padding:0 ">
            <ban-header></ban-header>
            <el-card class="box-card">
              <keep-alive>
                <!-- 需要缓存的组件 -->
                <router-view v-if="$route.meta.isCache"></router-view>
              </keep-alive>
              <!-- 不需要缓存的组件 -->
              <router-view v-if="!$route.meta.isCache"></router-view>
            </el-card>
          </el-main>
          <el-footer>
            <footerz></footerz>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import BanHeader from "./BanHeader";
import Asisd from "./Asisd";
import UserHeader from "./UserHeader.vue";
import Footerz from "./Footer.vue";

export default {
  name: "Home",
  data() {
    return {
      contentStyleObj: {
        height: "",
      },
    };
  },
  components: {
    Asisd,
    BanHeader,
    UserHeader,
    Footerz,
  },
  methods: {},
};
</script>
<style lang="less">
.home {
  height: 100%;
}
.el-container {
  height: 100%;
}
.el-header {
  background-color: #2b395d;
}
.el-header,
.el-footer {
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #2b395d;
  text-align: center;
  line-height: 200px;
  border-right: 1px solid #e9eef3;
}

.el-main {
  background-color: #f2f5fa;
  color: #333;
  text-align: center;
  line-height: 50px;
  position: relative;
  text-align: left;
}
//滚动条的宽度
.el-main::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
//滚动条的滑块
.el-main::-webkit-scrollbar-thumb {
  background-color: #a1a3a9;
  border-radius: 3px;
}
.el-card.box-card {
  margin: 40px 20px 0 20px;
  height: 92% !important;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  min-height: 790px;
}
body > .el-container {
  margin-bottom: 40px;
}
</style>
