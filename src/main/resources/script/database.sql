-- SQL Create table
Create table if not exists author
(
    author_id   serial primary key,
    author_name varchar(100),
    gender      varchar(50)
);
create table if not exists category
(
    category_id   serial primary key,
    category_name varchar(225)
);
drop table book;
create table if not exists book
(
    book_id        serial primary key,
    title          varchar(225),
    published_date timestamp default now(),
    author_id      int,

    foreign key (author_id) references author (author_id) on delete cascade on update cascade
);
create table book_category
(
    id          serial primary key,
    book_id     int,
    category_id int,

    foreign key (book_id) references book (book_id) on delete cascade on update cascade,
    foreign key (category_id) references category (category_id) on delete cascade on update cascade
);


-- SQL Query Test
SELECT author_id
FROM author;
SELECT *
FROM author
where author_id = 1;
INSERT INTO author (author_name, gender)
values ('sokea', 'female')
returning *;

SELECT *
FROM category;
SELECT *
FROM category
where category_id = 2;
INSERT INTO category (category_name)
values ('Sci-fi')
returning *;
UPDATE category
SET category_name = 'History'
where category_id = 5
returning *;
DELETE
FROM category
where category_id = 5;


SELECT *
FROM book;


SELECT c.category_id, c.category_name
FROM category c
         INNER JOIN book_category bc on c.category_id = bc.category_id
where bc.book_id = 1;

INSERT INTO book (title, author_id)
values ('Comedy', 3)
returning book_id;

INSERT INTO book_category (book_id, category_id)
values (2, 3);
UPDATE book
SET title='',
    author_id =1
where author_id = 2
returning author_id;

DELETE
FROM book_category
WHERE book_id = 2;

DELETE
FROM book
WHERE book_id = 1;

