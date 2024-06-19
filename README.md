# 尚庭公寓

尚庭公寓采用前后端模式，本项目为后端项目，主要使用以下技术：

- [SpringBoot3](https://spring.io/projects/spring-boot#learn)
- [MySQL8](https://dev.mysql.com/doc/refman/8.4/en/)
- [MyBatisPlus](https://baomidou.com/)
- [Redis](https://redis.io/)
- [knife4j](https://doc.xiaominfo.com/)
- [minio](https://min.io/download)
- [Aliyun SMS](https://dysms.console.aliyun.com/overview)
- [hutool](https://hutool.cn/)

前端项目地址：https://github.com/Zhu-junwei/lease-frontend

## 参考资料

在线视频：

- [bilibili](https://www.bilibili.com/video/BV1At421K7gP)

资料

- [百度网盘](https://pan.baidu.com/s/18gU7FbbHdv9vRLnPIywl2g?pwd=yyds) 提取码：yyds

- [阿里云盘 视频](https://www.alipan.com/s/bp6R9DV2pq6)（教程配套资料请从百度网盘下载）

# 项目结构

```txt
lease
├── common（公共模块——工具类、公用配置等）
│   ├── pom.xml
│   └── src
├── model（数据模型——与数据库相对应地实体类）
│   ├── pom.xml
│   └── src
├── web（Web模块）
│   ├── pom.xml
│   ├── web-admin（后台管理系统Web模块——包含mapper、service、controller）
│   │   ├── pom.xml
│   │   └── src
│   └── web-app（移动端Web模块——包含mapper、service、controller）
│       ├── pom.xml
│       └── src
└── pom.xml
```
