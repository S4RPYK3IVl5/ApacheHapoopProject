CREATE TABLE IF NOT EXISTS `asaprykin`.`top_category`(
    `category` VARCHAR(100),
    `count` BIGINT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_product`(
    `category` VARCHAR(100),
    `name` VARCHAR(100),
    `count` BIGINT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_country`(
    `country` VARCHAR(100),
    `sum_price_per_country` BIGINT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_category_mysql`(
    `category` VARCHAR(100),
    `count` BIGINT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_product_mysql`(
    `category` VARCHAR(100),
    `name` VARCHAR(100),
    `count` BIGINT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_country_mysql`(
    `country` VARCHAR(100),
    `sum_price_per_country` BIGINT
);
