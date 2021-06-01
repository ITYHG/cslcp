<template>
  <div id="app" :style="contentStyleObj">
    <router-view />
  </div>
</template>
<script>
import Home from "./views/Home";
export default {
  components: {
    Home,
  },
  data() {
    return {
      contentStyleObj: {
        height: "",
      },
    };
  },

  methods: {
    getHeight() {
      this.contentStyleObj.height = window.innerHeight + "px";
    },
    getClientHeight() {
      var clientHeight = 0;
      if (document.body.clientHeight && document.documentElement.clientHeight) {
        var clientHeight =
          document.body.clientHeight < document.documentElement.clientHeight
            ? document.body.clientHeight
            : document.documentElement.clientHeight;
      } else {
        var clientHeight =
          document.body.clientHeight > document.documentElement.clientHeight
            ? document.body.clientHeight
            : document.documentElement.clientHeight;
      }
      return clientHeight;
    },
  },
  created() {
    window.addEventListener("resize", this.getHeight);
    this.getHeight();
    if (sessionStorage.getItem("state")) {
      this.$store.replaceState(
        Object.assign(
          {},
          this.$store.state,
          JSON.parse(sessionStorage.getItem("state"))
        )
      );
    }
    window.addEventListener("beforeunload", () => {
      sessionStorage.setItem("state", JSON.stringify(this.$store.state));
    });
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight);
  },
};
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
* {
  margin: 0;
  padding: 0;
}
a {
  text-decoration: none;
  color: #333;
}

a:hover,
a:visited,
a:link,
a:active {
  color: #333;
  // 设置使所有a标签的四种状态都和本身颜色保持一致，样式代码自己写
}

ul,
ol {
  list-style: none;
}

img {
  vertical-align: top;
  border: none;
}

.button {
  border: 0;
  background-color: none;
  outline: none;
  -webkit-appearance: none; //用于IOS下移除原生样式
}

select {
  border: none;
  appearance: none;
  -moz-appearance: none;
  -webkit-appearance: none;
  /*在选择框的最右侧中间显示小箭头图片*/
  background: url("http://ourjs.github.io/static/2015/arrow.png") no-repeat
    scroll right center transparent;
  padding-right: 14px;
}

input,
textarea {
  outline: none;
  border: none;
}

textarea {
  resize: none;
  overflow: auto;
}

i {
  font-style: normal;
}

/*去除input[type=number]加减号*/
/* 谷歌 */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  appearance: none;
  margin: 0;
}

/* 火狐 */
input {
  -moz-appearance: textfield;
}
body {
  height: 100%;
  min-width: 1200px;
}
</style>
