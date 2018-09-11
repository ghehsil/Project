CREATE TABLE shoppingcart(
   Id bigint not null auto_increment comment '购物车ID',
   goodsId bigint not null comment '商品ID',
   userId bigint not null comment '用户ID',
   number int not null comment '数量',
   PRIMARY KEY(Id,userId)
)auto_increment=100 DEFAULT charset=utf8 comment='购物车'