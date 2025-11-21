# DML - 2. SELECT (조회)

# 테이블 전체 조회
select
	*   # 전부 (모든 칼럼)
FROM 
	PRODUCT; # 테이블명
    
# 조건을 걸 수 있다 - WHERE
SELECT 
	*
FROM 
	product
where  # where은 조건
	product_price > 50000; # 자바의 stream - filter와 동일

# 연산자
/*
1. () 
2. not 
3. 곱셈/나눗셈
4. +, - 
5. 비교연산 sql에서 "="는 같다   >, <, >=, <=, between, in, like, is null
6. and > or
*/

# in 잇냐 읍냐
select # 보고싯픈 칼럼 지정
	product_name, product_price # 보고싶은 칼럼을 지정 할 수 있다
from 
	product
where 
	product_name in ("노트북" , "키보드"); # 둘중 하나면 선택
    # product_name = "노트북" or product_name = "키보드"
    
# from 다음에, where,그 다음에 select
insert into
	product
values
    (null, "노트북", 1500000);
    
# select에서 distinct를 걸면, 중복을 제거한 결과를 볼 수 있다.
# 상품명 중복 제거
select distinct
	product_name # 만약 product_id를 추가하면, 중복제거가 아니라 고유한 객체가 설정됨
from
	product;
    
# Limit
select 
	* # *은 실무에서는 쓰면 안된다, count(*) x
from 
	product
order by # order by는 정렬을 어떻게 할것인가
	product_price  # 가격 오름 차순으로 정렬
limit 
	3; # 위에서 부터 3개만 가져온다 (하위 3개)
    
    

    
    
    
select 
	* # *은 실무에서는 쓰면 안된다, count(*) x
from 
	product
order by
	product_price desc # 가격 오름 차순으로 정렬
limit 
	3; # 상위 3개만 가져온다
    

# 가격 비싼 순서로 4,5,6등 뽑아 보기 - offset
select
	*
from 
	product
order by
	product_price desc  # 정렬 시킨다 - price를 기준으로
limit 3 offset 3; # 3개를 건너뛰고, 위의 3개를 조회한다
# 한 게시판에 게시글 20개를 보여줄때, 
# 3페이지를 조회 시,
# limit 20 offset 20 * (page - 1) - pagination
    
# 가격이 null인 상태를 조회 - is null / is not null null을 비교할때는 ,항상 is를 붙인다
select 
	*
from
	product
where 
	product_price is not null;  
    
    
# 숫자 범위 검색
# 상품 가격 1만원 ~ 5만원
select distinct
	product_name # 중복을 제거 시킴
from 
	product
where 
	product_price between 10000 and 50000; # 10000원에서 50000원 사이 상품 검색
    
    
# 문자열 패턴 검색 - like
select
	*
from
	product
where
	product_name like "마%"; 

# 마% : "마"로 시작하는 이름 -> fast(unique 혹은 pk가 걸려잇어야 한다)
# %마 : "마"로 끝나는 이름
# %마% : "마"가 포함된 이름    
# 양%준% : "양"으로 시작하고, "준"을 포함하는 이름
# 김__ : "김"으로 시작하고, 3글자 -> fast(unique 혹은 pk가 걸려잇어야 한다)
# 김_ : "김"으로 시작하고, 2글자
# _화_ : 중간이 "화"인 3글자

# 싸구려 음, 사이트는 시작하는것만 구현되어있고, 중간 글자 같은건 구현 안되잇음

# 실습1) 북으로 끝나는 상품을 조회해주세요
select 
	* 
from 
	product
where 
	product_name like "%북";
    
# 실습2) 상품가격이 30000원에서 100000원사이의 상품을 조회해주세요
select 
	*
from 
	product
where 
	product_price between 30000 and 100000;
    
# 실습3)  50000원이상 상품들중, 가격기준으로 내림차순 하셈
select 
	*
from 
	product
where
	product_price >= 50000
order by 
	product_price desc;

    
# 실습4) 가격이 높은 순으로 5-8등만 조회
select distinct
	product_name, product_price
from
	product
order by
	product_price desc
limit 4 offset 4; 
# order by 에 들어간 컬럼은 select에 있는 것이 표준
# 다만, distinct를 걸면, 반드시 잇어야 한다
# limit n : n개만 정렬, offset n : n개 건너뛰기

 


# 별칭기능 - as
select 
	product_name as `상품이름`,
	product_price as `상품가격`,
    product_price * 1.1 as `부가세ㅅㅂ` # 부가세를 포함한 가격
from
	product;
    
    
    
# 집계함수
select
	count(*) # product에 등록된 상품의 갯수 (row - 가로줄의 수)
    # count(*) -> null도 카운팅한다
    # count(특정칼럼) -> 해당 칼럼의 값이 null이면, 카운팅 안 한다
from 
	product;
    
    
select
	avg(product_price) as `상품가격 평균` # 평균 
    # max(product_price) -> 상품가격중 최대 가격
    # min(product_price) -> 상품가격중 최소 가격
from
	product;
    
    
    
# 그룹화 & case문법 - group-by 
# 저가, 중가, 고가를 임의로 나누고, 각 카테고리의 상품들이 얼마나 들어 있는가 ? -> 집게함수 공뷰

select
	# case문(조건문)
    # 그룹바이를 하면, 여러개의 객체를 하나로 묶기때문에, product_name이 사라진다
	case # if문 시작 
		when product_price <= 50000 then '저가'
		when product_price <= 100000 then '저가'
        else '고가'
	end as `price_range`, # if문 종료
	count(*) as `counting`
from 
	product
group by
	price_range # price_range의 결과가 같은것 끼리 하나로 묶겠다
having # group조건
	count(*) >= 0;  # 각 그룹의 count결과가 3이상인 것들만 -> group조건
    # 오류


/*
select 전체 실행 순서
: row(가로)를 먼저 제거하는 것이 우선 순위가 높다

select, where, from, group by, having, order by

1. from : 어떤 테이블에서 데이터를 가져 올 것인지 결정

2. (가로줄 먼저 제거) where : 가져온 데이터 중 조건을 만족하는 row만 남김
3. group by : 행을 그룹으로 묶음
4. having : 그룹 조건 -> 조건을 만족하는 그룹만 남김
5. select : 어떤 칼럼(세로줄)을 출력할것인지 결정
6. order by : 출력 순서 조정
7. limit : 몇 개까지 출력할지 제한
*/

# 실습) case문과 group by를 사용하여, 저가, 중가, --고가별 평균 가격--을 구해주세요..
# product_name -> 그룹화 해버린 상태에서 한칸에 여ㅑ러개ㅑ의 이름을 표현 -> 불가능
# group - by에 있는 필드이거나, 집계함수는 select에 선언 할 수 있다
select 
	case
		when product_price <= 50000 then '저가'
		when product_price <= 100000 then '중가' # 여기서는 백틱이 아니고, 문자열 이므로, 홑따옴표 써줘라
		else '고가' 
    end as `price_range`, # price_range 칼럼에 들어갈값은 저가, 중가, 고가 중 하나
    ####################################################################
    avg(product_price) as `평균가` # 평균가라는 컬럼
from 
	product
group by
	price_range;