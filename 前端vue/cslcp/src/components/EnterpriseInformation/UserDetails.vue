<template>
  <div class="details">
    <h2>企业名称：{{ name }}</h2>
    <div class="Operation">
      <el-button
        class="addStyleTwo"
        type="primary"
        @click="addData()"
        icon="el-icon-plus"
        >修改信息</el-button
      >
    </div>
    <fieldset>
      <legend>自然信息</legend>
      <div class="One-Top disFlex">
        <div>管理员手机号：{{ phone }}</div>
        <div>企业联系邮箱：{{ email }}</div>
        <div>企业状态：{{ status }}</div>
      </div>
      <div class="alone">企业信用码：{{ creditcode }}</div>
      <div class="alone">企业详细地址：{{ address }}</div>
    </fieldset>
    <fieldset>
      <legend>业务信息</legend>
      <div class="Two">
        <div class="one">会员类型：{{ membertype }}</div>
        <div class="two">客户级别：{{ level }}</div>
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
    <!-- 修改数据弹出框 -->
    <el-dialog
      title="修改信息"
      :visible.sync="updateDialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="form"
        :rules="formRules"
        ref="form"
        @submit.native.prevent
        v-if="updateDialogFormVisible"
      >
        <el-form-item
          label="公司名称："
          :label-width="formLabelWidth"
          prop="name"
        >
          <el-input
            @keyup.enter.native="onUpdata('form')"
            v-model="form.name"
            autocomplete="off"
            placeholder="请输入公司名称"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="管理员手机号："
          :label-width="formLabelWidth"
          prop="phone"
        >
          <el-input
            @keyup.enter.native="onUpdata('form')"
            v-model="form.phone"
            autocomplete="off"
            placeholder="请输入管理员手机号"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="企业邮箱："
          :label-width="formLabelWidth"
          prop="email"
        >
          <el-input
            @keyup.enter.native="onUpdata('form')"
            v-model="form.email"
            autocomplete="off"
            placeholder="请输入企业邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="企业信用码："
          :label-width="formLabelWidth"
          prop="creditcode"
        >
          <el-input
            @keyup.enter.native="onUpdata('form')"
            v-model="form.creditcode"
            autocomplete="off"
            placeholder="请输入企业信用码"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="公司地址："
          :label-width="formLabelWidth"
          prop="address"
        >
          <el-input
            @keyup.enter.native="onUpdata('form')"
            v-model="form.address"
            autocomplete="off"
            placeholder="请输入公司地址"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetUpdataForm('form')">取 消</el-button>
        <el-button type="primary" @click="onUpdata('form')">确 定</el-button>
      </div>
    </el-dialog>
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
    const onlyNumber = (rule, value, callback) => {
      const number = /^[0-9][0-9]*$/;
      if (!number.test(value)) {
        callback(new Error("只能输入数字"));
      } else if (value.length > 11) {
        callback(new Error("电话号码长度应小于11"));
      } else {
        callback;
      }
    };
    const isMax = (rule, value, callback) => {
      const numbers = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/;
      if (value.length > 12) {
        callback(new Error("长度需小于12"));
      } else if (value == "") {
        callback();
      } else if (!numbers.test(value)) {
        callback(new Error("以汉字或字母开头,且不能含有特殊字符"));
      } else {
        callback();
      }
    };
    const emailRules = (rule, value, callback) => {
      const rules = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!rules.test(value)) {
        callback(new Error("请输入正确的邮箱地址"));
      } else {
        callback();
      }
    };
    const creditcodeRules = (rule, value, callback) => {
      const rules = /^[A-Z][A-Z]*$/;
      if (!rules.test(value)) {
        callback(new Error("只能输入大写的英文字母"));
      } else {
        callback();
      }
    };
    return {
      // 图片加载
      isshow: false,
      //   公司名称
      name: "",
      // 会员类型
      membertype: "",
      // 客户级别
      level: "",
      // 企业状态
      status: "",
      // 企业信用码
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
      //   显示/隐藏 修改弹出框
      updateDialogFormVisible: false,
      // 点击空白处是否可以关闭弹出框
      closeOnClickModal: false,
      //   修改提单
      form: {
        name: "",
        phone: "",
        email: "",
        creditcode: "",
        address: "",
      },
      //   验证规则
      formRules: {
        name: [
          {
            required: true,
            message: "请输入公司名称",
            trigger: "blur",
          },
          {
            validator: isMax,
            trigger: "blur",
          },
        ],
        phone: [
          {
            required: true,
            message: "请输入管理员号码",
            trigger: "blur",
          },
          {
            validator: onlyNumber,
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: "blur",
          },
          {
            validator: emailRules,
            trigger: "blur",
          },
        ],
        creditcode: [
          {
            required: true,
            message: "请输入企业信用代码",
            trigger: "blur",
          },
          {
            validator: creditcodeRules,
            trigger: "blur",
          },
        ],
        address: [
          {
            required: true,
            message: "请输入公司地址",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    this.ReceiveData();
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
      let id = this.$store.state.userCompanyId;
      let map = {};
      map.id = id;

      this.$axios("/api/company/detail", { params: map }).then((res) => {
        if (res.data.request[0].issendbl == 1) {
          issendbl = "是";
        } else {
          issendbl = "否";
        }
        if (res.data.request[0].isreceive == 1) {
          isreceive = "是";
        } else {
          isreceive = "否";
        }
        if (res.data.request[0].isenableseal) {
          isenableseal = "是";
        } else {
          isenableseal = "否";
        }

        this.name = res.data.request[0].name;
        this.membertype = res.data.request[0].membertype;
        this.level = res.data.request[0].level;
        this.status = res.data.request[0].status;
        this.creditcode = res.data.request[0].status;
        this.creditcode = res.data.request[0].creditcode;
        this.address = res.data.request[0].address;
        this.phone = res.data.request[0].phone;
        this.email = res.data.request[0].email;
        this.createdate = res.data.request[0].createdate;
        this.createuserid = res.data.request[0].createuserid;
        this.createusername = res.data.request[0].createusername;
        this.issendbl = issendbl;
        this.isreceive = isreceive;
        this.isenableseal = isenableseal;
        this.file = res.data.request[0].file;
        // this.Sealfile1 = res.data.request[0].Sealfile;
        this.cerificateattachment1 = res.data.request[0].cerificateattachment1;
        this.cerificateattachment2 = res.data.request[0].cerificateattachment2;
        this.sealfile1 = res.data.request[0].sealfile1;
        this.sealfile2 = res.data.request[0].sealfile2;
      });
    },
    // 修改数据的按钮表单
    addData() {
      this.form.name = this.name;
      this.form.phone = this.phone;
      this.form.email = this.email;
      this.form.creditcode = this.creditcode;
      this.form.address = this.address;
      this.updateDialogFormVisible = true;
    },
    // 弹出表单的取消按钮
    resetUpdataForm(form) {
      this.$refs[form].resetFields();
      this.updateDialogFormVisible = false;
    },
    // 修改数据的确定按钮
    onUpdata(form) {
      let map = {
        id: this.$store.state.userCompanyId,
        name: this.form.name,
        email: this.form.email,
        address: this.form.address,
        phone: this.form.phone,
        creditcode: this.form.creditcode,
      };

      this.$axios("/api/company/edit", { params: map }).then((res) => {
        if (res.status == 200) {
          if (res.data.status == 1) {
            this.$message({
              message: "修改成功",
              type: "success",
            });
            this.ReceiveData();
            this.updateDialogFormVisible = false;
          } else if (res.data.status == 2) {
            this.$message.error("公司名称重复");
          } else {
            this.$message.error("数据丢失");
          }
        } else if (res.status == 404) {
          this.$message.error("找不到页面");
        } else if (res.status == 500) {
          this.$message.error("系统异常，请联系管理人员");
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.details {
  height: 1200px;
  padding: 5px;

  .addStyleTwo {
    position: absolute;
    top: 70px;
    left: 85.5%;
  }

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
