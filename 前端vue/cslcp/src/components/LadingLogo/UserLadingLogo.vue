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
    <!-- 增加，删除 -->
    <div class="Operation">
      <el-button
        class="addStyleTwo"
        type="primary"
        @click="addData()"
        icon="el-icon-plus"
        :disabled="isDisabled"
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
          :disabled="isDisabled"
          >删除</el-button
        >
      </el-popconfirm>
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
      <el-table-column prop="name" label="Logo名称" width="200" align="center">
      </el-table-column>
      <el-table-column
        prop="uploadday"
        label="上传日期"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="isenable"
        label="启用状态"
        width="180"
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
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="ToExamine(scope.row)"
            class="el-button-del"
            align="center"
            >修改</el-button
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
    <!-- 添加弹出框 -->
    <el-dialog
      title="新增LOGO图标"
      :visible.sync="dialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="form"
        :rules="formRules"
        ref="form"
        @submit.native.prevent
        v-if="dialogFormVisible"
      >
        <!-- Logo 名称 -->
        <el-form-item
          label="LOGO名称："
          :label-width="formLabelWidth"
          prop="name"
        >
          <el-input
            v-model="form.name"
            autocomplete="off"
            placeholder="请输入LOGO名称"
            @keyup.enter.native="onInsert('form')"
          ></el-input>
        </el-form-item>
        <!-- 确认状态 -->
        <el-form-item
          label="确认状态："
          :label-width="formLabelWidth"
          prop="comfirmstatus"
        >
          <el-select
            v-model="form.addForms.comfirmstatus"
            :label="form.addForms.label"
            :disabled="true"
          >
            <el-option
              v-for="item in updataFormsCon"
              :key="item.comfirmstatus"
              :label="item.label"
              :value="item.comfirmstatus"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 审批意见 -->
        <el-form-item
          label="审批意见："
          :label-width="formLabelWidth"
          prop="comments"
        >
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="form.comments"
            resize="none"
            overflow-y="auto"
            :disabled="true"
          >
          </el-input>
        </el-form-item>
        <!-- logo 图片 -->
        <div class="block">
          <el-card
            shadow="always"
            style="width: 100%; height: 100%; margin-left: 30px"
          >
            <el-image :src="'/api/static/' + form.file" alt="查看">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </el-card>
        </div>
        <!-- 上传 -->
        <el-upload
          class="upload-demo upload-demo-two"
          ref="uploadInsert"
          action="/api/file/import"
          :on-success="chenggong"
          :on-remvoe="handleRemove"
          :on-error="ONerror"
          :file-list="form.fileList"
          :auto-upload="false"
          :limit="1"
        >
          <el-button size="small" type="primary">点 击 上 传</el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件，且不超过1MB(仅可以上传一个)
          </div>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('form')">取 消</el-button>
        <el-button type="primary" @click="addSubmit">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改弹出框 -->
    <el-dialog
      width="35%"
      title="提单logo修改"
      :visible.sync="updateDialogFormVisible"
      :closeOnClickModal="closeOnClickModal"
    >
      <el-form
        :model="updataForm"
        :rules="updataFormRules"
        ref="updataForm"
        @submit.native.prevent
        v-if="updateDialogFormVisible"
      >
        <!-- 公司名称 -->
        <el-form-item
          label="公司名称："
          :label-width="formLabelWidth"
          prop="companyName"
        >
          <el-input
            @keyup.enter.native="onUpdata('updataForm')"
            v-model="updataForm.companyName"
            autocomplete="off"
            placeholder="请输入公司名称"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <!-- logo 名称 -->
        <el-form-item
          label="logo名称："
          :label-width="formLabelWidth"
          prop="companyName"
        >
          <el-input
            @keyup.enter.native="onUpdata('updataForm')"
            v-model="updataForm.name"
            autocomplete="off"
            placeholder="请输入logo名称"
            :disabled="
              (updataForm.isConfirmState.comfirmstatus == 1 ? false : true) ||
                (updataForm.isConfirmState.comfirmstatus != 1 ? true : false)
            "
          ></el-input>
        </el-form-item>

        <!-- 启用状态 -->
        <el-form-item
          label="启用状态："
          :label-width="formLabelWidth"
          prop="isenable"
        >
          <el-select
            v-model="updataForm.isEnableState.isenable"
            :label="updataForm.isEnableState.label"
            :disabled="
              updataForm.isConfirmState.comfirmstatus == 1 ||
              updataForm.isConfirmState.comfirmstatus == 3
                ? true
                : false
            "
          >
            <el-option
              v-for="item in updataForms"
              :key="item.isenable"
              :label="item.label"
              :value="item.isenable"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 确认状态 -->
        <el-form-item
          label="确认状态："
          :label-width="formLabelWidth"
          prop="comfirmstatus"
        >
          <el-select
            v-model="updataForm.isConfirmState.comfirmstatus"
            :label="updataForm.isConfirmState.label"
            :disabled="isDisabled"
          >
            <el-option
              v-for="item in updataFormsCon"
              :key="item.comfirmstatus"
              :label="item.label"
              :value="item.comfirmstatus"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 审批意见   多行文本 -->
        <el-form-item
          label="审批意见："
          :label-width="formLabelWidth"
          prop="comments"
          :disabled="isDisabled"
        >
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="updataForm.comments"
            resize="none"
            overflow-y="auto"
            :disabled="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="LOGO预览"
          :label-width="formLabelWidth"
          prop="file"
        >
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
          <!-- 上传 -->
          <el-upload
            class="upload-demo"
            ref="upload"
            action="/api/file/import"
            :on-success="onSuccess"
            :on-remvoe="handleRemove"
            :on-error="ONerror"
            :file-list="updataForm.fileList"
            :auto-upload="false"
            :limit="1"
            :disabled="
              (updataForm.isConfirmState.comfirmstatus == 1 ? false : true) ||
                (updataForm.isConfirmState.comfirmstatus != 1 ? true : false)
            "
          >
            <el-button size="small" type="primary">点 击 上 传</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过1MB(仅可以上传一个)
            </div>
          </el-upload>
          <div v-if="true">
            <el-button
              class="downloadBtn"
              slot="trigger"
              size="small"
              type="primary"
              @click="handleDownload"
              :disabled="isDisabled"
              >下载LOGO</el-button
            >
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetUpdataForm('updataForm')">取 消</el-button>
        <el-button type="primary" @click="submitFile">确 定</el-button>
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
      const Chinese = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/;
      if (value == "") {
        callback();
      } else if (value.length > 6) {
        callback(new Error("长度小于6"));
      } else if (!Chinese.test(value)) {
        callback(new Error("请以汉字或字母开头,不能含有特殊字符"));
      } else {
        callback();
      }
    };
    return {
      // 新增信息
      form: {
        // logo 名称
        name: "",
        // 确认状态
        addForms: {
          comfirmstatus: "",
          label: "",
        },
        // 审批意见
        comments: "",
        file: "",
        fileList: [],
      },
      // 添加表的验证
      formRules: {
        name: [
          {
            required: true,
            message: "请输入LOGO名称",
            trigger: "blur",
          },
          {
            validator: isMaxTwo,
            trigger: "blur",
          },
        ],
      },
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
        companyName: "",
        name: "",
        isEnableState: {
          isenable: "",
          label: "",
        },
        isConfirmState: {
          comfirmstatus: "",
          label: "",
        },

        companyid: "",
        version: "",
        // 审批意见
        comments: "",
        file: "",
        id: "",
        // 图片路径
        fileList: [],
      },
      // 修改弹框的启用状态的下拉选项
      updataForms: [
        {
          isenable: "2",
          label: "未启用",
        },
        {
          isenable: "1",
          label: "启用",
        },
      ],
      // 修改弹框的确认状态的下拉选项
      updataFormsCon: [
        {
          comfirmstatus: "1",
          label: "待审核",
        },
        {
          comfirmstatus: "2",
          label: "平台已确认",
        },
        {
          comfirmstatus: "3",
          label: "平台已否认",
        },
        {
          comfirmstatus: "4",
          label: "确认",
        },
      ],
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
      // 是否弹出新增框
      dialogFormVisible: false,
      // 修改的规则
      updataFormRules: {},
      // 序号
      tableData: [],
      // 存放被选中的id
      multipleSelection: [],
      // 点击空白处，不能够关闭弹出框
      closeOnClickModal: false,
      // 修改的弹出框
      updateDialogFormVisible: false,
      // 宽度
      formLabelWidth: "120px",
      // 是否禁用,是否显示
      isDisabled: false,
      // 重置添加表格
      resetAddData: false,
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
      this.form.name = "";
      this.form.file = "";
      this.form.addForms.comfirmstatus = "1";
      this.form.addForms.label = "待审核";
      this.dialogFormVisible = true;
      this.resetAddData = false;
    },
    // 添加数据
    onInsert(Insertform) {
      this.$refs[Insertform].validate((valid) => {
        // 如果通过校验
        if (valid) {
          let map = {
            name: this.form.name,
            comfirmStatus: this.form.addForms.comfirmstatus,
            label: this.form.addForms.label,
            comments: this.form.comments,
            file: this.form.file,
            companyid: this.$store.state.userCompanyId,
          };
          this.$axios
            .get("/api/bllogo/add", {
              params: map,
            })
            .then((res) => {
              if (res.status == 200) {
                if (res.data.status == 1) {
                  this.dialogFormVisible = false;
                  this.resultFromCompanyId("form");
                  this.$message({
                    message: "添加成功",
                    type: "success",
                  });
                } else if (res.data.status == 2) {
                  this.$message.error("LOGO名称重复，请更改LOGO名称");
                  this.dialogFormVisible = false;
                } else {
                  this.$message.error("系统发现错误，请联系管理人员");
                  this.dialogFormVisible = false;
                }
              } else if (res.status == 404) {
                this.$message.error("页面丢失");
                this.dialogFormVisible = false;
              } else if (res.status == 500) {
                this.$message.error("数据库异常");
                this.dialogFormVisible = false;
              }
            })
            .catch((err) => {
              this.$message.error("请联系管理人员进行修复");
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
          url: "/api/bllogo/remove",
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
              } else if (resp.data.status == 2) {
                this.$message.error("不可删除已被平台确认的logo");
                this.Reset("formInline");
              } else {
                this.currentPage = 1;

                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                this.resultFromCompanyId("formInline");
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
    // 点击修改
    ToExamine(row) {
      let isenable;
      let comfirmstatus;
      this.updateDialogFormVisible = true;
      if (row.isenable == "启用") {
        isenable = "1";
      } else if (row.isenable == "未启用") {
        isenable = "2";
      }

      if (row.confirmstatus == "待审核") {
        comfirmstatus = "1";
      } else if (row.confirmstatus == "平台已确认") {
        comfirmstatus = "2";
      } else if (row.confirmstatus == "平台已否认") {
        comfirmstatus = "3";
      } else if (row.comfirmstatus == "确认") {
        comfirmstatus = "4";
      }
      this.updataForms.forEach((element) => {
        if (element.isenable === isenable) {
          this.updataForm.isEnableState.isenable = element.isenable;
          this.updataForm.isEnableState.label = element.label;
        }
      });
      this.updataFormsCon.forEach((element) => {
        if (element.comfirmstatus == comfirmstatus) {
          this.updataForm.isConfirmState.comfirmstatus = element.comfirmstatus;
          this.updataForm.isConfirmState.label = element.label;
        }
      });
      this.updataForm.companyName = row.companyName;
      this.updataForm.name = row.name;
      this.updataForm.comments = row.comments;
      this.updataForm.file = row.file;
      this.updataForm.companyid = row.companyid;
      this.updataForm.version = row.version;
      this.updataForm.id = row.id;
    },
    // 更新
    onUpdata(updataForm) {
      // this.$store.state.userCompanyId
      this.$refs[updataForm].validate((valid) => {
        let map = {
          id: this.updataForm.id,
          file: this.updataForm.file,
          confirmstatus: this.updataForm.isConfirmState.comfirmstatus,
          comments: this.updataForm.comments,
          version: this.updataForm.version,
          companyid: this.updataForm.companyid,
          companyName: this.updataForm.companyName,
          name: this.updataForm.name,
          isenable: this.updataForm.isEnableState.isenable,
          // companyId:this.$store.state.userCompanyId
        };
        if (valid) {
          this.$axios
            .get("/api/bllogo/edit", { params: map })
            .then((res) => {
              if (res.status == 200) {
                if (res.data.status == 1) {
                  this.updateDialogFormVisible = false;
                  this.$message({
                    message: "修改审核成功",
                    type: "success",
                  });
                  this.resultFromCompanyId("updataForm");
                } else if (res.data.status == 2) {
                  this.$message.error("LOGO名称重复，请更改LOGO名称");
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
    // 取消按钮
    resetUpdataForm(updataForm) {
      this.$refs[updataForm].resetFields();
      this.updateDialogFormVisible = false;
    },
    // 上传成功
    onSuccess(response, file, fileList) {
      this.updataForm.file = response;
      this.onUpdata("updataForm");
    },
    // 清除上传的图片
    handleRemove(file) {
      this.$refs.upload.clearfiles();
    },
    //  提交文件
    submitFile() {
      if (this.$refs.upload.uploadFiles.length == 0) {
        this.onUpdata("updataForm");
      } else {
        this.$refs.upload.submit();
      }
    },
    // 增加数据，提交文件
    addSubmit() {
      if (this.$refs.uploadInsert.uploadFiles.length != 0) {
        // this.onInsert('form')
        this.$refs.uploadInsert.submit();
      } else {
        this.$message.error("请选择图片进行上传");
      }
    },
    // 添加成功，
    chenggong(response, file, fileList) {
      this.form.file = response;
      this.onInsert("form");
    },

    // 下载logo
    handleDownload() {
      this.$axios
        .get("/api/static/" + this.updataForm.file, {
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
          let fileName = "印章原文件" + value + ".png";
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
    // 重置添加的表单验证
    resetForm(form) {
      this.$refs[form].resetFields();
      this.dialogFormVisible = false;
      this.resetAddData = true;
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
    width: 150px;
    height: 150px !important;
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
