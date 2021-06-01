<template>
  <div class="details">
    <h2>企业名称：{{ name }}</h2>
    <fieldset>
      <legend>自然信息</legend>
      <div class="One-Top disFlex">
        <div>会员类型：{{ membertype }}</div>
        <div>客户级别：{{ level }}</div>
        <div>企业状态：{{ status }}</div>
      </div>
      <div class="alone">企业状态信用码：{{ creditcode }}</div>
      <div class="alone">企业详细地址：{{ address }}</div>
    </fieldset>
    <fieldset>
      <legend>业务信息</legend>
      <div class="Two">
        <div class="one">管理员手机号：{{ phone }}</div>
        <div class="two">企业联系邮箱：{{ email }}</div>
      </div>
      <div class="disFlex">
        <div>创建时间：{{ createdate }}</div>
      </div>
      <div class="disFlex">
        <div>是否开通出单服务：{{ issendbl }}</div>
        <div>是否开通发单服务：{{ isreceive }}</div>
        <div>是否开通签章服务：{{ isenableseal }}</div>
      </div>
    </fieldset>
    <div class="img-logo">
      <!-- 附件1 -->
      <div class="block" v-if="cerificateattachment1">
        <el-card shadow="always" style="width: 100%; height: 100%">
          <el-image :src="'/api/static' + cerificateattachment1" alt="查看">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
        <div>
          <el-button
            class="downloadBtn"
            slot="trigger"
            size="small"
            type="primary"
            >附 件 1</el-button
          >
        </div>
      </div>
      <!-- 附件2 -->
      <div class="block" v-if="cerificateattachment2">
        <el-card shadow="always" style="width: 100%; height: 100%">
          <el-image :src="'/api/static' + cerificateattachment2" alt="查看">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
        <div>
          <el-button
            class="downloadBtn"
            slot="trigger"
            size="small"
            type="primary"
            >附 件 2</el-button
          >
        </div>
      </div>
      <!-- LOGO -->
      <div class="block" v-if="file">
        <el-card shadow="always" style="width: 100%; height: 100%">
          <el-image :src="'/api/static' + file" alt="查看">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
        <div>
          <el-button
            class="downloadBtn"
            slot="trigger"
            size="small"
            type="primary"
          >
            LOGO
          </el-button>
        </div>
      </div>
      <!-- 印章1 -->
      <div class="block" v-if="sealfile1">
        <el-card shadow="always" style="width: 100%; height: 100%">
          <el-image :src="'/api/static' + sealfile1" alt="查看">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
        <div>
          <el-button
            class="downloadBtn"
            slot="trigger"
            size="small"
            type="primary"
            >出单签章</el-button
          >
        </div>
      </div>
      <!-- 印章2 -->
      <div class="block" v-if="sealfile2">
        <el-card shadow="always" style="width: 100%; height: 100%">
          <el-image :src="'/api/static' + sealfile2" alt="查看">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </el-card>
        <div>
          <el-button
            class="downloadBtn"
            slot="trigger"
            size="small"
            type="primary"
            >收单签章</el-button
          >
        </div>
      </div>
    </div>
    <!-- 返回上一页 -->
    <div>
      <el-button
        class="returnBefore"
        slot="trigger"
        size="small"
        type="primary"
        v-on:click="back"
        >返回主页面</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.ReceiveData();
    window.setTimeout(() => {
      this.isshow = true;
    }, 3000);
  },
  data() {
    return {
      isshow: false,
      name: "",
      // 会员类型
      membertype: "",
      // 客户级别
      level: "",
      // 企业状态
      status: "",
      // 企业状态信用码
      creditcode: "",
      // 企业详细地址
      address: "",
      // 管理员手机号
      phone: "",
      // 企业联系邮箱
      email: "",
      // 创建时间
      createdate: "",
      // 创建人账号id
      createuserid: "",
      // 创建人姓名
      createusername: "",
      // 是否开通出单服务
      issendbl: "",
      // 是否开通发单服务
      isreceive: "",
      // 是否开通签章服务
      isenableseal: "",

      title: "nihao",
      // LOGO 是否弹出
      LOGOdialogTableVisible: false,
      // 表单中 label 宽度
      formLabelWidth: "120px",
      // 签章弹出框是否弹出
      dialogFormVisible: false,
      // 点击空白处能否关闭弹窗
      closeOnClickModal: false,

      dialogImageUrl: "",
      dialogVisible: false,
      // logo
      file: "",
      // 印章1-
      sealfile1: "",
      // 印章2
      sealfile2: "",
      // 附件1
      cerificateattachment1: "",
      // 附件2
      cerificateattachment2: "",
      // 印章启用状态  1/2  1:启用  2:禁用
      isEnable: "",
      // 印章类型     1/2  1:
      sealType: "",
    };
  },
  methods: {
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    ReceiveData() {
      let issendbl;
      let isreceive;
      let isenableseal;
      if (this.$route.query.issendbl == 1) {
        issendbl = "是";
      } else {
        issendbl = "否";
      }
      if (this.$route.query.isreceive == 1) {
        isreceive = "是";
      } else {
        isreceive = "否";
      }
      if (this.$route.query.isenableseal) {
        isenableseal = "是";
      } else {
        isenableseal = "否";
      }

      this.name = this.$route.query.name;
      this.membertype = this.$route.query.membertype;
      this.level = this.$route.query.level;
      this.status = this.$route.query.status;
      this.creditcode = this.$route.query.status;
      this.creditcode = this.$route.query.creditcode;
      this.address = this.$route.query.address;
      this.phone = this.$route.query.phone;
      this.email = this.$route.query.email;
      this.createdate = this.$route.query.createdate;
      this.createuserid = this.$route.query.createuserid;
      this.createusername = this.$route.query.createusername;
      this.issendbl = issendbl;
      this.isreceive = isreceive;
      this.isenableseal = isenableseal;
      this.file = this.$route.query.file;
      // this.Sealfile1 = this.$route.query.Sealfile;
      this.cerificateattachment1 = this.$route.query.cerificateattachment1;
      this.cerificateattachment2 = this.$route.query.cerificateattachment2;
      this.sealfile1 = this.$route.query.sealfile1;
      this.sealfile2 = this.$route.query.sealfile2;
    },
    // 返回上一页
    back() {
      this.$router.go(-1);
    },
  },
};
</script>

<style lang="less" scoped>
.details {
  height: 1200px;
  // overflow-y: scroll;
  padding: 5px;
  // box-sizing: border-box;
  legend {
    margin-left: 20px;
  }
  .disFlex {
    display: flex;
    justify-content: space-between;
    flex-wrap: nowrap;

    div {
      width: 30%;
      height: 40px;
      line-height: 40px;
      text-align: start;
      margin: 0 0 10px 10px;
      font-weight: bold;
      font-size: 20px;
    }
  }
  .alone {
    height: 40px;
    line-height: 40px;
    text-align: start;
    margin: 0 0 10px 10px;
    font-weight: bold;
    font-size: 20px;
  }
  .Two {
    display: flex;
    justify-content: space-between;
    div {
      width: 30%;
      height: 40px;
      line-height: 40px;
      display: inline-block;
      margin: 0 0 10px 10px;
      font-weight: bold;
      font-size: 20px;

      &.two {
        margin-left: 50px;
      }
    }
  }

  .img-logo {
    position: relative;
    display: flex;
    height: 450px;
    .block {
      position: inherit;
      display: inline-block;
      width: 20%;
      height: 66% !important;

      .el-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      .downloadBtn {
        position: absolute;
        left: 50%;
        top: 90%;
        transform: translate(-50%, -50%);
      }
    }
  }
  .returnBefore {
    position: absolute;
    left: 91%;
    top: 60px;
  }

  // 滚动条的宽度
  /deep/ .el-table__body-wrapper::-webkit-scrollbar {
    width: 6px; // 横向滚动条
    height: 6px; // 纵向滚动条 必写
  }
  // 滚动条的滑块
  /deep/ .el-table__body-wrapper::-webkit-scrollbar-thumb {
    background-color: #ddd;
    border-radius: 3px;
  }
}
</style>
