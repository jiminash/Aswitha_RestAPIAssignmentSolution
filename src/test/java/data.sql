insert into role values(1,'USER');
insert into role values(2,'ADMIN');

insert into users values(1,'admin', '$2a$12$IvKDGYK307v9uf5WsYGrXurfuNoj/y4oNcKlQhWPn0rOucTqyCJli');
insert into users values(2,'user','$2a$12$IvKDGYK307v9uf5WsYGrXurfuNoj/y4oNcKlQhWPn0rOucTqyCJli');

insert into userroles values(1 , 2);
insert into userroles values(2 , 1);

insert into employee values(1, 'a', 'b','ab@gmail.com');