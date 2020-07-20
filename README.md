# 前言
[![Spring Boot](https://img.shields.io/badge/spring--boot-2.2.5.RELEASE-brightgreen)](https://github.com/spring-projects/spring-boot)
[![vue](https://img.shields.io/badge/vue-2.6.10-brightgreen.svg)](https://github.com/vuejs/vue)
[![element-ui](https://img.shields.io/badge/element--ui-2.11.0-brightgreen.svg)](https://github.com/ElemeFE/element)
[![Vant](https://img.shields.io/badge/vant-2.2.0-brightgreen.svg)](https://youzan.github.io/vant/#/zh-CN/intro)
[![Vant-weapp](https://img.shields.io/badge/vant--weapp-1.0.1-brightgreen)](https://youzan.github.io/vant-weapp/#/intro)
[![Flutter](https://img.shields.io/badge/Flutter-1.9.6-brightgreen)](https://flutter.dev/)
![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-brightgreen.svg) 
![Java](https://img.shields.io/badge/Java-1.8-blue.svg)
![Database](https://img.shields.io/badge/Database-MySQL-lightgrey.svg)



​游离于城市的痛痒

错过了心爱的姑娘

宣告世界的那个理想

已不知去向

为所欲为是轻狂

防不胜防是悲伤

后来才把成熟当偏方

当所有想的说的要的爱的

都挤在心脏

行李箱里装不下我 想去的远方。所以就写了Lucky-Shop，寓意(Lucky Lucky)，希望自己能幸运一点。

- Lucky-Shop 是一个基于[Spring Boot](https://spring.io/projects/spring-boot/)和[Vue.js](https://cn.vuejs.org)的web商城系统
- Lucky-Shop 包含了商城的后台管理系统,手机h5，小程序版本
- Lucky-Shop 项目原型来自码云上面的一个开源项目[linjiashop](https://gitee.com/microapp/linjiashop)
- Lucky-Shop是我为了实践自己的学习的微服务技术而改造的，因为技术及时间原因很多方面没来的及重构，优化。后期会慢慢优化，后期改动可能会比较大。同时希望借此项目提升自己的技术，早日重返IT届，哈哈。

## 系统结构

```

Lucky-Shop     
├── lucky-shop-admin         // 后台管理系统模块
│       └── lucky-shop-admin-auth                        // 后台管理系统认证中心 [9888]
│       └── lucky-shop-admin-cms                         // 后台管理系统cms模块 [9001]
│       └── lucky-shop-admin-dashboard                   // 后台管理系统首页模块 [9002]
│       └── lucky-shop-admin-mall                        // 后台管理系统商城模块 [9003]
│       └── lucky-shop-admin-message                     // 后台管理系统消息模块 [9004]
│       └── lucky-shop-admin-ops                         // 后台管理系统运维模块 [9005]
│       └── lucky-shop-admin-seo                         // 后台管理系统推广模块 [9006]
│       └── lucky-shop-admin-system                      // 后台管理系统系统模块 [9007]
├── lucky-shop-api           // Feign接口模块
│       └── lucky-shop-admin-api                      // 后台管理系统系统Feign接口模块
│       │       └── lucky-shop-admin-mall-api                 // 后台管理系统商城模块Feign接口
│       │       └── lucky-shop-admin-message-api              // 后台管理系统消息模块Feign接口
│       │       └── lucky-shop-admin-ops-api                  // 后台管理系统运维模块Feign接口
│       │       └── lucky-shop-admin-system-api               // 后台管理系统系统模块Feign接口
│       └── lucky-shop-mobile-api                      // 移动端Feign接口模块
│               └── lucky-shop-mobile-product-api              // 移动端模块产品模块Feign接口
│               └── lucky-shop-mobile-ucenter-api              // 移动端模块用户中心模块Feign接口
├── lucky-shop-auth          // 认证中心 [9000]
├── lucky-shop-common        // 通用模块
│       └── lucky-shop-common-core                     // 通用模块通用工具模块
│       └── lucky-shop-common-log                      // 通用模块日志记录模块
│       └── lucky-shop-common-redis                    // 通用模块缓存模块
├── lucky-shop-config       // 配置模块
├── lucky-shop-dependencies // 依赖管理模块
├── lucky-shop-gateway      // 网关模块 [9527]
├── lucky-shop-generator    // 代码生成模块 [1001]
├── lucky-shop-mobile       // 移动端模块
│       └── lucky-shop-mobile-auth                      // 移动端认证中心 [9101]
│       └── lucky-shop-mobile-order                     // 移动端订单模块 [9102]
│       └── lucky-shop-mobile-product                   // 移动端产品模块 [9103]
│       └── lucky-shop-mobile-ucenter                   // 移动端用户中心模块 [9104]
├── lucky-shop-monitor     // 监控模块 [9200]
├── lucky-shop-ui         // 前端模块 
│       └── lucky-shop-admin                            // 前端模块后台管理系统前端
│       └── lucky-shop-mobile                           // 前端模块移动端前端
│       └── lucky-shop-wxapp                            // 前端模块微信app
├──pom.xml                // 公共依赖
```

## 技术选型
- 核心框架：Spring Boot，Spring Cloud Alibaba，Spring Security oAuth2 ，MyBatis-Plus
- 数据库连接池：Druid
- 缓存：Redis
- 前端：后台管理基于[element](http://element-cn.eleme.io)，手机端界面基于[vant](https://youzan.github.io/vant/#/zh-CN/intro)

## 演示图

![lucky-shop](http://image.guohuaijian.com/lucky-shop9.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop5.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop1.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop2.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop3.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop8.png )![lucky-shop](http://image.guohuaijian.com/lucky-shop4.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop7.png )

![lucky-shop](http://image.guohuaijian.com/lucky-shop6.png )
