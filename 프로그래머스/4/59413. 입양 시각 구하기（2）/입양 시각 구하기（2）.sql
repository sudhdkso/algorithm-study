WITH RECURSIVE hour_list AS (
    select 0 as hour
    union all
    select hour + 1
    from hour_list
    where hour < 23
)

SELECT b.hour as HOUR, count(ANIMAL_ID) as COUNT
FROM ANIMAL_OUTS a
RIGHT JOIN hour_list b ON HOUR(a.DATETIME) = hour
GROUP BY 1
ORDER BY 1
