drop database xharktank;
drop user admin;
create user admin with password 'Admin@1324';
create database xharktank with template=template0 owner = admin;
\connect xharktank;
alter default privileges grant all on tables to admin;
alter default privileges grant all on sequences to admin;

create table pitches(
pitch_id integer primary key not null,
entrepreneur varchar(40) not null,
pitchTitle varchar(1000) not null,
pitchIdea varchar(10000) not null,
askAmount float(10) not null,
equity float(3) not null,
createdTime timestamp default current_timestamp
);

create table offers(
offer_id integer primary key not null,
investor varchar(40) not null,
amount float(10) not null,
equity float(3) not null,
comment varchar(10000) not null,
pitch_id integer not null
);

alter table offers add constraint pitch_id_fk
foreign key (pitch_id) references pitches(pitch_id);

create sequence xharktank_pitch_seq increment 1 start 1;
create sequence xharktank_offer_seq increment 1 start 1;