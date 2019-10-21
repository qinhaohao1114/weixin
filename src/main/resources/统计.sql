

-- 1 年度运营总收入
SELECT
	ifnull( sum( IF ( trans_type = 1, trans_amt, 0 ) ), 0 ) AS 年度运营总收入
FROM
	finance_trans_detail
WHERE
	budget_subject_id IS NOT NULL and DATE_FORMAT(start_time,'%Y')=DATE_FORMAT(now(),'%Y');
-- 年度运营环比
SELECT
	( now.su - old.su ) / old.su AS 环比,
CASE

		WHEN now.su - old.su > 0 THEN
		1 ELSE 2
	END AS 涨跌
FROM
	(
	SELECT
		1 AS YEAR,
		ifnull( sum( IF ( trans_type = 1, trans_amt, 0 ) ), 0 ) AS su
	FROM
		finance_trans_detail
	WHERE
		budget_subject_id IS NOT NULL
		AND DATE_FORMAT( start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' )
	) AS now
	LEFT JOIN (
	SELECT
		1 AS YEAR,
		ifnull( sum( IF ( trans_type = 1, trans_amt, 0 ) ), 0 ) AS su
	FROM
		finance_trans_detail
	WHERE
		budget_subject_id IS NOT NULL
		AND DATE_FORMAT( start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' ) - 1
	) AS old ON now.YEAR = old.YEAR;


-- 2年度支出
SELECT
	ifnull( sum( IF ( trans_type = 0, trans_amt, 0 ) ), 0 ) as 年度运营总支出
FROM
	finance_trans_detail
WHERE
	budget_subject_id IS NOT NULL and DATE_FORMAT(start_time,'%Y')=DATE_FORMAT(now(),'%Y');

	SELECT
	( now.su - old.su ) / old.su AS 环比,
CASE

		WHEN now.su - old.su > 0 THEN
		1 ELSE 2
	END AS 涨跌
FROM
	(
	SELECT
		1 AS YEAR,
		ifnull( sum( IF ( trans_type = 0, trans_amt, 0 ) ), 0 ) AS su
	FROM
		finance_trans_detail
	WHERE
		budget_subject_id IS NOT NULL
		AND DATE_FORMAT( start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' )
	) AS now
	LEFT JOIN (
	SELECT
		1 AS YEAR,
		ifnull( sum( IF ( trans_type = 0, trans_amt, 0 ) ), 0 ) AS su
	FROM
		finance_trans_detail
	WHERE
		budget_subject_id IS NOT NULL
		AND DATE_FORMAT( start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' ) - 1
	) AS old ON now.YEAR = old.YEAR;

-- 3 管理总面积

select sum(area) as 管理总面积 from building;

-- 4 物业总数
SELECT
	count( DISTINCT ( display_name ) ) as 物业总数
FROM
	building
WHERE
	is_deleted = 0;

-- 5 在租面积

SELECT
	ifnull(
		sum(
		CASE

				WHEN cs.`space_id` IS NOT NULL THEN
				s.`area`
				WHEN ( cs.`space_id` IS NULL AND cs.`floor_id` IS NOT NULL AND cs.`building_id` IS NOT NULL ) THEN
				f.`area`
				WHEN cs.`space_id` IS NULL
				AND cs.`floor_id` IS NULL THEN
					b.`area` ELSE 0
				END
				),
				0
			) AS rentOutArea
		FROM
			contract c
			LEFT JOIN contract_space cs ON c.`id` = cs.`contract_id`
			LEFT JOIN building b ON b.id = cs.`building_id`
			LEFT JOIN floor f ON f.`id` = cs.`floor_id`
			LEFT JOIN space s ON s.id = cs.`space_id`
		WHERE
			c.contract_type = 1
			AND c.STATUS = 2
			AND c.is_deleted = 0
			AND cs.is_deleted = 0;
-- 6	租户个数

select count(DISTINCT signer_id) from contract where contract_type=1 and `status`=2;

-- 7 物业服务满意度
select name as 服务类名,value as 满意度 from user_satisfaction;

-- 8 设备故障率
SELECT
	t.equipment_name AS 设备故障名称,
	t.failure_rate AS 设备故障率
FROM
	failure_pmcomprate_rate t
WHERE
	t.type = 1;

-- 9 故障工单完成率
select w.all_comp_rate as 故障工单完成率 from workorder_patrolspot_comprate w where w.type=1;
-- 10 维保工单完成率

select f.equipment_name as 设备故障名称,f.failure_rate as 工单完成率 from failure_pmcomprate_rate f where f.type=2;

-- 11 巡检点位完成率
select w.all_comp_rate as 巡检点位完成率 from workorder_patrolspot_comprate w where w.type=2;

-- 12 收入构成

SELECT
  bs.`name` as 收入名称,
 ifnull( sum( IF ( trans_type = 1, trans_amt, 0 ) ), 0 ) as 金额
FROM
	finance_trans_detail f
	LEFT JOIN budget_subject bs on f. budget_subject_id=bs.id
WHERE
	DATE_FORMAT( f.start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' )
		AND f.trans_type = 1
 GROUP BY bs.`name`;

-- 支出构成   办公电话费

SELECT
  bs.`name` as 支出名称,
 ifnull( sum( IF ( trans_type = 0, trans_amt, 0 ) ), 0 ) as 金额
FROM
	finance_trans_detail f
	LEFT JOIN budget_subject bs on f. budget_subject_id=bs.id
WHERE
	DATE_FORMAT( f.start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' )
		AND f.trans_type = 0
 GROUP BY bs.`name`;

 -- 13 收支趋势
SELECT
  DATE_FORMAT( f.start_time, '%Y-%m' ) as 日期,
  ifnull( sum( IF ( trans_type = 0, trans_amt, 0 ) ), 0 ) as 支出金额,
	ifnull( sum( IF ( trans_type = 1, trans_amt, 0 ) ), 0 ) as 收入金额
FROM
	finance_trans_detail f
	LEFT JOIN budget_subject bs on f. budget_subject_id=bs.id
WHERE
	DATE_FORMAT( f.start_time, '%Y' ) = DATE_FORMAT( now( ), '%Y' ) GROUP BY DATE_FORMAT( f.start_time, '%Y-%m' );

-- 14 租户租赁面积top5(待确认）

-- 15 资产管理面积top5

SELECT
t.id, t.display_name as 物业点,
sum( t.area ) AS 管理面积,
	 ((
		SELECT
			ifnull( sum( s.area ), 0 ) usedArea
		FROM
			( SELECT space_id FROM revolving_room_register WHERE is_deleted = 0 AND STATUS IN ( 3, 9, 10 ) GROUP BY space_id ) r
			LEFT JOIN space s ON r.space_id = s.id
			LEFT JOIN building b ON b.id = s.building_id
		WHERE
			s.is_deleted = 0
			and b.id = t.id
	 )
	 +
		(select
        ifnull(sum(case
        when cs.`space_id` is not null then s.`area`
        when (cs.`space_id` is null and cs.`floor_id` is not null and cs.`building_id` is not null) then f.`area`
        when cs.`space_id` is null and cs.`floor_id` is null then b.`area`
        else 0 end ),0) as groupUsedArea
        from(
            SELECT
              distinct building_id,floor_id,space_id
            FROM
            groups_assignment
            WHERE
            is_deleted = 0
            AND(
            (
            start_time <= date_format(NOW() , '%Y-%m-%d')
            AND end_time >= date_format(NOW() , '%Y-%m-%d')
            )
            OR
            (start_time is null and end_time is null )
            OR is_permanent = 1
            )
        ) AS cs
        left join building b on b.id = cs.`building_id`
        left join floor f on f.`id` = cs.`floor_id`
        left join space s on s.id = cs.`space_id`
				where b.id = t.id)
			+
			(select
        ifnull(sum(case
        when cs.`space_id` is not null then s.`area`
        when (cs.`space_id` is null and cs.`floor_id` is not null and cs.`building_id` is not null) then f.`area`
        when cs.`space_id` is null and cs.`floor_id` is null then b.`area`
        else 0 end ),0) as rentOutArea
        from contract c
        left join contract_space cs on c.`id` = cs.`contract_id`
        left join building b on b.id = cs.`building_id`
        left join floor f on f.`id` = cs.`floor_id`
        left join space s on s.id = cs.`space_id`
        where c.contract_type = 1 AND c.status = 2 AND c.is_deleted = 0 AND cs.is_deleted = 0 and b.id = t.id))
				as 使用面积
FROM
	building t GROUP BY t.id, t.display_name ORDER BY sum(t.area) desc limit 5;



-- 16 物业性质分布
SELECT
	CASE b.property_type
	WHEN 1 THEN
		'自有'
	when 2 then
	  '租赁'
	ELSE
		'公租房'
END as 物业性质,
count(DISTINCT b.display_name) as 数量
FROM
	building b
GROUP BY
	b.property_type;

-- 17 物业业态分布
SELECT
	(case use_type
		when 1 then '商业'
		when 2 then '办公'
		when 3 then '周转房'
		when 9 then '其他'
		end) '物业业态',
	count( display_name ) 数量
FROM
	building
WHERE
	is_deleted = 0
GROUP BY
	use_type

-- 18 周转房分配占比
SELECT
	count( use_type = 3 OR NULL ) total,
	( SELECT COUNT( DISTINCT space_id ) FROM revolving_room_register WHERE STATUS IN ( 2, 3, 9, 10 ) AND is_deleted = 0 ) AS used
FROM
	building b,
	space s
WHERE
	b.id = s.building_id
	AND b.is_deleted = 0
	AND s.is_deleted = 0

-- 19 工单看板（待确认）

-- 20 供应商评价（涨跌无）
SELECT
	s.*
FROM
	( SELECT supplier_id, max( id ) id FROM supplier_scores_record GROUP BY supplier_id ) t
	INNER JOIN supplier_scores_record s ON t.id = s.id;

	-- 21地图数据
SELECT
	t.city_name,
	t.area,
	t.count,
	b.longitude,
	b.latitude
FROM
	( SELECT city_id, city_name, count( 1 ) AS count, max( id ) AS id,sum(area) as area FROM building GROUP BY city_id, city_name ) t
	LEFT JOIN building b ON b.id = t.id;

	-- 22 动态消息
select * from consultation_news;
