## base-framework

base-framework 是对常用的 java web 开发封装的基础 maven archetype，通过该 archetype 能够直接生成基于 spring、spring mvc、mybatis、apache shiro 等框架做底层的权限系统，该框架使用的 jdk 需要1.6以上，在整个框架中没有任何数据表映射的实体对象，通过 Map 代替实体数据表映射的实体对象。

演示地址:http://baseframework.coding.io/

**演示说明**

该框架基于 bootstrap + jquery 来做页面展示，所以必须访问演示地址时，需要使用 IE 8 以上的浏览器进行访问，否则将什么都展示不了。

该系统预先创建了3类用户，根据登录的用户可以得到不同的操作权限，用户信息如下:

1. 登录帐号:admin，密码:123456，该用户为超级管理员，拥有整个系统的所有功能。
1. 登录帐号:user，密码:123456，该用户为普通用户，只能在系统中做查看和阅读操作，不能对任何内容进行修改。
1. 登录帐号:maintain，密码:123456，该用户为维护用户，只能对系统管理模块做增、删、改、查操作。

**本地运行**

base-framework 的开发环境使用 h2 来做数据库，所以，如果通过下载 base-framework 到本地调试或运行时，需要执行一下步骤：

1. 配置 manven 完成后，双击根目录下的 bin/install 文件。
1. install 执行成功后，你可以通过 http://localhost/base-framework 进行访问。

如果想生成本地的当前版本，在 install 后可以通过以下命令进行生成。

    mvn archetype:generate -DarchetypeCatalog=local -DarchetypeGroupId=net.coding.chenxiaobo -DarchetypeArtifactId=base-framework-archetype -DarchetypeVersion=当前版本
    