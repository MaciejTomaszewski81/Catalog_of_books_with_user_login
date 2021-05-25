INSERT INTO book (title,publisher,translation, genre, publishing_cycle, isbn, release_date)VALUES
('Przez ciemne zwierciadło', 'Dom Wydawniczy REBIS','Tomasz Jabłoński', 'science fiction','','978-83-7510-920-7', '2016') ,
('Labirynt śmierci', 'Dom Wydawniczy REBIS','Arkadiusz Nakoniecznik', 'science fiction','','978-83-8062-255-5', '2017'),
('Boża inwazja', 'Dom Wydawniczy REBIS','Lech Jęczmyk', 'science fiction','','978-83-7510-119-5','2017'),
('Wbrew wskazówkom zegara', 'Dom Wydawniczy REBIS','Maciej Szymański', 'science fiction','','978-83-7818-476-8','2013'),
('Wyznania łgarza', 'Dom Wydawniczy REBIS','Tomasz Jabłońsk', 'science fiction','','978-83-7510-859-0', '2012'),
('Doktor Bluthgeld', 'Dom Wydawniczy REBIS','Tomasz Jabłońsk', 'science fiction','','978-83-7510-569-8', '2016'),
('Oko na niebie', 'Dom Wydawniczy REBIS','Katarzyna Mioduszewicz', 'science fiction','','978-83-7818-577-2', '2015'),
('Elektryczna mrówka', 'Dom Wydawniczy REBIS','Janusz Szczepański', 'science fiction','','978-83-8062-141-1', '2017'),
('Zbrojni', 'Prószyński i S-ka','Piotr W. Cholewa', 'Fantasy','Świat Dysku','83-7337-201-6', ''),
('Ciekawe Czasy', 'Prószyński i S-ka','Piotr W. Cholewa', 'Fantasy','Świat Dysku','83-7337-292-X', '2003');
INSERT INTO author (name)VALUES
('Philip K. Dick'),
('Terry Pratchett');
INSERT INTO book_authors(books_id, authors_id)VALUES
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,2),
(10,2);