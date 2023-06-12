CREATE TABLE PUBLIC.BRANDS
(
    ID BIGINT AUTO_INCREMENT,
    BRAND VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE PUBLIC.PRICES
(
    ID BIGINT AUTO_INCREMENT,
    BRAND_ID BIGINT,
    START_DATE TIMESTAMP,
    END_DATE TIMESTAMP,
    PRODUCT_ID BIGINT,
    PRIORITY INTEGER NOT NULL,
    PRICE NUMERIC(20, 2),
    CURR VARCHAR(3),

    PRIMARY KEY (ID),
    FOREIGN KEY (BRAND_ID) REFERENCES BRANDS(ID)
);

CREATE INDEX priority_idx ON prices(priority);