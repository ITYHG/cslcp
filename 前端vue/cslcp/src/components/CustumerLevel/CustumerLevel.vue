<template>
  <div id="CustumerLevel">
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
        label="客户等级查询:"
        prop="name"
        title="请输入数字，最长6位"
      >
        <el-input
          @keyup.enter.native="onSubmit('formInline')"
          class="addStyleOne"
          v-model="formInline.name"
          placeholder="请输入VIP等级"
        ></el-input>
      </el-form-item>
      <el-form-item class="addStyleOne">
        <el-button
          type="primary"
          @click="onSubmit('formInline')"
          icon="el-icon-search"
          >查询</el-button
        >
      </el-form-item>

      <el-form-item class="addStyleOne">
        <el-button @click="Reset('formInline')" icon="el-icon-refresh-right"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <div class="Operation">
      <el-button
        class="addStyleTwo"
        type="primary"
        @click="dialogFormVisible = true"
        icon="el-icon-plus"
        >增加</el-button
      >
      <el-popconfirm
        confirm-button-text="确定"
        cancel-button-text="我再想想"
        icon="el-icon-info"
        icon-color="red"
        @confirm="onDelete()"
        title="您确定要删除所选列吗？"
      >
        <el-button
          class="addStyleTwo"
          slot="reference"
          icon="el-icon-delete"
          style="margin: 0 10px"
          >删除</el-button
        >
      </el-popconfirm>

      <el-button class="addStyleTwo" @click="exportData" icon="el-icon-download"
        >导出</el-button
      >
    </div>
    <el-divider></el-divider>
    <!-- table 表 -->
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      :show-header="true"
      :height="562"
      @selection-change="handleSelectionChange"
      :header-cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column type="selection" width="120" align="center">
      </el-table-column>
      <el-table-column label="序号" width="120" prop="xuhao" align="center">
        <template slot-scope="scope">{{ scope.row.xuhao }}</template>
      </el-table-column>
      <el-table-column prop="name" label="客户等级" width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="low"
        label="消费下限(元)"
        show-overflow-tooltip
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="hi"
        label="消费上限(元)"
        show-overflow-tooltip
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="Control"
        label="操作"
        show-overflow-tooltip
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            @click="handleClick(scope.row)"
            type="text"
            size="small"
            class="el-button-updata"
            >修改</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="DeleteOne(scope.row)"
            class="el-button-del"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div>
      <div class="count-show">
        <span> 共 {{ pageNumber }} 条 </span>
      </div>
      <el-pagination
        class="Page"
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

    <!-- 添加数据弹出框 -->
    <el-dialog
      title="新增客户等级"
      :visible.sync="dialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="form"
        :rules="formRules"
        ref="form"
        @submit.native.prevent
      >
        <el-form-item
          label="客户等级："
          :label-width="formLabelWidth"
          prop="name"
        >
          <el-input
            v-model="form.name"
            autocomplete="off"
            placeholder="请输入客户等级,请按照：VIP+“数字” 的格式书写"
            @keyup.enter.native="onInsert('form')"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="消费下限(元)："
          :label-width="formLabelWidth"
          prop="low"
        >
          <el-input
            v-model="form.low"
            autocomplete="off"
            placeholder="请输入消费下限"
            @keyup.enter.native="onInsert('form')"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="消费上限(元)："
          :label-width="formLabelWidth"
          prop="hi"
        >
          <el-input
            v-model="form.hi"
            autocomplete="off"
            placeholder="请输入消费上限"
            @keyup.enter.native="onInsert('form')"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('form')">取 消</el-button>
        <el-button type="primary" @click="onInsert('form')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改数据弹出框 -->
    <el-dialog
      title="修改客户等级"
      :visible.sync="updateDialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="updataForm"
        :rules="formRules"
        ref="updataForm"
        @submit.native.prevent
      >
        <el-form-item
          label="客户等级："
          :label-width="formLabelWidth"
          prop="name"
        >
          <el-input
            @keyup.enter.native="onUpdata('updataForm')"
            v-model="updataForm.name"
            autocomplete="off"
            placeholder="请输入客户等级"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="消费下限(元)："
          :label-width="formLabelWidth"
          prop="low"
        >
          <el-input
            @keyup.enter.native="onUpdata('updataForm')"
            v-model="updataForm.low"
            autocomplete="off"
            placeholder="请输入消费下限"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="消费上限(元)："
          :label-width="formLabelWidth"
          prop="hi"
        >
          <el-input
            @keyup.enter.native="onUpdata('updataForm')"
            v-model="updataForm.hi"
            autocomplete="off"
            placeholder="请输入消费上限"
          ></el-input>
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
  name: "CustumerLevel",
  mounted() {
    this.onSubmit("formInline");
  },
  data() {
    // 表单验证，只能为数字
    const isNum = (rule, value, callback) => {
      const numbers = /^[1-9][0-9]*$/g;
      if (!value) {
        return;
      }
      if (!numbers.test(value)) {
        callback(new Error("请输入正确的数字"));
      } else if (value > 2147483647) {
        callback(new Error("请输入小于2147483647的金额"));
      } else {
        callback();
      }
    };
    // VIP + 数字
    const StringAndNumber = (rule, value, callback) => {
      const SAN = /^(VIP)[0-9]*$/;
      if (!SAN.test(value)) {
        callback(new Error("请正确的输入客户等级格式"));
      } else {
        callback();
      }
    };
    // 最大输入长度
    const isMax = (rule, value, callback) => {
      const numbers = /^(VIP)[0-9]*$/g;
      if (value.length > 6) {
        callback(new Error("长度需小于6个字符"));
      } else if (value == "") {
        callback();
      } else if (!numbers.test(value)) {
        callback(new Error("请以VIP/VIP+数字开头"));
      } else {
        callback();
      }
    };
    return {
      // 输入框的值
      formInline: {
        name: "",
      },
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 9,
      //总条数
      pageNumber: 0,
      // 是否弹出新增框
      dialogFormVisible: false,

      // 弹出框出现的时候，是否可以点击空白处进行关闭弹出框
      closeOnClickModal: false,
      // 增加数据按钮的数据
      form: {
        name: "",
        low: "",
        hi: "",
      },
      // 是否弹出修改框
      updateDialogFormVisible: false,
      // 更新数据表
      updataForm: {
        id: "",
        name: "",
        low: "",
        hi: "",
      },
      // 添加数据验证规则
      formRules: {
        name: [
          {
            required: true,
            message: "请输入客户等级",
            trigger: "blur",
          },
          {
            validator: isMax,
            trigger: "blur",
          },
        ],
        low: [
          {
            required: true,
            message: "请输入消费下限",
            trigger: "blur",
          },
          {
            validator: isNum,
            trigger: "blur",
          },
        ],
        hi: [
          {
            required: true,
            message: "请输入消费上限",
            trigger: "blur",
          },
          {
            validator: isNum,
            trigger: "blur",
          },
        ],
      },
      // 查询规则
      searchRules: {
        name: [
          {
            max: 6,
            message: "长度最长为6",
            trigger: "blur",
          },
          {
            validator: isMax,
            trigger: "blur",
          },
        ],
      },
      formLabelWidth: "120px",
      // 序号
      tableData: [],
      // 存放被选中的 id
      multipleSelection: [],
    };
  },
  methods: {
    // 搜索数据
    onSubmit(formInline) {
      this.$refs[formInline].validate((valid) => {
        if (valid) {
          let parmMap = {
            name: this.formInline.name,
            currentPage: this.currentPage,
            pageSize: this.pageSize,
          };
          this.$axios
            .get("/api/cuslevel/list", { params: parmMap })
            .then((res) => {
              if (res.status == 200) {
                this.tableData.splice(0, this.tableData.length);
                for (var list = 1; list <= res.data.request.length; list++) {
                  let map = {
                    xuhao: list + (this.currentPage - 1) * this.pageSize,
                    id: res.data.request[list - 1].id,
                    name: res.data.request[list - 1].name,
                    low: res.data.request[list - 1].low,
                    hi: res.data.request[list - 1].hi,
                  };
                  this.tableData.push(map);
                }

                this.pageNumber = res.data.pageNumber;
                this.currentPage = res.data.currentPage;
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
              } else if (res.status == 500) {
                this.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("发现系统异常，请联系管理员");
            });
        }
      });
    },
    // 重置
    Reset(formInline) {
      // 对关联的 formInline 的内容进行验证，并且清空
      this.$refs[formInline].resetFields();
      this.onSubmit("formInline");
    },
    // 导出数据
    exportData() {
      this.$axios
        .post(
          "/api/cuslevel/excelDownload",
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
            let fileName = "客户级别" + value + ".xls";
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
    // 添加数据
    onInsert(Insertform) {
      this.$refs[Insertform].validate((valid) => {
        // 如果通过校验
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          this.$axios
            .get("/api/cuslevel/add", {
              params: this.form,
            })
            .then((res) => {
              if (res.status == 200) {
                if (res.data.status == 1) {
                  this.dialogFormVisible = false;
                  this.onSubmit("form");
                } else if (res.data.status == 2) {
                  this.$message.error("名字重复或消费上下限不符合梯度");
                } else {
                  this.$message.error("系统发现错误，请联系管理人员");
                }
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
              } else if (res.status == 500) {
                this.$message.error("数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("zengjia请联系管理人员进行修复");
            });
        } else {
          // 没有通过校验，返回false
          return false;
        }
      });
    },
    // 多选删除
    onDelete() {
      if (this.multipleSelection.length === 0) {
        this.$message.error("请确定您已经选择至少一项进行删除");
      } else {
        var delValue = [];
        this.multipleSelection.forEach((element) => {
          delValue.push(element.id);
        });
        this.$axios({
          url: "/api/cuslevel/remove",
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          dataType: "json",
          data: JSON.stringify(delValue),
        })
          .then((resp) => {
            if (resp.status == 200) {
              if (resp.data.status == 0) {
                this.$message.error("删除失败，请重试");
              } else {
                this.currentPage = 1;
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                this.onSubmit("formInline");
              }
            } else if (resp.status == 400) {
              this.$message.error("页面丢失");
            } else if (resp.status == 500) {
              this.$message.error("后台或数据库异常");
            }
          })
          .catch((err) => {
            this.$message.error("后台发生错误，请联系管理人员");
          });
      }
    },
    // 修改
    onUpdata(updataForm) {
      this.$refs[updataForm].validate((valid) => {
        if (valid) {
          this.$axios
            .get("/api/cuslevel/edit", {
              params: this.updataForm,
            })
            .then((res) => {
              if (res.status == 200) {
                if (res.data.status == 1) {
                  this.updateDialogFormVisible = false;
                  this.$message({
                    message: "修改成功",
                    type: "success",
                  });
                  this.onSubmit("updataForm");
                } else if (res.data.status == 2) {
                  this.$message.error("名字重复或消费上下线不符合梯度");
                } else {
                  this.$message.error("系统发现未知错误，请联系管理员修复");
                }
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
              } else if (res.status == 500) {
                this.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => [
              this.$message.error("系统发现未知错误，请联系管理人员"),
            ]);
        } else {
          // 校验不通过
          return false;
        }
      });
    },
    handleClick(row) {
      this.updataForm.name = row.name;
      this.updataForm.low = row.low;
      this.updataForm.hi = row.hi;
      this.updataForm.id = row.id;
      this.updateDialogFormVisible = true;
    },
    DeleteOne(row) {
      var delValue = [row.id];
      this.$axios({
        url: "/api/cuslevel/remove",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        dataType: "json",
        data: JSON.stringify(delValue),
      })
        .then((res) => {
          if (res.status == 200) {
            if (res.data.status == 0) {
              this.$message.error("删除失败，请重试");
            } else {
              this.currentPage = 1;
              this.$message({
                message: "删除成功",
                type: "success",
              });
              this.onSubmit("formInline");
            }
          } else if (res.status == 404) {
            this.$message.error("页面丢失");
          } else if (res.status == 500) {
            this.$message.error("后台或数据库异常");
          }
        })
        .catch((err) => {
          this.$message.error("后台发现未知错误，请联系管理人员修复");
        });
    },
    // 重置添加的表单验证
    resetForm(form) {
      this.$refs[form].resetFields();
      this.dialogFormVisible = false;
    },
    // 重置修改的表单验证
    resetUpdataForm(updataForm) {
      this.$refs[updataForm].resetFields();
      this.updateDialogFormVisible = false;
    },
  },
  watch: {
    dialogFormVisible() {
      (this.form.name = ""), (this.form.low = ""), (this.form.hi = "");
    },
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
#CustumerLevel {
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
