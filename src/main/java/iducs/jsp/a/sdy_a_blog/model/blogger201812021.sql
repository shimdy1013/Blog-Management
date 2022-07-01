create sequence seq_blogger201812021 increment by 1 start with 1;
create table blogger201812021
(
    id      number(11)   not null primary key,
    email   varchar2(30) not null unique,
    pw      varchar2(20) not null,
    name    varchar2(30) not null,
    phone    varchar2(50),
    address    varchar2(100)
);

drop sequence seq_blogger201812021;
drop table blogger201812021;

select * from blogger201812021;

insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb1@induk.ac.kr', 'cometrue', 'csb1', '029507621', 'addr csb1');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb2@induk.ac.kr', 'cometrue', 'csb2', '029507622', 'addr csb2');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb3@induk.ac.kr', 'cometrue', 'csb3', '029507623', 'addr csb3');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb4@induk.ac.kr', 'cometrue', 'csb4', '029507624', 'addr csb4');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb5@induk.ac.kr', 'cometrue', 'csb5', '029507625', 'addr csb5');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb6@induk.ac.kr', 'cometrue', 'csb7', '029507626', 'addr csb6');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb7@induk.ac.kr', 'cometrue', 'csb7', '029507627', 'addr csb7');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb8@induk.ac.kr', 'cometrue', 'csb8', '029507628', 'addr csb8');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csb9@induk.ac.kr', 'cometrue', 'csb9', '029507629', 'addr csb9');

insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa1@induk.ac.kr', 'cometrue', 'csa1', '029507631', 'addr csa1');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa2@induk.ac.kr', 'cometrue', 'csa2', '029507632', 'addr csa2');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa3@induk.ac.kr', 'cometrue', 'csa3', '029507633', 'addr csa3');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa4@induk.ac.kr', 'cometrue', 'csa4', '029507634', 'addr csa4');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa5@induk.ac.kr', 'cometrue', 'csa5', '029507635', 'addr csa5');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa6@induk.ac.kr', 'cometrue', 'csa7', '029507636', 'addr csa6');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa7@induk.ac.kr', 'cometrue', 'csa7', '029507637', 'addr csa7');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa8@induk.ac.kr', 'cometrue', 'csa8', '029507638', 'addr csa8');
insert into blogger201812021 values(seq_blogger201812021.nextval, 'csa9@induk.ac.kr', 'cometrue', 'csa9', '029507639', 'addr csa9');