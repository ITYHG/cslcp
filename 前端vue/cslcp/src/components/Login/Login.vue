<template>
  <div id="mainDiv" :style="{ backgroundImage: 'url(' + backgroundImg + ')' }">
    <h1 style="color:#FFF">
      企业管理员-账号：张三，平台管理员-账号：admin，平台工作人员-账号：staff，密码都为：123456
    </h1>
    <el-card shadow="always" id="loginWindow">
      <h2>登 录</h2>
      <el-divider></el-divider>
      <el-form :model="loginForm" :rules="rules" ref="loginForm">
        <el-form-item prop="userName">
          <el-input
            prefix-icon="el-icon-user"
            @keyup.enter.native="submitForm()"
            v-model="loginForm.userName"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-key"
            @keyup.enter.native="submitForm()"
            type="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="registered()">注册</el-button>
          <el-button type="primary" class="subButton" @click="submitForm()"
            >登陆</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
    <!-- 注册 -->
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    //用户名验证规则
    let checkUserName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      }
      callback();
    };
    //密码验证规则
    let validatePass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("密码不能为空"));
      }
      callback();
    };
    return {
      form: {
        name: "",
      },
      // 背景图片
      backgroundImg: require("@/assets/imges/background.png"),
      loginForm: {
        password: "",
        userName: "",
      },
      rules: {
        password: [{ validator: validatePass, trigger: "blur" }],
        userName: [{ validator: checkUserName, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm() {
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          this.$axios({
            url: "/api/user/login",
            method: "POST",
            headers: { "Content-Type": "application/json" },
            //async:false,
            dataType: "json",
            data: JSON.stringify(this.loginForm),
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
                  this.$store.commit("isLogin", true);
                  let one = this.$store.dispatch(
                    "menu",
                    this.loginForm.userName
                  );
                  let two = this.$store.dispatch(
                    "userPromission",
                    this.loginForm.userName
                  );
                  let p = Promise.all([one, two]);
                  p.then((res) => {
                    sessionStorage.setItem("state", this.$store.state);
                    this.$router.push(this.$store.state.menu[0].path);
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
      });
    },
    registered() {
      this.$message({
        message: "功能还在开发中，敬请期待！",
        type: "warning",
      });
    },
  },
};
</script>
<style lang="less" scoped>
#mainDiv {
  background-size: 100% 100%;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  margin: 0;
  padding: 0;
  overflow: hidden;
  text-align: center;

  #loginWindow {
    height: 350px !important;
    width: 600px;
    position: absolute;
    left: 50%;
    top: 300px;
    transform: translate(-50%);
    background: #ffffff;
    border-radius: 15px;
    opacity: 0.9;
    .el-form {
      z-index: 1;
      font-family: Helvetica;
      padding: 0 45px 30px 45px;
      text-align: center;
      border-radius: 10px;
      * {
        font-size: 18px;
        font-weight: bold;
      }
      .el-form-item {
        margin: 10px 0 40px 0;
      }
    }
    .el-driver {
      margin: 10px 0 10px 0;
    }
    button {
      margin: 0 20px;
      width: 90px;
    }
  }
  .el-form button:hover,
  .el-form button:active,
  .el-form button:focus {
    transform: scale(1.1);
  }
}

/* 
#formDiv {
  padding-top: 10%;
  padding-left: 10px;
  padding-right: 10px;
}

.subButton {
  margin-top: 20px;
  width: 100%;
} */
</style>
