SELECT author_id FROM author;
SELECT * FROM author where author_id = 1;
INSERT INTO author (author_name,gender)values ('sokea','female') returning  *;




SELECT * FROM category;
SELECT * FROM category where category_id = 2;
INSERT INTO category (category_name) values ('Sci-fi') returning *;
UPDATE category SET category_name = 'History' where category_id = 5 returning *;
DELETE FROM category where category_id =5;


SELECT * FROM book;


SELECT c.category_id,c.category_name FROM category c INNER JOIN book_category bc on c.category_id = bc.category_id where  bc.book_id = 1;

INSERT INTO book (title, author_id) values ('Comedy',3) returning book_id;

INSERT INTO book_category (book_id, category_id) values (2,3);
UPDATE book SET title='',author_id =1 where author_id = 2 returning author_id;

DELETE FROM book_category WHERE book_id = 2;

DELETE FROM book WHERE book_id = 1 ;

