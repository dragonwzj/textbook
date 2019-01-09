- 解压apache-maven-3.6.0-bin.zip，将bin目录配置到path环境变量，执行```mvn -v```检查安装、检查版本
- 将提供的settings.xml放在C:/Users/当前用户名/.m2目录下
- 修改代码src/main/resources/application.yml中的SQL SERVER数据库连接配置
- 在SQL SERVER中建textbook数据库
- 在项目根目录下执行```mvn compile```下载依赖包、编译
- 编译成功后在项目根目录下执行```mvn spring-boot:run```
- 浏览器访问127.0.0.1:8089/textbook
- 截图：
![首页](http://img.rainfn.com/textbook1.jpg)
![新增页](http://img.rainfn.com/textbook2.jpg)
