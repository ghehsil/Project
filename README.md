  这是用ssm框架做的一个购物系统，具体模块有人员注册、登录(http://localhost:8080/User/Register , http://localhost:8080/User/Login)，管理人员界面(管理员(数据库中人员表有一列属性ytpe，0代表普通用户，1代表管理员用户)登陆后删除、修改人员信息http://localhost:8080/User/list)。
  普通用户登录成功后，跳转到购物页面(http://localhost:8080/Goods/Customer/list)，在该页面用户可以搜索商品名称跳转到详情页，也可以点击查看具体商品链接跳转到详情页(http://localhost:8080/Goods/Customer/detail)。在详情页里有加入购物车链接，点击后跳转到购物车页面(http://localhost:8080/Goods/Customer/myShoppingCart)。购物车链接里有支付链接(http://localhost:8080/Goods/Customer/pay)、删除购物车链接(包括删除一个、删除全部)(http://localhost:8080/Goods/Customer/deleteOne)(http://localhost:8080/Goods/Customer/deleteAll)。点击支付后，跳转到账单页面(http://localhost:8080/Goods/Customer/myPayment)。这就是普通用户的一个基本流程。
  接下来介绍管理员用户增加或删除商品，管理员登录后，跳转到商品列表(http://localhost:8080/Goods/Manager/list)，这个商品列表和普通用户的有所不同，里面增加了删除、查看库存等模块。删除商品链接(http://localhost:8080/Goods/Manager/deleteOne)，更新商品明细链接(http://localhost:8080/Goods/Manager/update)，点击更新链接后跳转到修改页面，和增加商品页面类似。
  接下来介绍一下用到的技术，首先数据库是用mysql。持久层用mybatis管理。先创建一个全局的mybatis文档，设置主键自增、驼峰命名。再创建mapper文档，里面主要写sql语句，使得Java代码和数据库代码分离。接下来用Spring整合mybatis，建立c3p0连接池，里面配置数据库驱动，jdbc url，数据库账号，密码，最大、最小链接数量，重连次数，失效时间。再建立sqlSessionFactory，整合dataSource、mybatis全局配置文档、实体类、mapper里的文档。最后整合持久层的接口，使得接口和mapper文档对应。再配置service层，用Spring注入service层，使其可以自动注入。再用Spring整合web层，把webapp里的jsp链接和controller层里的uri匹配，就可以用uri链接来访问jsp页面。三层架构基本就是这样了。
  接下来讲讲用到的前端技术，我使用第三方bootstrap的技术布局的前端页面，利用他的js和css样式来美化web页面。具体使用了横拉列表控件，分页的控件，form表单等。
  最后为了强化这个项目，鉴于人员信息基本上不修改，我用了redis缓存来管理人员登录。把从后台数据库里查到的人员实体类存放到redis换缓存里，由于redis使用内存的，所以存取速率特别快。经实践前台人员登录的速度比不用redis快了很多很多(不用redis登录判断大约要用2到3秒，用redis登录判断几乎都是秒进)。
  对了，忘了讲了，注册和增加商品时还增加了图片上传的功能(为了解决中文乱码卡了好久)，图片上传到Img文件夹里，数据库里存的是图片的名字。
  好了，第一次给项目写那么多的讲解，有些术语讲的不对还请谅解。发现言语还不能全部表达我做项目遇到的问题和解决方法，还有一些功能的细节，如果要讲的话还要讲很多，但是我已经几乎把这个项目的功能说出来了。
