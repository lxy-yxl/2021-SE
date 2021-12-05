module.exports = {
    configureWebpack: {},
    devServer: { // 环境配置
        host: '0.0.0.0',
        public: '192.168.41.1:8080', // 此处是自己电脑IP地址！
        port: '8080',
        https: false,
        disableHostCheck: true,
        open: false // 配置自动启动浏览器
    }
}

// 跨域配置
module.exports = {
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 9876,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:9090',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '/api': ''                     //选择忽略拦截器里面的单词
                }
            }
        }
    }
}
