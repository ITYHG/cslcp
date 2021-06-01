<template>
  <div class="EnterpriseInformation">
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
        prop="name"
        title="请输入公司名称，最长12位"
      >
        <el-input
          @keyup.enter.native="onSubmit('formInline')"
          class="addStyleOne"
          v-model="formInline.name"
          placeholder="请输入公司名称"
        ></el-input>
      </el-form-item>
      <el-form-item
        label="国家:"
        prop="countryname"
        title="请输入国家名称，最长6位"
      >
        <el-input
          @keyup.enter.native="onSubmit('formInline')"
          class="addStyleOne"
          v-model="formInline.countryname"
          placeholder="请输入国家名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="审核状态:">
        <el-select v-model="formInline.status" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.status"
            :label="item.label"
            :value="item.status"
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
    <!-- 导出  -->
    <div class="Operation">
      <el-button class="addStyleTwo" @click="exportData" icon="el-icon-download"
        >导出</el-button
      >
    </div>
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
      <el-table-column label="序号" width="200" prop="xuhao" align="cener">
        <template slot-scope="scope" align="cener">{{
          scope.row.xuhao
        }}</template>
      </el-table-column>
      <el-table-column prop="name" label="公司名称" width="300" align="cener">
      </el-table-column>
      <el-table-column
        prop="countryName"
        label="注册国家"
        width="150"
        align="cener"
      >
      </el-table-column>
      <el-table-column
        prop="provinceName"
        label="注册省份"
        width="150"
        align="cener"
      >
      </el-table-column>
      <el-table-column
        prop="cityName"
        label="注册城市"
        width="150"
        align="cener"
      >
      </el-table-column>
      <el-table-column prop="status" label="审核状态" width="100" align="cener">
      </el-table-column>
      <el-table-column
        prop="creditcode"
        label="企业信用代码"
        width="200"
        align="cener"
      >
      </el-table-column>
      <el-table-column
        prop="phone"
        label="管理员手机号"
        width="200"
        align="cener"
      >
      </el-table-column>
      <el-table-column prop="email" label="企业邮箱" width="250" align="cener">
      </el-table-column>
      <el-table-column
        prop="Control"
        label="操作"
        show-overflow-tooltip
        width="100"
        fixed="right"
        align="cener"
      >
        <template slot-scope="scope" align="cener">
          <el-button
            type="text"
            size="small"
            @click="ToExamine(scope.row)"
            class="el-button-del"
            >审核</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="details(scope.row)"
            class="el-button-del"
            >详情</el-button
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
    <!-- 审核弹框 -->
    <el-dialog
      title="修改审核状态"
      :visible.sync="updateDialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="updataForm"
        ref="updataForm"
        @submit.native.prevent
        v-if="updateDialogFormVisible"
      >
        <el-form-item label="审核状态:">
          <el-select v-model="updataForm.status" :label="updataForm.label">
            <el-option
              v-for="item in updataForms"
              :key="item.status"
              :label="item.label"
              :value="item.status"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetUpdataForm('updataForm')">取 消</el-button>
        <el-button type="primary" @click="onUpdata('updataForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "EnterpriseInformation",
  mounted() {
    this.onSubmit("formInline");
  },
  data() {
    const isMaxOne = (rule, value, callback) => {
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
    const isMaxTwo = (rule, value, callback) => {
      const Chinese = /^[\u4e00-\u9fa5][\u4e00-\u9fa5]*$/;
      if (value.length > 6) {
        callback(new Error("长度小于6"));
      } else if (value == "") {
        callback();
      } else if (!Chinese.test(value)) {
        callback(new Error("请输入汉字,且不能含有特殊字符"));
      } else {
        callback();
      }
    };
    return {
      // 查询信息
      formInline: {
        name: "",
        countryname: "",
        status: "",
      },
      options: [
        {
          status: "1",
          label: "待审核",
        },
        {
          status: "2",
          label: "未通过",
        },
        {
          status: "9",
          label: "通过",
        },
      ],
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 9,
      // 总条数
      pageNumber: 0,

      // 弹出审核弹框
      updateDialogFormVisible: false,
      // 弹出框出现的时候，是否可以点击空白处进行关闭弹出框
      closeOnClickModal: false,
      // 将要修改的 审核
      changeStatus: "",
      // 改变审核后的名字
      chagneLabel: "",
      // 审核状态信息
      updataForm: {
        id: "",
        status: "",
        label: "",
      },
      updataForms: [
        {
          status: "9",
          label: "通过",
        },
        {
          status: "2",
          label: "未通过",
        },
        {
          status: "1",
          label: "未审核",
        },
      ],
      // 查询规则
      searchRules: {
        name: [
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
        countryname: [
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
      // 存放被选中的id
      multipleSelection: [],
    };
  },
  methods: {
    // 重置
    Reset(formInline) {
      // 对关联的formInline 的内容进行清空，并且进行验证
      this.$refs["formInline"].resetFields();
      this.formInline.status = "";
      this.onShouSubmit();
    },
    onShouSubmit() {
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
    onSubmit(formInline) {
      this.$refs[formInline].validate((valid) => {
        if (valid) {
          let parmMap = {
            name: this.formInline.name,
            countryname: this.formInline.countryname,
            status: this.formInline.status,
            currentPage: this.currentPage,
            pageSize: this.pageSize,
            companyid: this.$store.state.userCompanyId,
          };

          this.$axios("/api/company/list", { params: parmMap })
            .then((res) => {
              if (res.status == 200) {
                this.tableData.splice(0, this.tableData.length);

                for (var list = 1; list <= res.data.request.length; list++) {
                  let status = "";
                  if (res.data.request[list - 1].status == 1) {
                    status = "未审核";
                  } else if (res.data.request[list - 1].status == 2) {
                    status = "未通过";
                  } else if (res.data.request[list - 1].status == 9) {
                    status = "通过";
                  }
                  let map = {
                    xuhao: list + (this.currentPage - 1) * this.pageSize,
                    id: res.data.request[list - 1].id,
                    name: res.data.request[list - 1].name,
                    countryName: res.data.request[list - 1].countryName,
                    provinceName: res.data.request[list - 1].provinceName,
                    cityName: res.data.request[list - 1].cityName,
                    status: status,
                    creditcode: res.data.request[list - 1].creditcode,
                    phone: res.data.request[list - 1].phone,
                    email: res.data.request[list - 1].email,
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
    // 导出数据
    exportData() {
      this.$axios
        .post(
          "/api/company/excelDownload",
          { params: this.formInline.name },
          {
            responseType: "blob",
          }
        )
        .then((res) => {
          if (res.status == 200) {
            let content = res.data; // 文件流
            let blob = new Blob([content], {
              type: "application/octet-stream",
            });
            let aData = new Date();
            let value =
              aData.getFullYear() +
              "-" +
              (aData.getMonth() + 1) +
              "-" +
              aData.getDate();
            let fileName = "企业信息表" + value + ".xls";
            // 如果后端返回文件名
            if ("download" in document.createElement("a")) {
              // 非IE下载
              let link = document.createElement("a");
              link.download = fileName;
              link.style.display = "none";
              link.href = URL.createObjectURL(blob);
              document.body.appendChild(link);
              link.click();
              URL.revokeObjectURL(link.href); // 释放URL 对象
              document.body.removeChild(link);
            } else {
              // IE10+下载
              navigator.msSaveBlob(blob, fileName);
              this.$message({
                message: "导出成功",
                type: "success",
              });
            }
          } else if (res.data.status == 404) {
            this.$message.error("页面丢失");
          } else if (res.data.status == 500) {
            this.$message.error("后台/数据库异常");
          }
        });
    },
    ToExamine(row) {
      let status;
      this.updateDialogFormVisible = true;
      if (row.status == "未审核") {
        status = "1";
      } else if (row.status == "未通过") {
        status = "2";
      } else if (row.status == "通过") {
        status = "9";
      }
      this.updataForms.forEach((element) => {
        if (element.status === status) {
          this.updataForm.id = row.id;
          this.updataForm.status = element.status;
          this.updataForm.label = element.label;
        }
      });
    },
    // 更新审核状态
    onUpdata(updataForm) {
      this.$refs[updataForm].validate((valid) => {
        if (valid) {
          this.$axios("/api/company/edit", { params: this.updataForm })
            .then((res) => {
              if (res.status == 200) {
                if (res.data.status == 1) {
                  this.updateDialogFormVisible = false;
                  this.$message({
                    message: "修改审核成功",
                    type: "success",
                  });
                  this.onSubmit("updataForm");
                } else if (res.data.status == 2) {
                  this.$message.error("审核类型修改失败");
                } else {
                  this.$message.error("系统发现未知错误，请联系管理员修复");
                }
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
              } else if (res.status == 500) {
                this.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("系统发现未知错误，请联系管理员修复");
            });
        }
      });
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
    // 重置修改的表单验证
    resetUpdataForm(updataForm) {
      this.$refs[updataForm].resetFields();
      this.updateDialogFormVisible = false;
    },
    details(row) {
      let obj = {};
      obj.id = row.id;
      this.$axios("/api/company/detail", { params: obj }).then((res) => {
        this.$router.push({
          path: "/home/Details",
          query: res.data.request[0],
        });
      });
    },
  },
  watch: {
    currentPage() {
      this.onSubmit("formInline");
    },
    pageSize() {
      // 当每页的条数改变时，显示成第一页
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
  },
};
</script>

<style lang="less" scoped>
.EnterpriseInformation {
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
