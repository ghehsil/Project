CREATE TABLE goodsType(
  Id bigint not null auto_increment comment '类型表Id',
  typeName VARCHAR(120) not null comment '类型名字',
  enable int not null comment '是否被删除',
  PRIMARY KEY(Id)
)auto_increment=1000 DEFAULT charset=utf8 comment='类型表'