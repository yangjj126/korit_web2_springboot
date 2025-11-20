# DML - 2. SELECT (조회)

# 테이블 전체 조회
select
	* # 전부 (모든 칼럼)
FROM 
	PRODUCT; # 테이블명
    
# 조건을 걸 수 있다 - WHERE
SELECT 
	*
FROM 
	product
where 
	product_price > 50000; # 자바의 stream - filter와 동일

# 연산자
/*
1. () 
2. not 
3. 곱셈/나눗셈
4. +, - 
5. 비교연산 sql에서 "="는 같다 , >, <, >=, <=, between, in, like, is null
6. and > or
*/

# in
select 
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
order by
	product_price # 가격 오름 차순으로 정렬
limit 
	3; # 위에서 부터 3개만 가져온다 (하위 3개)
    
    
    ###############################
    
    
    
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

# 마% : "마"로 시작하는 이름
# %마 : "마"로 끝나는 이름
# %마% : "마"가 포함된 이름    


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
# limit n : n개만 정렬, offset n : n개 건너뛰기 


# 별칭기능 - as
select 
	product_name as `상품이름`,
	product_price as `상품가격`,
    product_price * 1.1 as `부가세ㅅㅂ` # 부가세를 포함한 가격
from
	product;
    
    

    
    

    