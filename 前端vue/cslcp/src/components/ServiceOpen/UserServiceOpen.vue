<template>
  <div class="UserServiceOpen">
    <!-- 查询模块 -->
    <el-form
      @submit.native.prevent
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      style="height: 50px "
    >
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
    <!-- 服务新增 -->
    <el-dialog
      title="服务添加"
      :visible.sync="InsertdialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
      width="35%"
    >
      <el-form
        @submit.native.prevent
        :model="Insertform"
        ref="Insertform"
        v-if="InsertdialogFormVisible"
      >
        <el-form-item label="服务类型:">
          <el-select v-model="Insertform.type">
            <el-option value="1" label="出单服务">出单服务</el-option>
            <el-option value="3" label="签章服务">签章服务</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="资质证明上传:">
          <el-upload
            class="upload-demo"
            ref="uploadInsert"
            action=""
            :on-remove="handleRemove"
            :file-list="fileList"
            :auto-upload="false"
            :http-request="httpRequest"
            :limit="10"
          >
            <el-button slot="trigger" size="small" type="primary"
              >选取文件</el-button
            >
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过1MB(仅可上传10个以内)
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="InsertdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpload">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改本行内容 -->
    <el-dialog
      width="35%"
      @submit.native.prevent
      title="修改本行内容"
      :visible.sync="UpdatedialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="Updateform"
        ref="Updateform"
        v-if="UpdatedialogFormVisible"
      >
        <el-form-item label="服务类型:">
          <el-select v-model="Updateform.type">
            <el-option value="1" label="出单服务">出单服务</el-option>
            <el-option value="3" label="签章服务">签章服务</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-table
            :data="Updateform.files"
            height="150"
            border
            style="width:501px;display:inline-block"
          >
            <el-table-column
              :resizable="false"
              show-overflow-tooltip
              label="原资质证明"
              width="500"
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
        </el-form-item>

        <el-form-item label="原资质证明下载:">
          <el-button size="small" type="primary" @click="handleDownload"
            >下载原文件</el-button
          >
        </el-form-item>

        <el-form-item label="资质证明上传:">
          <el-upload
            class="upload-demo"
            ref="uploadUpdata"
            action=""
            :on-remove="handleRemove1"
            :file-list="fileList1"
            :auto-upload="false"
            :http-request="httpRequest1"
            :limit="10"
          >
            <el-button slot="trigger" size="small" type="primary"
              >选取文件</el-button
            >
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过1MB(仅可上传10个以内)
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="UpdatedialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onUpdataFirst()">确 定</el-button>
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
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
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
        width="150"
        align="center"
        fixed
      >
      </el-table-column>
      <el-table-column
        prop="applyaccount"
        label="申请人账户"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="applyusername"
        label="申请人名称"
        width="150"
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
        width="180"
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
        prop="auditopinion"
        label="审核意见"
        width="200"
        align="center"
        :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column label="操作" align="center" width="100" fixed="right">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >修改</el-button
          >
          <el-button @click="DeleteOne(scope.row)" type="text" size="small"
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
  name: "UserServiceOpen",
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
      // 插入弹出框是否显示
      InsertdialogFormVisible: false,
      // 存放被选中的id
      multipleSelection: [],
      // 是否删除
      Deletevisible: false,
      closeOnClickModal: false,
      // 插入表单数据
      Insertform: {
        type: "1",
        file: [],
        companyid: this.$store.state.userCompanyId,
        applyaccount: this.$store.state.userCompanyPosition,
        applyusername: this.$store.state.userCompanyPosition,
        files: "",
      },
      // 上传数据
      fileList: [],
      // 更改数据
      fileList1: [],
      // 修改功能所需要的属性
      // 是否弹出修改框
      UpdatedialogFormVisible: false,
      // 修改的字段
      Updateform: {
        id: 0,
        type: "",
        // 渲染原文件
        files: [],
        version: 0,
        // 上传新文件
        file: [],
        filesU: "",
        // 修改人及为申请人
        applyaccount: this.$store.state.userCompanyPosition,
        applyusername: this.$store.state.userCompanyPosition,
      },
    };
  },
  methods: {
    // 修改第一步
    onUpdataFirst() {
      let that = this;
      if (this.$refs.uploadUpdata.uploadFiles.length == 0) {
        this.$message({
          message: "您选择不整改原资质文件！",
          type: "warning",
        });
        this.onUpdata();
      } else {
        var upData = new FormData();
        this.Updateform.file.splice(0, this.Updateform.file.length);
        this.$refs.uploadUpdata.submit(); // 这里是执行文件上传的函数，其实也就是获取我们要上传的文件
        this.Updateform.file.forEach(function(file) {
          // 因为要上传多个文件，所以需要遍历
          upData.append("file", file, file.name);
          // upData.append('file', this.file); //不要直接使用我们的文件数组进行上传，你会发现传给后台的是两个Object
        });
        upData.append("body", JSON.stringify(this.newData)); // 这里需要转换一下格式传给后台

        this.$axios
          .post("/api/file/importfiles", upData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then(function(res) {
            that.Updateform.filesU = res.data;
            that.onUpdata();
          })
          .catch(function(error) {
            that.$message.error("系统发现未知错误，请联系管理员修复！");
          });
      }
    },
    // 修改第二步
    onUpdata() {
      let that = this;
      // 修改数据并返回结果
      // 如果校验通过，请求接口，允许提交表单
      let map = {
        id: this.Updateform.id,
        type: this.Updateform.type,
        companyid: this.Updateform.companyid,
        applyaccount: this.Updateform.applyaccount,
        applyusername: this.Updateform.applyusername,
        files: this.Updateform.filesU,
        versio: this.Updateform.version,
      };
      that.$axios
        .get("/api/busApply/edit", {
          params: map,
        })
        .then((res) => {
          // 判断是否请求成功
          if (res.status == 200) {
            // 判断后台结果是否成功
            if (res.data.status == 1) {
              // 关闭弹出框
              that.UpdatedialogFormVisible = false;
              // 重新查出数据
              that.onSubmit("formInline");
              this.$message({
                message: "修改成功",
                type: "success",
              });
              // 如果已经存在同等梯度
            } else if (res.data.status == 2) {
              that.$message.error(res.data.msg);
            } else if (res.data.status == 3) {
              that.$message.error(res.data.msg);
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
    },
    //   删除一行
    DeleteOne(row) {
      let multis = [row.id];
      let that = this;
      // 发送请求删除本id
      that
        .$axios({
          url: "/api/busApply/remove",
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
              that.$message.error("删除失败，请重试！");
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
    // 删除多行
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
            url: "/api/busApply/remove",
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
                this.$message.error("删除失败，请重试！");
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
    // 上传文件函数
    submitUpload() {
      let that = this;
      if (this.$refs.uploadInsert.uploadFiles.length == 0) {
        this.$message.error("请上传文件！");
      } else {
        var upData = new FormData();
        this.Insertform.file.splice(0, this.Insertform.file.length);
        this.$refs.uploadInsert.submit(); // 这里是执行文件上传的函数，其实也就是获取我们要上传的文件
        this.Insertform.file.forEach(function(file) {
          // 因为要上传多个文件，所以需要遍历
          upData.append("file", file, file.name);
        });
        upData.append("body", JSON.stringify(this.newData)); // 这里需要转换一下格式传给后台

        this.$axios
          .post("/api/file/importfiles", upData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then(function(res) {
            that.Insertform.files = res.data;
            that.onInsert("Insertform");
          })
          .catch(function(error) {
            that.$message.error("系统发现未知错误，请联系管理员修复！");
          });
      }
    },
    handleRemove(file, fileList) {
      this.fileList = fileList;
    },
    handleRemove1(file, fileList) {
      this.fileList1 = fileList;
    },
    // 下载本模块的凭证
    handleDownload() {
      let idid = this.Updateform.id;
      this.$axios({
        url: "/api/busApply/zipDownload",
        method: "POST",
        headers: { "Content-Type": "application/json" },
        responseType: "blob",
        dataType: "json",
        data: idid,
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
        companyid: this.$store.state.userCompanyId,
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
    // 添加数据
    onInsert() {
      let that = this;
      // 添加数据并返回结果
      // 如果校验通过，请求接口，允许提交表单
      let map = {
        type: this.Insertform.type,
        companyid: this.Insertform.companyid,
        applyaccount: this.Insertform.applyaccount,
        applyusername: this.Insertform.applyusername,
        files: this.Insertform.files,
      };
      that.$axios
        .get("/api/busApply/add", {
          params: map,
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
              // 如果已经存在同等梯度
            } else if (res.data.status == 2) {
              that.$message.error(res.data.msg);
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
    },

    // 重写请求方法
    httpRequest(param) {
      this.Insertform.file.push(param.file); // 一般情况下是在这里创建FormData对象，但我们需要上传多个文件，为避免发送多次请求，因此在这里只进行文件的获取，param可以拿到文件上传的所有信息
    },
    httpRequest1(param) {
      this.Updateform.file.push(param.file); // 一般情况下是在这里创建FormData对象，但我们需要上传多个文件，为避免发送多次请求，因此在这里只进行文件的获取，param可以拿到文件上传的所有信息
    },
    // 重置条件
    onReset() {
      // 刷新表单验证并查询
      this.formInline.type = "";
      this.formInline.auditstatus = "";
      this.onShouSubmit();
    },
    // 修改本行内容,弹出修改框,并将选中的内容展示在模块中+
    handleClick(row) {
      this.Updateform.id = row.id;
      this.Updateform.companyid = row.companyId;
      this.Updateform.type = row.type;
      this.Updateform.version = row.version;
      this.$axios
        .get("/api/busApply/byid?id=" + row.id)
        .then((res) => {
          this.Updateform.files.splice(0, this.Updateform.files.length);
          for (let list = 0; list < res.data.filelist.length; list++) {
            let li = {};
            li.fileLink = "/api/static/" + res.data.filelist[list];
            li.fileName = "资质证明" + (list + 1);
            this.Updateform.files.push(li);
          }
        })
        .catch((err) => {
          this.$message.error("系统发现未知错误，请联系管理员修复！");
        });
      this.UpdatedialogFormVisible = true;
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
    // 检测选的页是否改变
    currentPage() {
      this.onSubmit("formInline");
    },
    // 每页多少条
    pageSize() {
      this.currentPage = 1;
      this.onSubmit("formInline");
    },
    InsertdialogFormVisible() {
      this.fileList.splice(0, this.fileList.length);
    },
    UpdatedialogFormVisible() {
      this.fileList1.splice(0, this.fileList1.length);
    },
  },
  mounted() {
    this.onSubmit("formInline");
  },
};
</script>

<style lang="less" scoped>
.UserServiceOpen {
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
  .el-dialog {
    .el-table {
      /deep/ th {
        padding: 0;
      }
      /deep/ td {
        padding: 0;
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
.el-dialog .el-input {
  width: 62%;
}
</style>
<style>
.el-tooltip__popper {
  width: 30%; /*修改宽度*/
  background: #000 !important; /*背景色  !important优先级*/
  color: #ffffff !important; /*字体颜色*/
}
</style>
