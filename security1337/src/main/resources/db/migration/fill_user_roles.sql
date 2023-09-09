insert into roles(name_role)
values
('ROLE_USER')

insert into users(username, password, role_id)
values('admin', 'admin', 1),
('user', 'user', 2)