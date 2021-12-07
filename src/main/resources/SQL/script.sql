
create table dados_bancarios (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  numero_conta  varchar(20) not null,
  agencia varchar(20) not null,
  cheque_especial_liberado boolean,
  saldo decimal(10,2),
  cheque_especial decimal(10,2),
  taxa decimal(10,2)  ,
  primary key (id)
) engine=InnoDB default charset=utf8;

