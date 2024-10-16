SELECT count(I.ID) as FISH_COUNT, N.FISH_NAME
FROM FISH_NAME_INFO N
INNER JOIN FISH_INFO I
ON I.FISH_TYPE = N.FISH_TYPE
GROUP BY N.FISH_NAME
ORDER BY count(I.ID) DESC