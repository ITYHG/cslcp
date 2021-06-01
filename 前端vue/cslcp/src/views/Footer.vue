<template>
  <div class="footerz">
    <el-link
      :underline="false"
      href="https://mail.qq.com/"
      type="info"
      icon="el-icon-message"
      target="_black"
      >1421609643@qq.com</el-link
    >
    <el-link
      :underline="false"
      href="tel://13482941367"
      type="info"
      icon="el-icon-phone-outline"
      target="_black"
      >13482941367</el-link
    >
    <el-link
      :underline="false"
      href="https://beian.miit.gov.cn/#/Integrated/index"
      type="info"
      target="_black"
      >京ICP备19004658号</el-link
    >
    <el-link
      :underline="false"
      href=" https://csdnimg.cn/release/live_fe/culture_license.png"
      type="info"
      target="_black"
      >京网文〔2020〕1039-165号</el-link
    >
    <el-link
      :underline="false"
      href=" https://csdnimg.cn/cdn/content-toolbar/csdn-ICP.png"
      type="info"
      target="_black"
      >经营性网站备案信息</el-link
    >
    <el-link
      :underline="false"
      href=" http://www.bjjubao.org/"
      type="info"
      target="_black"
      >北京互联网违法和不良信息举报中心</el-link
    >
    <el-link :underline="false" type="info"
      >©2021-2021中远海运集装箱运输有限公司</el-link
    >
    <el-link :underline="false" type="info" href="/api/membertype/exportPdf"
      >版权与免责声明</el-link
    >
  </div>
</template>

<script>
export default {
  name: "Footerz",
  data() {
    return {
      drawer: false,
      direction: "ttb",
    };
  },
  methods: {},
};
</script>
<style lang="less" scoped>
.footerz {
  line-height: 50px;
  height: 100%;
  width: 100%;
  .el-link {
    margin: 0 10px;
  }
}
</style>
