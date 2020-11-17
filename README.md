# SignUp 打卡APP开发文档（更新中...）

## 说明

## 功能截图

## 技术框架

## 提交记录
**第一次提交 2020.11.11**
- 初始化工程文件
- 封装BaseActivity基类
- 封装BaseFragment基类
- 分包common负责提取一些常规操作、lang负责存储字符串类型数据，方便多语言切换
- app\build.gradle、common\build.gradle添加依赖库
- 添加图片资源文件、xml资源文件等

**第二次提交 2020.11.13**
- MainActivity界面布局封装
- 底部导航栏的封装
- 头像、顶部导航背景、点击效果
- 导航栏切换效果的实现

**第三次提交 2020.11.13**
- 修改文件名
- 修改背景、布局等

**第四次提交 2020.11.14**
- 添加浮动按钮动画
- 添加了 NavHelper 工具类，解决对fragment的调度与重用问题
- 修改了 部分标题信息

**第五次提交 2020.11.15**
- 添加图片选择框架
- 完善修改用户头像、剪切头像等

**第六次提交 2020.11.16**
- 完成动态权限申请
- 完成启动页面，进入启动页先授权，才可以启动app

**第七次提交 2020.11.16**
- 添加相关工具类

**第八次提交 2020.11.16**
- 头像上传到阿里云oss

**第九次提交 2020.11.16**
- 初始化登录、注册Fragment
- 改动用户登录、注册切换逻辑
- 改动用户信息修改逻辑

**第十次提交 2020.11.16**
- 登录、注册界面布局

**第十一次提交 2020.11.17**
- 修改了 首页appbar背景图片拉伸问题
- 添加了 注册部分逻辑
- 添加了 登录部分逻辑
- 抽离了 逻辑接口
- 注册功能的基本实现（没有使用网络请求）
