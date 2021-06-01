<template>
  <div class="BanHeader">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="item in currentPath" :key="item.path">
        {{ item.meta.title }}
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: "BanHeader",
  data() {
    return {
      currentPath: null,
    };
  },
  watch: {
    $route: {
      handler() {
        this.getcurrentPath();
      },
      deep: true,
      immediate: true,
    },
  },
  methods: {
    getcurrentPath() {
      this.currentPath = this.$route.matched;
    },
  },
};
</script>

<style lang="less" scoped>
.BanHeader {
  height: 30px;
  width: 98.8%;
  position: absolute;
  padding-left: 20px;
  left: 0;
  background-color: #ffffff;
  margin-bottom: 10px;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  * {
    font-size: 13px;
    font-weight: bold;
    line-height: 30px;
  }
}
</style>
