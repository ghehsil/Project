CREATE TABLE goods(
  Id bigint not null auto_increment comment '商品ID',
  md5Id VARCHAR(200) not null comment 'md5Id',
  name VARCHAR(120) not null comment '商品名称',
  price double not null comment '商品价格',
  number int not null comment '商品库存',
  picture VARCHAR(120) not null comment '商品图片',
  detail VARCHAR(500) not null comment '商品明细',
  goodsTypeId bigint not null comment '类型Id',
  enable int not null comment '是否被删除',
  PRIMARY KEY (Id)
)auto_increment=1000 DEFAULT charset=utf8 comment='商品表'