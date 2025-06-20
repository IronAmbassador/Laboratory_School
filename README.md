# 实验室管理系统

```md
🕙 分享是一种美德，右上随手点个 🌟 Star，谢谢
```

## 项目介绍

```md
该项目是基于 SpringBoot 开发的实验室管理系统，采用前后端分离架构模式。
为了管理和协调实验室资源、设备、人员和实验室活动而设计的软件系统。
该系统旨在提高实验室的效率、安全性和管理水平，同时简化日常操作并提供实验室数据的
准确记录和分析。本项目主要包含用户管理，登录日志，操作日志，实验室管理、分室管理、
预约管理，课程管理，设备管理，设备报修管理，人员管理，课程项目管理，实验室开放管理
模块等。
```

## 内置功能

- 系统包含管理员，教师，学生三个角色，分别配有不同权限。
- 用户登陆，用户注册，修改密码，修改个人信息
- 用户查看，新增用户，修改用户，删除用户，重置密码
- 登录日志查看，删除登录日志，导出登录日志，操作日志查看，删除操作日志，导出操作日志
- 实验室查看，新增实验室，修改实验室，删除实验室，导入实验室，导出实验室，预约实验室
- 预约信息查看，编辑实验室预约信息，删除实验室预约信息，审核实验室预约信息，导出实验室预约信息
- 实验室开放管理，实验室人员管理，课程管理，课程项目管理
- 设备查看，新增设备，编辑设备，删除设备，导入、导出设备
- 设备报修记录查看，修改报修信息，删除报修记录
- 等等...

## 运行项目

> ### 项目环境
>
> > - `MySQL5.7+` `JDK1.8+` `Maven3.6+` `NodeJS8.9+` `minio4.0+`
>
> ### 项目结构
>
> > - laboratory-admin 前端代码
> > - laboratory-server 后端代码
>
> ### 克隆项目
>
> > `git clone https://gitee.com/wokanxingqing/laboratory.git`
>
> ### 配置数据库
>
> > - 使用数据库连接工具创建数据库`CREATE DATABASE laboratorydb;`
> > - 导入 `laboratorydb-init.sql`
>
> ### 运行后端
>
> > - 后端项目通过 Idea 打开
> > - 使用 Maven 下载依赖
> > - 编辑 `application-local.yml` 文件(修改数据库连接信息)
> > - 运行 `LaboratoryApplication.java`
> > - 接口文档地址[http://localhost:8070/doc.html](http://localhost:8070/doc.html)
>
> ### 运行前端
>
> > - 进入前端项目目录 `cd laboratory-admin`
> > - 安装依赖 `npm install`
> > - 启动服务 `npm run dev`
> > - 浏览器访问: [http://localhost:9528](http://localhost:9528)
>
> ### 注意事项
>
> > - 运行后端请先修改 `application-local.yml`

## 项目预览

| 示例                                                         | 示例                                                     |
| ------------------------------------------------------------ | -------------------------------------------------------- |
| ![登录](.images/login.png)<br>登录                           | ![注册](.images/register.png)<br>注册                    |
| ![主页](.images/home.png)<br>主页                            | ![个人信息](.images/profile.png)<br>个人中心             |
| ![用户管理](.images/user.png)<br>用户管理                    | ![用户新增](.images/user-add.png)<br>用户新增            |
| ![登录日志](.images/login-log.png)<br>登录日志               | ![操作日志](.images/operation-log.png)<br>操作日志       |
| ![实验室管理](.images/lab.png)<br>实验室管理                 | ![实验室分室新增](.images/lab-edit.png)<br>实验室编辑    |
| ![实验室分室详情](.images/lab-detail.png)<br>实验室分室详情  | ![实验室预约](.images/booking.png)<br>实验室预约         |
| ![实验室预约管理](.images/lab-booking.png)<br>实验室预约管理 | ![实验室人员管理](.images/person.png)<br>实验室人员管理  |
| ![导入课程](.images/course-import.png)<br>导入课程           | ![课程管理](.images/course.png)<br>课程管理              |
| ![课程项目管理](.images/project.png)<br>课程项目管理         | ![课程项目新增](.images/project-add.png)<br>课程项目新增 |
| ![设备管理](.images/device.png)<br>设备管理                  | ![](.images/device-repair.png)<br/> 设备报修             |
| ![设备报修管理](.images/repair.png)<br>设备报修管理          | ![接口文档](.images/api-doc.png)<br>接口文档             |

## 作者信息

1. 作者：王 sir
2. 邮箱：`work_wangbing@163.com`
3. wx：`_king_bing`

## 关于技术栈

前端技术栈

| 技术       | 说明                           |
| ---------- | ------------------------------ |
| Vue        | 前端核心框架，用于构建单页应用 |
| VueX       | 状态管理，用于全局状态共享     |
| Vue Router | 路由控制，实现页面跳转         |
| Axios      | HTTP 请求框架                  |
| Element-UI | UI 组件库                      |
| ECharts    | 可视化图表                     |
| Vite       | 构建工具                       |

后端技术栈

| 技术        | 说明               |
| ----------- | ------------------ |
| SpringBoot  | 快速构建微服务框架 |
| MybatisPlus | ORM 框架           |
| Pagehelper  | 分页插件           |
| JWT         | JSON Web Token     |
| EasyExcel   | 导入导出框架       |
| Swagger2    | 接口文档           |
| MySQL       | 主数据库           |
| Minio       | 文件存储           |

## 感谢

- [花裤衩](https://gitee.com/panjiachen/vue-admin-template?_from=gitee_search) 开源框架

## 结语

欢迎一起探讨，如果你觉得还可以，可以给我点一个 star
