create sequence seq_blog201812021 increment by 1 start with 1;
create table blog201812021
(
    id      number(11)   not null primary key,
    name    varchar2(20) not null,
    email   varchar2(30) not null,
    title   varchar2(50),
    content varchar2(100)
);

drop sequence seq_blog201812021;
drop table blog201812021;

insert into blog201812021 values(seq_blog201812021.nextval, '심두용', 'email@induk.ac.kr', '제목', '내용');

select * from blog201812021;