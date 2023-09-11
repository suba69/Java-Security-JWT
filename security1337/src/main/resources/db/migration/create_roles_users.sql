create table roles
(
  id bigserial constraint roles_pk primary key,
  name_role varchar(32) not null
);



create table users
(
  id bigserial constraint user_pk primary key,
  username varchar(50) not null constraint user_pk2 unique,
  password varchar(50) not null,
  role_id bigserial not null references roles
);


