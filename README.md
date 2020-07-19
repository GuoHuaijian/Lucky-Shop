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



​	在校的时候比较贪玩一直玩游戏没有认真学习过，为了进入心仪的公司大三才开始觉醒每天奔波于图书馆一直想出去找一个好工作，幻想着参与各种高大上的项目，什么优化，高并发等等，以为自己能在软件行业施展抱负。刚上大四我们专业就可以离校找工作了，很高兴的在各大招聘网站投递以为找工作很容易，不料一次次失败。很快秋招来临，我们在各大高校参与秋招又一次输的很彻底，一个普通本科院校的学生能力还不出众。大四上半学期结束我没有收到一个offer，回家过年后没多久就出现了疫情无法再次出去找工作，我在家无所事事就看看专业书写点代码。三月份疫情有所控制后我再次离家找工作，没过多久收到了我人生中的第一个offer。我满心欢喜的进入公司开始我的程序员生活，进去后才发现和自己想象的差距这么大，虽然很失望但是依然尽力做好每件事。和我同期进入公司的很多员工因为部分原因相继离职，我也因为需要回校答辩等原因为由提出离职。答辩完成后想着找一份外地的工作好好磨练一下自己，找了好长一段时间后依然没找到一份工作甚至连一个面试机会都没有。这时候家里面又催着回去考编制，虽然不喜欢考编制但是出于现状只能回家。回家后白天看书考编制的书，晚上自己依然写代码。毕竟还是更喜欢写代码，虽然梦想可能永远是梦想，但是依然要时刻准备着说不定哪天上天就眷顾我了呢。所以就写了Lucky-Shop，寓意(Lucky Lucky)，希望自己能幸运一点。

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
