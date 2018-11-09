DROP TABLE IF EXISTS fines;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS duties;

DROP SEQUENCE IF EXISTS jpwh_sequence;

-------------------

CREATE SEQUENCE jpwh_sequence START 1;

CREATE TABLE duties (
  id         BIGINT  NOT NULL DEFAULT nextval('jpwh_sequence') PRIMARY KEY,
  name       VARCHAR NOT NULL,
  price      INTEGER NOT NULL
               CONSTRAINT duties_price_check CHECK (price >= 0),
  version    INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE users (
  id         BIGINT  NOT NULL DEFAULT nextval('jpwh_sequence') PRIMARY KEY,
  name       VARCHAR NOT NULL,
  patronymic VARCHAR NOT NULL,
  surname    VARCHAR NOT NULL,
  license    VARCHAR NOT NULL,
  version    INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE cars (
  id         BIGINT  NOT NULL DEFAULT nextval('jpwh_sequence') PRIMARY KEY,
  brand      VARCHAR NOT NULL,
  model      VARCHAR NOT NULL,
  number     VARCHAR NOT NULL,
  user_id    BIGINT  NOT NULL REFERENCES users ON DELETE CASCADE,
  version    INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE fines (
  id         BIGINT  NOT NULL DEFAULT nextval('jpwh_sequence') PRIMARY KEY,
  car_id     BIGINT  NOT NULL REFERENCES cars   ON DELETE CASCADE,
  duty_id    BIGINT  NOT NULL REFERENCES duties ON DELETE CASCADE,
  status     BOOLEAN NOT NULL DEFAULT FALSE,
  version    INTEGER NOT NULL DEFAULT 0
);