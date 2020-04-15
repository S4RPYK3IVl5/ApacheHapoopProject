-- Most frequently purchased category
CREATE TABLE top_category as
    SELECT
        category as category,
        count(*) as count
    FROM events
    GROUP BY category
    ORDER BY count DESC
    LIMIT 10;

-- Most frequently purchased product in each category
-- SELECT
--     category,
--     name,
--     count(*) as product_count
-- FROM events
-- GROUP BY category, name
-- HAVING
--     (SELECT
--         *
--     FROM event as e
--     WHERE events.category = e.category
--     GROUP BY category, name
--     HAVING count(*) > product_count) <= 10
-- ORDER BY category_count DESC;

CREATE TABLE top_product as
    SELECT
        a.category as category,
        a.name as name,
        a.prod_count as count
    FROM
        (SELECT
            t.category,
            t.name,
            t.prod_count,
            rank() over (partition by t.category order by t.prod_count desc) as ranked
        FROM
            (SELECT
                category,
                name,
                count(*) as prod_count
                FROM events
                GROUP BY category, name) as t) as a
    WHERE a.ranked < 10;
