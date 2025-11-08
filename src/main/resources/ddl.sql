---------------------------------------------------------
-- DROP TABLES (reverse dependency order)
---------------------------------------------------------
DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS platform_wallet CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS merchant CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS customer CASCADE;

---------------------------------------------------------
-- CREATE CUSTOMER TABLE
---------------------------------------------------------
CREATE TABLE customer (
                          customer_id     BIGSERIAL PRIMARY KEY,
                          customer_name   VARCHAR(100) NOT NULL,
                          dob             VARCHAR(20),
                          mobile_number   VARCHAR(15) UNIQUE,
                          email           VARCHAR(100) UNIQUE,
                          address         TEXT,
                          product_id      BIGINT
);

---------------------------------------------------------
-- CREATE ACCOUNT TABLE
---------------------------------------------------------
CREATE TABLE account (
                         account_id      BIGSERIAL PRIMARY KEY,
                         account_number  VARCHAR(50) NOT NULL UNIQUE,
                         customer_id     BIGINT NOT NULL,
                         balance         NUMERIC(19,2) NOT NULL,
                         currency        CHAR(3) NOT NULL,
                         created_date    TIMESTAMP,
                         updated_date    TIMESTAMP
);

---------------------------------------------------------
-- CREATE MERCHANT TABLE
---------------------------------------------------------
CREATE TABLE merchant (
                          merchant_id     BIGSERIAL PRIMARY KEY,
                          merchant_name   VARCHAR(100) NOT NULL,
                          email           VARCHAR(100) UNIQUE,
                          mobile_number   VARCHAR(15) UNIQUE,
                          amount          NUMERIC(19,2) NOT NULL DEFAULT 0.00,
                          date            TIMESTAMP,
                          utr             VARCHAR(50) UNIQUE
);

---------------------------------------------------------
-- CREATE PRODUCT TABLE (FK -> merchant)
---------------------------------------------------------
CREATE TABLE product (
                         product_id      BIGSERIAL PRIMARY KEY,
                         merchant_id     BIGINT NOT NULL,
                         product_name    VARCHAR(100) NOT NULL,
                         description     TEXT,
                         price           NUMERIC(19,2) NOT NULL,
                         currency        CHAR(3) NOT NULL,
                         CONSTRAINT fk_product_merchant
                             FOREIGN KEY (merchant_id)
                                 REFERENCES merchant(merchant_id)
                                 ON DELETE CASCADE
);

---------------------------------------------------------
-- CREATE PLATFORM WALLET TABLE
---------------------------------------------------------
CREATE TABLE platform_wallet (
                                 id          BIGSERIAL PRIMARY KEY,
                                 currency    CHAR(3) NOT NULL UNIQUE,
                                 balance     NUMERIC(19,2) NOT NULL
);

---------------------------------------------------------
-- CREATE PAYMENT TABLE
---------------------------------------------------------
CREATE TABLE payment (
                         id                  BIGSERIAL PRIMARY KEY,
                         customer_id         BIGINT NOT NULL,
                         merchant_id         BIGINT NOT NULL,
                         product_id          BIGINT,
                         created_datetime    TIMESTAMP NOT NULL,
                         updated_datetime    TIMESTAMP,
                         utr                 VARCHAR(50) NOT NULL UNIQUE,
                         debit               NUMERIC(19,2),
                         credit              NUMERIC(19,2),
                         fee                 NUMERIC(19,2),
                         currency            CHAR(3),
                         status              VARCHAR(20) N
