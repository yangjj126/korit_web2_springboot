
# update 기본 문법
update
	product
set # --set안에는 수정내용
	product_price = 1600000
where 
	product_id = 1;
    
# where 조건 
# where절 조건에 primary key조건이 아니면
# update가 실행되지 않는다. -> 안전업데이트 모드
# 참고) 안전업데이트 모드 on/off
set SQL_SAFE_UPDATES = 0;
set SQL_SAFE_UPDATES = 1;
update
	product
set 
	product_price = product_price + 50000
where
	product_price <= 50000;
    
# 업데이트는 앞에 태그를 넣는 것을 많이 한다
update
	product
set 
	# CONCAT(a,b) -> a문자열과 b문자열을 이어 붙힌다
    # db에서 굳이 태그를 붙여야하나? -> 선택
    # 서버에서 태그처리를 하는게 좀 더 일반적이다
	product_name = concat("[NEW] ", product_name) # concat은 +연산
where
	product_id = 1;
    
update 
	product
set
	# substring(문자열, index) 7번 인덱스부터 끝까지 읽겠다
	product_name = substring(product_name, 6) # 7번째 이후꺼만 쓸꺼야
where
	product_id = 1;

select
	*
from
	product;
    

/*
UPDATE할때에는, 반드시 SELECT로 확인하고, 어떤 ROW가 업데이트 될지 확인하는게 권장

update
	product
set 
	product_price = product_price * 1.1
where
	product_name like "%메모리%";

select
	*
from
	product
where
	product_name like "%메모리%"
*/



