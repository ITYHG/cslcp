<template>
  <div class="ServiceOpen">
    <!-- 查询模块 -->
    <el-form
      @submit.native.prevent
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="height: 50px "
    >
      <el-form-item label="公司名称:" prop="companyName">
        <el-input
          @keyup.enter.native="onSubmit"
          class="addStyleOne"
          v-model="formInline.companyName"
          placeholder="请输入公司名称"
        ></el-input>
      </el-form-item>

      <el-form-item label="服务类型:">
        <el-select v-model="formInline.type">
          <el-option value="" label="全选">全选</el-option>
          <el-option value="1" label="出单服务">出单服务</el-option>
          <el-option value="2" label="收单服务">收单服务</el-option>
          <el-option value="3" label="签章服务">签章服务</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态:">
        <el-select v-model="formInline.auditstatus">
          <el-option value="" label="全选">全选</el-option>
          <el-option value="1" label="待审核">待审核</el-option>
          <el-option value="2" label="已审核">已审核</el-option>
        </el-select>
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
      <el-button @click="onExport" icon="el-icon-download">导出</el-button>
    </div>

    <el-divider></el-divider>
    <!-- 表格绘制区域 -->
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100% overflow-y: auto;"
      :show-header="true"
      :height="562"
    >
      <el-table-column
        prop="xuhao"
        label="序号"
        fixed
        width="50"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="typeName"
        label="开通服务类型"
        width="160"
        align="center"
        fixed
      >
      </el-table-column>
      <el-table-column
        prop="companyName"
        label="申请公司"
        width="170"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="applyaccount"
        label="申请人账户"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="applyusername"
        label="申请人名称"
        width="100"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="applydate"
        label="申请时间"
        width="150"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="auditaccount"
        label="审核人账户"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="auditusername"
        label="审核人姓名"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="auditdate"
        label="审核时间"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="auditresultName"
        label="审核结果"
        width="100"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="auditstatusName"
        label="审核状态"
        width="100"
        align="center"
      >
      </el-table-column>

      <el-table-column
        label="审核意见"
        align="center"
        width="100"
        fixed="right"
      >
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >审核</el-button
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
  name: "ServiceOpen",
  data() {
    return {
      // 存放查询条件
      formInline: {
        type: "",
        auditstatus: "",
        companyName: "",
      },
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 9,
      //总条数
      pageNumber: 0,
      // 表格数据
      tableData: [],
      // 弹出框宽度
      InsertformLabelWidth: "125px",
    };
  },
  methods: {
    handleDownload() {
      this.$axios
        .get("/api/static/" + this.Updateform.file, {
          responseType: "blob",
        })
        .then((res) => {
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
          let fileName =
            this.Updateform.companyName + this.Updateform.name + value + ".png";
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
        });
    },
    // 导出
    onExport() {
      // 发送查询条件
      let map = this.formInline;
      map.companyid = this.$store.state.userCompanyId;

      this.$axios
        .post("/api/busApply/excelDownload", map, {
          responseType: "blob",
        })
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
            let fileName = "服务开通申请" + value + ".xls";
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
    // 手动点击查询按钮
    onShouSubmit() {
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
    // 查询数据
    onSubmit() {
      let that = this;
      // 创建axios携带参数
      let parmMap = {
        companyName: that.formInline.companyName,
        type: that.formInline.type,
        auditstatus: that.formInline.auditstatus,
        currentPage: this.currentPage,
        pageSize: this.pageSize,
      };
      that.$axios
        .get("/api/busApply/list", {
          params: parmMap,
        })
        .then((res) => {
          // 判断请求是否成功
          if (res.status == 200) {
            // 清空数据表里的内容
            that.tableData.splice(0, that.tableData.length);
            // 循环遍历出后台查询的内容
            for (let list = 1; list <= res.data.request.length; list++) {
              let typeName =
                res.data.request[list - 1].type == 1
                  ? "出单服务"
                  : res.data.request[list - 1].type == 2
                  ? "收单服务"
                  : "签章服务";
              let auditresultName =
                res.data.request[list - 1].auditresult == 1
                  ? "未通过"
                  : "已通过";
              let auditstatusName =
                res.data.request[list - 1].auditstatus == 1
                  ? "未审核"
                  : "已审核";
              let map = {
                xuhao: list + (this.currentPage - 1) * this.pageSize,
                id: res.data.request[list - 1].id,
                type: res.data.request[list - 1].type,
                typeName: typeName,
                companyId: res.data.request[list - 1].companyid,
                companyName: res.data.request[list - 1].companyName,
                applyaccount: res.data.request[list - 1].applyaccount,
                applyusername: res.data.request[list - 1].applyusername,
                applydate: res.data.request[list - 1].applydate,
                auditaccount: res.data.request[list - 1].auditaccount,
                auditusername: res.data.request[list - 1].auditusername,
                auditdate: res.data.request[list - 1].auditdate,
                auditresult: res.data.request[list - 1].auditresult,
                auditopinion: res.data.request[list - 1].auditopinion,
                auditstatus: res.data.request[list - 1].auditstatus,
                auditresultName: auditresultName,
                auditstatusName: auditstatusName,
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
    },
    // 重置条件
    onReset() {
      // 刷新表单验证并查询
      this.formInline.companyName = "";
      this.formInline.type = "";
      this.formInline.auditstatus = "";
      this.onShouSubmit();
    },
    // 修改本行内容,弹出修改框,并将选中的内容展示在模块中
    handleClick(row) {
      let map = {
        id: row.id,
        companyid: row.companyId,
        type: row.type,
        currentPage: 1,
        pageSize: 10,
      };
      this.$axios
        .get("/api/busApply/busApplylist", {
          params: map,
        })
        .then((res) => {
          // 判断请求是否成功
          if (res.status == 200) {
            let lista = [];
            for (let list = 0; list < res.data.filelist.length; list++) {
              let z = {
                fileName: "资质证明" + (list + 1),
                fileLink: "/api/static/" + res.data.filelist[list],
              };
              lista.push(z);
            }
            let listb = [];
            for (let list = 1; list <= res.data.request.length; list++) {
              let typeName =
                res.data.request[list - 1].type == 1
                  ? "出单服务"
                  : res.data.request[list - 1].type == 2
                  ? "收单服务"
                  : "签章服务";
              let auditresultName =
                res.data.request[list - 1].auditresult == 1
                  ? "未通过"
                  : "已通过";
              let auditstatusName =
                res.data.request[list - 1].auditstatus == 1
                  ? "未审核"
                  : "已审核";
              let x = {
                xuhao: list + (this.currentPage - 1) * this.pageSize,
                id: res.data.request[list - 1].id,
                type: res.data.request[list - 1].type,
                typeName: typeName,
                companyId: res.data.request[list - 1].companyid,
                companyName: res.data.request[list - 1].companyName,
                applyaccount: res.data.request[list - 1].applyaccount,
                applyusername: res.data.request[list - 1].applyusername,
                applydate: res.data.request[list - 1].applydate,
                auditaccount: res.data.request[list - 1].auditaccount,
                auditusername: res.data.request[list - 1].auditusername,
                auditdate: res.data.request[list - 1].auditdate,
                auditresult: res.data.request[list - 1].auditresult,
                auditopinion: res.data.request[list - 1].auditopinion,
                auditstatus: res.data.request[list - 1].auditstatus,
                auditresultName: auditresultName,
                auditstatusName: auditstatusName,
                version: res.data.request[list - 1].version,
              };
              listb.push(x);
            }
            let parm = {
              id: row.id,
              lista: lista,
              data: listb,
              isTrue: row.auditresult,
              auditopinion: row.auditopinion,
              auditstatus: row.auditstatus,
              companyid: row.companyid,
              type: row.type,
              pageNumber: res.data.pageNumber,
              currentPage: res.data.currentPage,
              version: row.version,
            };
            let data = JSON.stringify(parm);
            sessionStorage.removeItem("data");
            sessionStorage.setItem("data", [data]);
            this.$router.push({
              path: "Review",
            });
            // 如果为404则显示页面丢失
          } else if (res.status == 404) {
            that.$message.error("页面丢失");
            // 如果为500显示后台或数据库异常
          } else if (res.status == 500) {
            that.$message.error("后台或数据库异常");
          }
        });
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
  mounted() {
    this.onSubmit("formInline");
  },
};
</script>

<style lang="less" scoped>
.ServiceOpen {
  line-height: 0;
  padding: 0px 20px 0px 20px;
  display: inline-block;
  text-align: left;
  width: 95%;
  height: 85%;
  font-weight: bold;
  color: black;
  min-width: 1500px;
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
.el-dialog .el-input {
  width: 62%;
}
</style>
