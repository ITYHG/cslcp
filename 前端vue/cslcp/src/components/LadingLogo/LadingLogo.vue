<template>
  <div class="LadingLogo">
    <!-- 搜索框 -->
    <el-form
      @submit.native.prevent
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      :rules="searchRules"
      ref="formInline"
      style="height: 50px"
    >
      <el-form-item
        label="公司名称:"
        prop="companyName"
        title="请输入公司名称，最长12位"
      >
        <el-input
          @keyup.enter.native="resultFromCompanyId('formInline')"
          class="addStyleOne"
          v-model="formInline.companyName"
          placeholder="请输入公司名称"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="LOGO名称:"
        prop="name"
        title="请输入LOGO名称，最长6位"
      >
        <el-input
          @keyup.enter.native="resultFromCompanyId('formInline')"
          class="addStyleOne"
          v-model="formInline.name"
          placeholder="请输入LOGO名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="审核状态:">
        <el-select v-model="formInline.isenable" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.isenable"
            :label="item.label"
            :value="item.isenable"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="addStyleOne">
        <el-button type="primary" @click="onShouSubmit" icon="el-icon-search"
          >查询</el-button
        >
      </el-form-item>
      <el-form-item class="addStyleOne">
        <el-button @click="Reset" icon="el-icon-refresh-right">重置</el-button>
      </el-form-item>
    </el-form>
    <el-divider></el-divider>
    <!-- table 表格 -->
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      :show-header="true"
      :height="566"
      @selection-change="handleSelectionChange"
      :header-cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column type="selection" width="120" align="center">
      </el-table-column>
      <el-table-column label="序号" width="200" prop="xuhao" align="center">
        <template slot-scope="scope">{{ scope.row.xuhao }}</template>
      </el-table-column>
      <el-table-column
        prop="companyName"
        label="公司名称"
        width="300"
        align="center"
      >
      </el-table-column>
      <el-table-column prop="name" label="Logo名称" width="150" align="center">
      </el-table-column>
      <el-table-column
        prop="uploadday"
        label="上传日期"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="isenable"
        label="启用状态"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="confirmstatus"
        label="LOGO确认状态"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="Control"
        label="操作"
        show-overflow-tooltip
        width="200"
        fixed="right"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="ToExamine(scope.row)"
            class="el-button-del"
            fixed="right"
            align="center"
            >查看LOGO</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="Page">
      <div class="count-show">
        <span>共 {{ pageNumber }} 条</span>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[9, 20, 50]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next, jumper"
        :total="pageNumber"
      >
      </el-pagination>
    </div>
    <!-- 修改弹出框 -->
    <el-dialog
      width="30%"
      title="LOGO预览"
      :visible.sync="updateDialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="updataForm"
        ref="updataForm"
        @submit.native.prevent
        v-if="updateDialogFormVisible"
      >
        <el-form-item :label-width="formLabelWidth" prop="file">
          <!-- LOGO 图片 -->
          <div class="block">
            <el-card shadow="always" style="width: 100%; height: 100%">
              <el-image :src="'/api/static' + updataForm.file" alt="查看">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </el-card>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetUpdataForm('updataForm')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    const isMaxOne = (rule, value, callback) => {
      const numbers = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/;
      if (value.length > 12) {
        callback(new Error("长度需小于12"));
      } else if (value == "") {
        callback();
      } else if (!numbers.test(value)) {
        callback(new Error("请以汉字或字母开头,不能含有特殊字符"));
      } else {
        callback();
      }
    };
    const isMaxTwo = (rule, value, callback) => {
      const Chinese = /^\u4e00-\u9fa5*$/;
      if (Chinese.test(value)) {
        callback(new Error("请输入汉字"));
      } else if (value.length > 6) {
        callback(new Error("长度小于6"));
      } else if (value == "") {
        callback();
      } else {
        callback();
      }
    };
    return {
      // 查询信息
      formInline: {
        companyName: "",
        name: "",
        isenable: "",
      },
      // 查询处的下拉选择
      options: [
        {
          isenable: "1",
          label: "启用",
        },
        {
          isenable: "2",
          label: "未启用",
        },
      ],
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 9,
      // 总条数
      pageNumber: 0,
      // 修改弹出框
      updataForm: {
        file: "",
      },
      // 查询规则
      searchRules: {
        companyName: [
          {
            max: 12,
            message: "长度最长位12",
            trigger: "blur",
          },
          {
            validator: isMaxOne,
            trigger: "blur",
          },
        ],
        name: [
          {
            max: 6,
            message: "最大长度为6",
            trigger: "blur",
          },
          {
            validator: isMaxTwo,
            trigger: "blur",
          },
        ],
      },
      // 序号
      tableData: [],
      // 点击空白处，不能够关闭弹出框
      closeOnClickModal: false,
      // 修改的弹出框
      updateDialogFormVisible: false,
      // 宽度
      formLabelWidth: "120px",
      // 是否禁用,是否显示
      isDisabled: false,
      // 取消时，为 true，下次展示新的
      AddDialogFormVisible: false,
    };
  },
  mounted() {
    this.resultFromCompanyId("formInline");
  },
  methods: {
    // 根据 companyId 进行查询
    resultFromCompanyId(formInline) {
      let companyId = this.$store.state.userCompanyId;
      // 海运平台管理公司
      if (companyId == 1) {
        this.isDisabled = true;
      } else {
        this.isDisabled = false;
      }
      let map = {
        companyName: this.formInline.companyName,
        name: this.formInline.name,
        isenable: this.formInline.isenable,
        currentPage: this.currentPage,
        pageSize: this.pageSize,
        companyid: companyId,
      };
      this.$refs[formInline].validate((valid) => {
        if (valid) {
          this.$axios("/api/bllogo/list", { params: map })
            .then((res) => {
              if (res.status == 200) {
                this.tableData.splice(0, this.tableData.length);

                for (var list = 1; list <= res.data.request.length; list++) {
                  let isenable = "";
                  let confirmstatus = "";
                  // 启用状态
                  if (res.data.request[list - 1].isenable == 1) {
                    isenable = "启用";
                  } else if (res.data.request[list - 1].isenable == 2) {
                    isenable = "未启用";
                  }
                  // 确认状态
                  if (res.data.request[list - 1].confirmstatus == 1) {
                    confirmstatus = "待审核";
                  } else if (res.data.request[list - 1].confirmstatus == 2) {
                    confirmstatus = "平台已确认";
                  } else if (res.data.request[list - 1].confirmstatus == 3) {
                    confirmstatus = "平台已否认";
                  } else if (res.data.request[list - 1].confirmstatus == 4) {
                    confirmstatus = "确认";
                  }
                  let map = {
                    xuhao: list + (this.currentPage - 1) * this.pageSize,
                    id: res.data.request[list - 1].id,
                    companyid: res.data.request[list - 1].companyid,
                    companyName: res.data.request[list - 1].companyName,
                    name: res.data.request[list - 1].name,
                    uploadday: res.data.request[list - 1].uploadday,
                    comments: res.data.request[list - 1].comments,
                    isenable: isenable,
                    confirmstatus: confirmstatus,
                    file: res.data.request[list - 1].file,
                    version: res.data.request[list - 1].version,
                  };
                  this.tableData.push(map);
                }
                // 数据总条数
                this.pageNumber = res.data.pageNumber;
                // 当前页面数
                this.currentPage = res.data.currentPage;
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
              } else if (res.status == 500) {
                this.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("发现系统异常，请联系管理人员");
            });
        } else {
          return false;
        }
      });
    },
    // 重置
    Reset(formInline) {
      // 对关联的formInline 的内容进行清空，并且进行验证
      this.$refs["formInline"].resetFields();
      this.formInline.isenable = "";
      this.onShouSubmit();
    },
    onShouSubmit() {
      this.currentPage = 1;
      this.resultFromCompanyId("formInline");
    },
    // 添加数据的按钮
    addData() {
      this.form.addForms.comfirmstatus = "1";
      this.form.addForms.label = "待审核";
      this.dialogFormVisible = true;
    },
    // 点击修改
    ToExamine(row) {
      this.updateDialogFormVisible = true;
      this.updataForm.file = row.file;
    },
    // 复选框被选中，val 代表被选中这一行的数据
    handleSelectionChange(val) {
      this.multipleSelection = val;
      return this.multipleSelection;
    },
    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    // 取消按钮
    resetUpdataForm(updataForm) {
      this.$refs[updataForm].resetFields();
      this.updateDialogFormVisible = false;
    },
    // 重置添加的表单验证
    resetForm(form) {
      this.$refs[form].resetFields();
      this.dialogFormVisible = false;
      this.AddDialogFormVisible = true;
    },
  },
  watch: {
    currentPage() {
      this.resultFromCompanyId("formInline");
    },
    pageSize() {
      // 当每页的条数改变时，显示成第一页
      this.currentPage = 1;
      this.resultFromCompanyId("formInline");
    },
  },
};
</script>

<style lang="less" scoped>
.LadingLogo {
  line-height: 0;
  text-align: left;
  padding: 0px 20px 0px 20px;
  display: inline-block;
  width: 95%;
  height: 85%;
  font-weight: bold;
  color: black;

  .count-show {
    margin-top: 40px;
    display: inline-block;
    font-weight: lighter;
    font-size: 13px;
    font-weight: 400;
    color: #606266;
  }
  .el-divider {
    margin: 10px 0 10px 0;
  }
  .addStyleOne {
    margin-right: 10px;
  }
  .el-pagination {
    position: relative;
    top: -13px;
    display: inline-block;
  }
  .Operation {
    width: 400px;
    margin-top: 10px;
    text-align: start;
  }
  .pop {
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 300px;
    height: auto;
    background: lightblue;
    font-size: 20px;
    padding: 10px;
    div {
      height: 50px;
      line-height: 50px;
      margin-top: 50px;
      text-align: center;

      input {
        height: 30px;
      }
      button {
        width: 50px;
        height: 30px;
        margin: 0 30px;
      }
    }
  }
  .el-button-updata {
    margin-right: 40px;
  }
  .el-button-del,
  .el-button-updata {
    font-size: 12px;
  }

  .block {
    display: inline-block;
    width: 300px;
    height: 300px !important;
  }

  .upload-demo {
    position: absolute;
    left: 200px;
    top: 60px;
  }

  .downloadBtn {
    position: absolute;
    left: 200px;
    top: 5px;
  }
  .upload-demo-two {
    top: 300px;
    left: 250px;
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
