DELETE FROM fines;
DELETE FROM cars;
DELETE FROM users;
DELETE FROM duties;

ALTER SEQUENCE jpwh_sequence RESTART WITH 1;

-------------------

INSERT INTO duties (name, price) VALUES
  ('Парковка в неположенном месте',              1000),
  ('Превышение установленной скорости движения', 1500),
  ('Управление в нетрезвом виде',                2000),
  ('Проезд на запрещающий сигнал светофора',     2500),
  ('Тонирование передних и лобового стекол',     3000);

INSERT INTO users (name, patronymic, surname, license) VALUES
  ('Максимилиан', 'Александрович', 'Волошин',     'GA5763FWA'),
  ('Владимир',    'Васильевич',    'Гиппиус',     'MH7731BYW'),
  ('Осип',        'Эмильевич',     'Мандельштам', 'LN5214YKQ'),
  ('Борис',       'Леонидович',    'Пастернак',   'MA4012QIB'),
  ('Александр',   'Трифонович',    'Твардовский', 'BM8531WBM');

INSERT INTO cars (brand, model, number, user_id) VALUES
  ('Skoda',   'Octavia',  'А564ФУ 70', 6),
  ('Renault', 'Duster',   'М283ЕУ 70', 7),
  ('Kia',     'Sportage', 'Е884ИТ 70', 8),
  ('Hyundai', 'Creta',    'К121ЮА 70', 9),
  ('Lada',    'Priora',   'Ш433СО 70', 10);

INSERT INTO fines (car_id, duty_id) VALUES
  (11, 1),
  (11, 2),
  (11, 3),

  (12, 1),
  (12, 1),

  (13, 4),
  (13, 5),

  (14, 2),
  (14, 2),
  (14, 2),
  (14, 4),

  (15, 1);