CREATE TABLE user(
  Id bigint not null auto_increment comment '用户Id',
  md5Id VARCHAR(200) not null comment 'md5Id',
  userName VARCHAR(120) not null comment '用户名字',
  password VARCHAR(120) not null comment '用户密码',
  portrait VARCHAR(120) not null comment '用户头像',
  phone bigint not null comment '用户电话',
  birth date not null comment '用户生日',
  type int not null comment '用户类型 0为普通用户 1为管理员用户',
  enable int not null comment '是否被删除',
  primary key(Id)
)auto_increment=10000 DEFAULT charset=utf8 comment='用户表'