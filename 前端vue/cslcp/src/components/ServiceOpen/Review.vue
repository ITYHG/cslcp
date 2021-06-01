<template>
  <div class="Review">
    <el-col :span="24">
      <el-card id="ReviewTop" shadow="never">
        <el-form
          @submit.native.prevent
          :inline="true"
          :model="formInline"
          :rules="Seachrules"
          class="demo-form-inline"
          ref="formInline"
        >
          <el-form-item label="是否通过:">
            <el-select
              v-model="formInline.isTrue"
              style="width:100px"
              :disabled="fatherparams.auditstatus == 2"
            >
              <el-option value="1" label="不通过">不通过</el-option>
              <el-option value="2" label="通过">通过</el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="审批意见" prop="auditopinion">
            <el-input
              :readonly="fatherparams.auditstatus == 2"
              class="Rtextarea"
              type="textarea"
              resize="none"
              style="width:400px"
              rows="8"
              v-model="formInline.auditopinion"
            ></el-input>
          </el-form-item>
          <el-table
            :data="tableData1"
            height="180"
            border
            style="width:500px;display:inline-block"
          >
            <el-table-column
              show-overflow-tooltip
              label="文件名称"
              width="498px"
              align="center"
            >
              <template slot-scope="scope">
                <span class="span-text"
                  ><el-link :href="scope.row.fileLink" target="_blank">{{
                    scope.row.fileName
                  }}</el-link></span
                >
              </template>
            </el-table-column>
          </el-table>
          <el-form-item class="caozuo">
            <el-button
              :disabled="fatherparams.auditstatus == 2"
              type="primary"
              @click="onSubIstrue('formInline')"
              icon="el-icon-upload2"
              >提交</el-button
            >
            <el-button @click="downloadAll" icon="el-icon-download"
              >下载</el-button
            >

            <el-button @click="goleft" icon="el-icon-arrow-left"
              >返回</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>

      <el-card id="ReviewTable" shadow="never">
        <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100% overflow-y: auto;"
          :show-header="true"
          :height="400"
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
            width="200"
            align="center"
            fixed
          >
          </el-table-column>

          <el-table-column
            prop="applydate"
            label="申请时间"
            width="150"
            align="center"
          ></el-table-column>
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
            width="150"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="auditopinion"
            label="审核意见"
            width="550"
            align="center"
            :show-overflow-tooltip="true"
          >
          </el-table-column>
          <el-table-column
            prop="auditstatusName"
            label="审核状态"
            width="150"
            align="center"
          >
          </el-table-column>

          <el-table-column
            label="文件下载"
            align="center"
            width="100"
            fixed="right"
          >
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="text"
                size="small"
                >下载</el-button
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
            :page-sizes="[10, 20, 50]"
            :page-size="pageSize"
            layout="sizes, prev, pager, next, jumper"
            :total="pageNumber"
          >
          </el-pagination>
        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "Review",
  data() {
    return {
      // 当前页
      currentPage: 1,
      // 每页多少条
      pageSize: 10,
      //总条数
      pageNumber: "",
      // 当前行id
      id: "",
      // 将父亲传给的值保存起来
      fatherparams: "",
      // 存放提交条件
      formInline: {
        isTrue: "",
        auditopinion: "",
        auditstatus: "",
        version: "",
      },
      Seachrules: {
        auditopinion: [
          { max: 200, message: "长度需小于200个字符", trigger: "blur" },
        ],
      },
      tableData1: [],
      tableData: [],
      companyName: "",
    };
  },
  methods: {
    onSubIstrue(formInline) {
      let that = this;
      // 表单校验
      that.$refs[formInline].validate((valid) => {
        //开启校验
        if (valid) {
          // 创建axios携带参数
          // this.fatherparams.id
          let parmMap = {
            id: that.id,
            auditresult:
              that.formInline.isTrue == "不通过" ? 1 : that.formInline.isTrue,
            auditopinion: that.formInline.auditopinion,
            auditstatus: 2,
            auditaccount: this.$store.state.userCompanyPosition,
            auditusername: this.$store.state.userCompanyPosition,
            version: that.formInline.version,
          };
          that.$axios
            .get("/api/busApply/edit", {
              params: parmMap,
            })
            .then((res) => {
              // 判断请求是否成功
              if (res.status == 200) {
                this.$message({
                  message: "审核成功",
                  type: "success",
                });
                sessionStorage.removeItem("data");
                this.$router.push("ServiceOpen");
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

    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    // 下载本行资质证明
    handleClick(row) {
      this.$axios({
        url: "/api/busApply/zipDownload",
        method: "POST",
        headers: { "Content-Type": "application/json" },
        responseType: "blob",
        dataType: "json",
        data: JSON.stringify(row.id),
      }).then((res) => {
        const blob = new Blob([res.data], { type: "application/zip" });
        let aData = new Date();
        // 获取当前日期格式
        let value =
          aData.getFullYear() +
          "-" +
          (aData.getMonth() + 1) +
          "-" +
          aData.getDate();
        let fileName = "资质证明材料" + value + ".zip";
        const downloadElement = document.createElement("a");
        const href = window.URL.createObjectURL(blob); //创建下载的链接
        downloadElement.href = href;
        [downloadElement.download] = [fileName];
        document.body.appendChild(downloadElement);
        downloadElement.click(); //点击下载
        document.body.removeChild(downloadElement); //下载完成移除元素
        window.URL.revokeObjectURL(href); //释放blob对象
      });
    },
    // 下载所有凭证
    downloadAll() {
      let that = this;
      this.$axios({
        url: "/api/busApply/zipDownload",
        method: "POST",
        headers: { "Content-Type": "application/json" },
        responseType: "blob",
        dataType: "json",
        data: that.id,
      }).then((res) => {
        const blob = new Blob([res.data], { type: "application/zip" });
        let aData = new Date();
        // 获取当前日期格式
        let value =
          aData.getFullYear() +
          "-" +
          (aData.getMonth() + 1) +
          "-" +
          aData.getDate();
        let fileName = "资质证明材料" + value + ".zip";
        const downloadElement = document.createElement("a");
        const href = window.URL.createObjectURL(blob); //创建下载的链接
        downloadElement.href = href;
        [downloadElement.download] = [fileName];
        document.body.appendChild(downloadElement);
        downloadElement.click(); //点击下载
        document.body.removeChild(downloadElement); //下载完成移除元素
        window.URL.revokeObjectURL(href); //释放blob对象
      });
    },
    goleft() {
      sessionStorage.removeItem("data");
      this.$router.push("ServiceOpen");
    },
    getRouteData() {
      this.fatherparams = JSON.parse(sessionStorage.getItem("data"));

      this.formInline.isTrue =
        this.fatherparams.isTrue == 1 ? "不通过" : "通过";
      this.formInline.auditopinion = this.fatherparams.auditopinion;
      this.formInline.auditstatus = this.fatherparams.auditstatus;
      this.formInline.version = this.fatherparams.version;
      this.pageNumber = this.fatherparams.pageNumber;
      this.currentPage = this.fatherparams.currentPage;
      this.tableData1 = this.fatherparams.lista;
      this.tableData = this.fatherparams.data;
      this.id = this.fatherparams.id;
    },
    // 查询
    onSubmit() {
      let that = this;
      // 创建axios携带参数
      let parmMap = {
        id: that.id,
        companyid: this.fatherparams.companyid,
        type: this.fatherparams.type,
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
            if (res.data.status == 1) {
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
            } else {
              that.$message.error(res.data.msg);
            }
          }
        })
        .catch((err) => {
          this.$message.error("系统发现未知错误，请联系管理员修复！");
        });
    },
    goBack() {
      sessionStorage.removeItem("data");
      this.$router.push("ServiceOpen");
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
  beforeMount() {
    this.getRouteData();
  },
  mounted() {
    if (window.history && window.history.pushState) {
      history.pushState(null, null, document.URL);
      window.addEventListener("popstate", this.goBack, false);
    }
  },
  destroyed() {
    window.removeEventListener("popstate", this.goBack, false);
  },
};
</script>

<style lang="less" scoped>
.Review {
  .el-table {
    /deep/ th {
      padding: 0;
    }
    /deep/ td {
      padding: 0;
    }
  }
  .caozuo {
    margin: 50px 60px;
  }
  .count-show {
    // margin-top: 40px;
    display: inline-block;
    font-weight: lighter;
    font-size: 13px;
    font-weight: 400;
    color: #606266;
  }
  .el-pagination {
    position: relative;
    top: 10px;
    display: inline-block;
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

#ReviewTop {
  height: 220px;
}
#ReviewTable {
  margin-top: 20px;
}
</style>
<style>
.el-tooltip__popper {
  width: 30%; /*修改宽度*/
  background: #000 !important; /*背景色  !important优先级*/
  color: #ffffff !important; /*字体颜色*/
}
</style>
