<template>
  <div class="Courier">
    <!-- 查询模块 -->
    <el-form
      @submit.native.prevent
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      :rules="Seachrules"
      ref="formInline"
      style="height: 50px "
    >
      <el-form-item label="快递公司名称：" prop="name">
        <el-tooltip
          class="item"
          effect="dark"
          content="只能输入汉字、字母、长度小于12！"
          placement="top"
        >
          <el-input
            v-model.trim="formInline.name"
            @keyup.enter.native="onShouSubmit"
            onkeyup="this.value=this.value.replace(/\s+/g,'')"
            placeholder="快递公司名称"
          ></el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onShouSubmit" icon="el-icon-search"
          >查询</el-button
        >
        <el-button @click="onReset" icon="el-icon-refresh-right"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <!-- 表格操作模块 -->
    <div class="Operation">
      <el-button
        type="primary"
        @click="InsertdialogFormVisible = true"
        icon="el-icon-plus"
        >增加</el-button
      >
      <el-popconfirm
        confirm-button-text="确定"
        cancel-button-text="我再想想"
        icon="el-icon-info"
        icon-color="red"
        @confirm="onDelete()"
        title="您确定要删除所选行吗？"
      >
        <el-button slot="reference" icon="el-icon-delete" style="margin:0 10px"
          >删除</el-button
        >
      </el-popconfirm>

      <el-button @click="onExport" icon="el-icon-download">导出</el-button>
    </div>
    <!-- 添加弹窗功能区域 -->
    <el-dialog
      title="快递公司添加"
      :visible.sync="InsertdialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        @submit.native.prevent
        :model="Insertform"
        :rules="Insertrules"
        ref="Insertform"
        v-if="InsertdialogFormVisible"
      >
        <el-form-item
          label="快递公司名称"
          :label-width="InsertformLabelWidth"
          prop="name"
        >
          <el-input
            @keyup.enter.native="onInsert('Insertform')"
            v-model.trim="Insertform.name"
            onkeyup="this.value=this.value.replace(/\s+/g,'')"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="InsertdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onInsert('Insertform')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- 修改本行内容 -->
    <el-dialog
      @submit.native.prevent
      title="修改本行内容"
      :visible.sync="UpdatedialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="Updateform"
        :rules="Insertrules"
        ref="Updateform"
        v-if="UpdatedialogFormVisible"
      >
        <el-form-item
          label="快递公司名称"
          :label-width="InsertformLabelWidth"
          prop="name"
        >
          <el-input
            v-model.trim="Updateform.name"
            onkeyup="this.value=this.value.replace(/\s+/g,'')"
            autocomplete="off"
            @keyup.enter.native="onUpdata('Updateform')"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="UpdatedialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onUpdata('Updateform')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <el-divider></el-divider>
    <!-- 表格绘制区域 -->
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100% overflow-y: auto;"
      :show-header="true"
      :height="562"
      fit
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="xuhao" label="序号" align="center">
      </el-table-column>
      <el-table-column prop="name" label="快递公司名称" align="center">
      </el-table-column>
      <el-table-column prop="quantity" label="累计发单量" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >修改</el-button
          >
          <el-button type="text" size="small" @click="DeleteOne(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页菜单实现 -->
    <div>
      <div class="count-show">
        <span> 共 {{ pageNumber }} 条 </span>
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
  </div>
</template>
<script>
export default {
  name: "Members",
  data() {
    // 长度小于12且只能输入汉字字母
    const isMAX = (rule, value, callback) => {
      if (value.length <= 12) {
        const number = /^[a-zA-Z\u4e00-\u9fa5]*$/;
        if (!number.test(value)) {
          if (value == "") {
            callback();
          } else {
            callback(new Error("只允许输入汉字、字母组合！"));
          }
        } else {
          callback();
        }
      } else {
        callback(new Error("长度需小于12个字符"));
      }
    };
    return {
      // 存放查询条件
      formInline: {
        name: "",
      },
      Seachrules: {
        name: [
          { max: 12, message: "长度需小于12个字符", trigger: "blur" },
          { validator: isMAX, trigger: "blur" },
        ],
      },
      // 是否删除
      Deletevisible: false,
      closeOnClickModal: false,
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 9,
      //总条数
      pageNumber: 0,
      // 表格数据
      tableData: [],
      // 存放被选中的id
      multipleSelection: [],
      // 添加功能实现所需要的属性
      // 插入弹出框是否显示
      InsertdialogFormVisible: false,
      // 插入表单中传入的数据
      Insertform: {
        name: "",
      },
      // 弹出框宽度、（插入和更新两个均用这个）
      InsertformLabelWidth: "125px",
      // 插入和更新的表单验证
      Insertrules: {
        name: [
          { required: true, message: "请输入快递公司名称！", trigger: "blur" },
          { max: 12, message: "长度需小于12个字符", trigger: "blur" },
          { validator: isMAX, trigger: "blur" },
        ],
      },
      // 修改功能所需要的属性
      // 是否弹出修改框
      UpdatedialogFormVisible: false,
      // 修改的字段
      Updateform: {
        id: 0,
        name: "",
        version: 0,
      },
    };
  },
  methods: {
    // 添加数据
    onInsert(Insertform) {
      let that = this;
      // 添加数据并返回结果
      that.$refs[Insertform].validate((valid) => {
        //开启校验
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          that.$axios
            .get("/api/expressCompany/add", {
              params: that.Insertform,
            })
            .then((res) => {
              // 判断是否请求成功
              if (res.status == 200) {
                // 判断后台结果是否成功
                if (res.data.status == 1) {
                  // 关闭弹出框
                  that.InsertdialogFormVisible = false;
                  // 重新查出数据
                  that.onSubmit("formInline");
                  this.$message({
                    message: "添加成功",
                    type: "success",
                  });
                  // 如果已经存在相同名称快递
                } else if (res.data.status == 2) {
                  that.$message.error("已经存在相同名称快递，请重新尝试！");
                } else {
                  that.$message.error("系统发现未知错误，请联系管理员修复！");
                }
                // 如果是404显示页面丢失
              } else if (res.status == 404) {
                that.$message.error("页面丢失");
                // 如果是500返回数据库异常
              } else if (res.status == 500) {
                that.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              that.$message.error("系统发现未知错误，请联系管理员修复！");
            });
        } else {
          //校验不通过
          return false;
        }
      });
    },
    // 手动点击查询按钮
    onShouSubmit() {
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
    // 查询数据
    onSubmit(formInline) {
      let that = this;
      // 表单校验
      that.$refs[formInline].validate((valid) => {
        //开启校验
        if (valid) {
          // 创建axios携带参数
          let parmMap = {
            name: that.formInline.name,
            currentPage: this.currentPage,
            pageSize: this.pageSize,
          };
          that.$axios
            .get("/api/expressCompany/list", {
              params: parmMap,
            })
            .then((res) => {
              // 判断请求是否成功

              if (res.status == 200) {
                // 清空数据表里的内容
                that.tableData.splice(0, that.tableData.length);
                // 循环遍历出后台查询的内容
                for (let list = 1; list <= res.data.request.length; list++) {
                  let map = {
                    xuhao: list + (this.currentPage - 1) * this.pageSize,
                    id: res.data.request[list - 1].id,
                    quantity: res.data.request[list - 1].quantity,
                    name: res.data.request[list - 1].name,
                    version: res.data.request[list - 1].version,
                  };
                  that.tableData.push(map);
                }
                // 修改当前总条数和页数
                this.pageNumber = res.data.pageNumber;
                this.currentPage = res.data.currentPage;
                // 如果为404则显示页面丢失
              } else if (res.status == 404) {
                that.$message.error("页面丢失");
                // 如果为500显示后台或数据库异常
              } else if (res.status == 500) {
                that.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("系统发现未知错误，请联系管理员修复！");
            });
        } else {
          //校验不通过
          return false;
        }
      });
    },
    onDelete() {
      // 判断是否选择了内容
      if (this.multipleSelection.length == 0) {
        this.$message.error("请您确定已选择一项或多项,并重新选择!");
      } else {
        let that = this;
        // 将选中内容的id添加到数组中
        var arr = this.multipleSelection;
        let multis = [];
        // 将选中的每一个id放入到multis数组中
        for (var i = 0; i < arr.length; i++) {
          multis.push(arr[i].id);
        }
        // 是否关闭提示文件
        this.Deletevisible = false;
        that
          .$axios({
            url: "/api/expressCompany/remove",
            method: "POST",
            headers: { "Content-Type": "application/json" },
            //async:false,
            dataType: "json",
            data: JSON.stringify(multis),
          })
          .then((res) => {
            // 是否向后台成功发送请求
            if (res.status == 200) {
              // 请求内容是否成功，成功调用查询，不成功弹出错误
              if (res.data.status == 0) {
                this.$message.error(res.data.msg);
              } else if (res.data.status == 2) {
                this.$message.error(res.data.msg);
              } else {
                this.currentPage = 1;
                that.onSubmit("formInline");
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
              }
              // 判断请求是否为404，如果是则显示页面丢失
            } else if (res.status == 404) {
              that.$message.error("页面丢失");
              // 判断请求是否为500，如果是则显示后台或数据库异常
            } else if (res.status == 500) {
              that.$message.error("后台或数据库异常");
            }
          })
          .catch((err) => {
            this.$message.error("后台发现未知错误，请联系管理员修复！");
          });
      }
    },

    // 导出
    onExport() {
      // 发送查询条件
      this.$axios
        .post(
          "/api/expressCompany/excelDownload",
          {
            params: this.formInline.name,
          },
          {
            responseType: "blob",
          }
        )
        .then((res) => {
          // 是否向后台成功发送请求
          if (res.status == 200) {
            let content = res.data; // 文件流
            let blob = new Blob([content], {
              type: "application/octet-stream",
            });
            let aData = new Date();
            // 获取当前日期格式
            let value =
              aData.getFullYear() +
              "-" +
              (aData.getMonth() + 1) +
              "-" +
              aData.getDate();
            let fileName = "快递公司信息" + value + ".xls";
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
              this.$message({
                message: "导出成功",
                type: "success",
              });
            } else {
              // IE10+下载
              navigator.msSaveBlob(blob, fileName);
              this.$message({
                message: "导出成功",
                type: "success",
              });
            }
            // 判断请求是否为404，如果是显示页面丢失
          } else if (res.status == 404) {
            that.$message.error("页面丢失");
            // 判断请求是否为500，如果是显示后台或数据库异常
          } else if (res.status == 500) {
            that.$message.error("后台或数据库异常");
          }
        });
    },
    // 修改
    onUpdata(Updataform) {
      let that = this;
      // 更改数据并返回结果
      this.$refs[Updataform].validate((valid) => {
        //开启校验
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          that.$axios
            .get("/api/expressCompany/edit", {
              params: that.Updateform,
            })
            .then((res) => {
              // 判断请求是否为200

              if (res.status == 200) {
                // 判断后台数据是否请求成功
                if (res.data.status == 1) {
                  // 关闭弹出框
                  that.UpdatedialogFormVisible = false;
                  // 重新查询
                  that.onSubmit("formInline");
                  this.$message({
                    message: "修改成功",
                    type: "success",
                  });
                  // 如果已经存在相同名称快递公司
                } else if (res.data.status == 2) {
                  that.$message.error("已经存在相同名称快递，请重新尝试！");
                } else {
                  // 如果返回值不为1或2，弹出错误
                  that.$message.error("系统发现未知错误，请联系管理员修复！");
                }
                // 判断请求是否为404，如果是显示页面丢失
              } else if (res.status == 404) {
                that.$message.error("页面丢失");
                // 判断请求是否为500，如果是显示后台或数据库异常
              } else if (res.status == 500) {
                that.$message.error("后台或数据库异常");
              }
            })
            .catch((err) => {
              this.$message.error("系统发现未知错误，请联系管理员修复！");
            });
        } else {
          //校验不通过
          return false;
        }
      });
    },

    // 重置条件
    onReset() {
      // 刷新表单验证并查询
      this.$refs["formInline"].resetFields();
      this.onShouSubmit();
    },
    // 修改本行内容,弹出修改框,并将选中的内容展示在模块中
    handleClick(row) {
      this.Updateform.id = row.id;
      this.Updateform.name = row.name;
      this.Updateform.quantity = row.amount;
      this.Updateform.version = row.version;
      this.UpdatedialogFormVisible = true;
    },
    // 删除本行内容
    DeleteOne(row) {
      let multis = [row.id];
      let that = this;
      // 发送请求删除本id
      that
        .$axios({
          url: "/api/expressCompany/remove",
          method: "POST",
          headers: { "Content-Type": "application/json" },
          dataType: "json",
          data: JSON.stringify(multis),
        })
        .then((res) => {
          // 是否向后台成功发送请求
          if (res.status == 200) {
            // 判断后台是否访问成功
            if (res.data.status == 0) {
              that.$message.error(res.data.msg);
            } else if (res.data.status == 2) {
              that.$message.error(res.data.msg);
            } else {
              // 查询并弹出成功
              this.currentPage = 1;
              that.onSubmit("formInline");
              that.$message({
                message: "删除成功",
                type: "success",
              });
            }
            // 判断请求是否为404，如果是显示页面丢失
          } else if (res.status == 404) {
            that.$message.error("页面丢失");
            // 判断请求是否为500，如果是显示后台或数据库异常
          } else if (res.status == 500) {
            that.$message.error("后台或数据库异常");
          }
        })
        .catch((err) => {
          this.$message.error("后台发现未知错误，请联系管理员修复！");
        });
    },
    // 存放当前id
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    // 将选中的复选框的id存放进multipleSelection数组中
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
  },

  watch: {
    // 每次添加都是新的
    InsertdialogFormVisible() {
      this.Insertform.name = "";
    },
    // 检测选的页是否改变
    currentPage() {
      this.onSubmit("formInline");
    },
    // 每页多少条
    pageSize() {
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
  },
  mounted: function() {
    this.onSubmit("formInline");
  },
};
</script>

<style lang="less" scoped>
.Courier {
  line-height: 0;

  padding: 0px 20px 0px 20px;
  display: inline-block;
  text-align: left;
  width: 95%;
  height: 85%;
  font-weight: bold;
  color: black;
  .el-divider {
    margin: 10px 0 10px 0;
  }
  .count-show {
    margin-top: 40px;
    display: inline-block;
    font-weight: lighter;
    font-size: 13px;
    font-weight: 400;
    color: #606266;
  }
  .el-pagination {
    position: relative;
    top: -13px;
    display: inline-block;
  }
  .demo-form-inline {
    display: inline-block;
    width: 100%;
  }
  .Operation {
    display: inline-block;
    margin: 10px 0 0 0;
  }
  .el-table {
    margin-top: 20px;
    width: 100%;
    * {
      text-align: center;
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
