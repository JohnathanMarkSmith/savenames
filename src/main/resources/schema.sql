drop table messages if exists ;
CREATE TABLE  messages (id bigint generated by default as identity (start with 1), message VARCHAR(100));

insert into messages values (1,  "Johnathan Smith");
insert into messages values (2,  "Regan Goldring");