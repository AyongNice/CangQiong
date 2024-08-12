# 阿勇的第一个Java项目 准备工作

### 关注 微信公众号  阿勇学前端

### 环境
1. jdk : java version "1.8.0_411"
2. maven : Apache Maven 3.6.1
3. mysql : 8.0
4. redis : 7.2.5
5. node : v16.20.2

###  后端准备工作
1. 使用数据库连接工具 或者 idea 连接数据库 执行 cangqiong.sql (文件在项目内)脚本
2. 启动  redis 服务
3. 将 application.yml 中  数据库端口 、 用户名、密码、redis 端口  修改为自己的配置

4. 刷新pop文件依赖 运行spring-boot

### 前端准备工作

#### 管理的 pc
1. 解压nginx-1.20.2.rar 不能放到有中文的名 的文件夹！！！
2. 修改 conf 目录下 nginx.conf 文件中的  反向代理,处理管理端发送的请求 端口号 为 后端spring-boot 运行端口
   例如 proxy_pass  http://127.0.0.1:8080/admin/;  改为 http://127.0.0.1:(你自己的后端端口)/admin/;
3. 双击nginx.exe 、 在浏览器窗口 http://localhost/#/login 即可访问管理端 账号   admin 密码 1234

#### 用户端 小程序、
1. https://mp.weixin.qq.com/ 登录微信公众平台使用测试号小程序 粘贴自己的小程序appid  、AppSecret 复制到常量类里 JwtClaims
2. 微信官网下载微信小程序开发工具 https://mp.weixin.qq.com/
3. 解压 mp-weixin.zip
4. 使用微信小程序开发工具打开 导入项目 mp-weixin 即可


### 按照 用户端 管理端接口文档 开发接口
