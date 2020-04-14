CREATE TABLE IF NOT EXISTS `asaprykin`.`top_category`(
    `category` VARCHAR(50),
    `count` INT
);

CREATE TABLE IF NOT EXISTS `asaprykin`.`top_product`(
    `category` VARCHAR(50),
    `name` VARCHAR(50),
    `count` INT
)
