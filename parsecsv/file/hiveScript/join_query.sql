CREATE TABLE top_county as
    SELECT
        ic.country as country,
        sum(e.price) as sum_price_per_country
    FROM events as e JOIN ip_countries as ic
    ON e.ip = ic.network
    GROUP BY ic.country
    ORDER BY sum_price_per_country DESC
    LIMIT 10;