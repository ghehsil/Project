CREATE TABLE payment(
  Id bigint not null auto_increment comment '账单ID',
  userId bigint not null comment '用户ID',
  goodsId bigint not null comment '商品ID',
  number int not null comment '数量',
  PRIMARY KEY(Id)
)auto_increment=1000 DEFAULT charset=UTF8 comment '账单表'