module.exports = {
    //axios域代理，解决axios跨域问题
    devServer: {
      port: 8080,
      proxy: {
        //配置跨域
        "/api": {
          //target: 'http://26073jg409.wicp.vip:38596/',  //这里后台的地址模拟的;应该填写你们真实的后台接口
          // target: "http://192.168.1.111:20001/", //这里后台的地址模拟的;应该填写你们真实的后台接口
           target: 'http://10.21.24.154:8081/',  //这里后台的地址模拟的;应该填写你们真实的后台接口
          changOrigin: true, //允许跨域
          pathRewrite: {
            /* 重写路径，当我们在浏览器中看到请求的地址为：http://localhost:8080/api/accountback/conditions 时
                实际上访问的地址是：http://192.168.1.111:20001/accountback/conditions,因为重写了 /api
                */
            "^/api": ""
          }
        }
        // '/socket': {
        //   target: 'ws://192.168.1.232:20001/',  //这里后台的地址模拟的;应该填写你们真实的后台接口
        //   changOrigin: true,  //允许跨域
        //   ws: true,
        //   pathRewrite: {
        //     /* 重写路径，当我们在浏览器中看到请求的地址为：http://localhost:8080/api/accountback/conditions 时
        //         实际上访问的地址是：http://192.168.1.111:20001/accountback/conditions,因为重写了 /api
        //         */
        //     '^/socket': ''
        //   }
        // }
      }
    }
   
  };