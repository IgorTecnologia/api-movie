
INSERT INTO tb_user (name, email, password) VALUES ('Igor', 'igor@gmail.com', '1234567' );
INSERT INTO tb_user (name, email, password) VALUES ('Nanci', 'nanci@gmail.com', '1234567' );

INSERT INTO tb_genre (name) VALUES ('Ação' );
INSERT INTO tb_genre (name) VALUES ('Comédia' );

INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_id) VALUES ('O homem de ferro', 'A batalha', 2024, 'www.img/url.com', 'Um homem com poderes incrivéis, salva o mundo enfrentando grandes batalhas', 1);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_id) VALUES ('O Garfild', 'O gato sapéca', 2012, 'www.img/url.com', 'Um gato para lá de esperto', 2 );

INSERT INTO tb_role(authority) VALUES ('Administrador');
INSERT INTO tb_role(authority) VALUES ('Membro');

INSERT INTO tb_user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role(user_id, role_id) VALUES (2, 1);

INSERT INTO tb_review(text, movie_id, user_id) VALUES ('Filmaço', 1, 1);
INSERT INTO tb_review(text, movie_id, user_id) VALUES ('Muito engraçado', 2, 2);
INSERT INTO tb_review(text, movie_id, user_id) VALUES ('Muitas risadas', 2, 2);
