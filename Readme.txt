## 环境搭建

### 前置需求

1. JDK8.0
2. Intellij IDEA 14+

### 初始化

+ gradlew.bat idea  # 保证springboot可以被idea支持

+ gradlew.bat build # 构建项目

+ idea中设置gradle link project，配置框会在项目初次打开时自动提示的

### 启动服务

+ gradlew.bat bootrun

+ 或者 idea 中 run/debug configuration 配置 gradle 启动项目 (推荐，因为可以调试)

### 热部署

+ 在idea的project structure中设置编译的输出目录为build。项目启动后，通过make，即可实现热部署。