# wms back
这里是项目的后端

# 运行环境

jdk18

maven 3.9.6 参考安装教程：[链接](https://blog.csdn.net/m0_63684495/article/details/129046405?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171870005316800213015293%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171870005316800213015293&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-129046405-null-null.142^v100^pc_search_result_base9&utm_term=maven%E5%AE%89%E8%A3%85&spm=1018.2226.3001.4187)



# 项目运行

## 1.运行sql脚本建表和插入数据

可以使用sql文件夹下的sql脚本



## 2.修改数据库配置

![](https://cdn.jsdelivr.net/gh/Fornewt/Picture@master/image-20240618145548255.png)

![image-20240621233403353](https://cdn.jsdelivr.net/gh/Fornewt/Picture@master/image-20240621233403353.png)

设置环境变量MYSQL_USERNAME和MYSQL_PWD   这样数据库的用户名和密码不需要每次都更改

有需要，可以只修改端口号



## 3.运行BackApplication

注意后端端口为8000



