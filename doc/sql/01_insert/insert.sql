/*
데이터베이스 언어
데이터베이스에서 데이터를 조작하는 언어,
DDL, DML, DCL
DDL(Data Definition Language )
-> 테이블구조를 정의하는 언어
DML(DATA Manipulation Language)
-> 테이블에 데이터를 넣거나, 수정, 삭제, 조회하는 언어
*/
# 컨트롤 + 스크롤 = 창확대

# < 데이터 베이스 만들기 >

# 데이터 베이스 생성
CREATE DATABASE web2;
# 데이터베이스 선택
USE web2;   #GUI 에서 더블클릭
# 테이블 생성
/*
CREATE TABLE 테이블이름(
		속성1이름 자료형 제약사항
        속성2이름 자료형 제약사항alter
);

1. 데이터베이스의 자료형
- 숫자
INT : 일반정수
BIGINT : 매우 큰 정수(long)
DOUBLE : 실수

- 문자열
1 VARCHAR(n) : 가변길이 문자열 (n은 크기)
  VARCHAR(100) -> String의 길이가 100이다
2 TEXT : 긴 텍스트(게시글 본문)

- 날짜 / 시간 
DATETIME : 날짜 + 시간 저장 -> 자바의 LocalDateTime 타입
2025-11-20 19:34:30
-> 생성된 날짜 + 시간 기록/ 수정 로그 기록/ 가입일자....

2. 제약 조건
- NOT NULL : NULL을 허용하지 않겠다.
( ID같은 경우에는, NULL이 들어오면 안된다 )
- UNIQUE : 중복된 값을 저장하지 않겠다.
-- 주민등록번호 예시
- PRIMATY KEY : 식별자 NOT NULL + UNIQUE -> 주민번호
: 하나의 행(가로줄-row)을 구별하는 고유값 = 하나의 객체, 하나의 인스턴스 
참고) 하나의 행(가로줄-row)은 자바의 인스턴스와 1:1 대응이 된다
- AUTO_INCREMENT : 자동증가하는 번호(주로 Primary Key와 함께 사용) - max를 자동으로 구해줌alter
- FOREIGN KEY : 외래키(다른 테이블의 primary key) - 정규화와 join배울때, 나중에
- DEFAULT : 값이 없을 때, 기본값 지정
- CHECKI : 값 검증( 특정 조건을 만족하는 값만 허용 ) - 스프링에서 POSTIVE나 음수같은거 다시 체크함
---> 스프링부트에서도 값 검증을 하지만, 더블체크를 하는게 권장된다.
*/

# DDL - 테이블 생성
create table product(
	product_id int /* 자바랑 반대로 */ auto_increment primary key,
    product_name varchar(100) not null,
    product_price int not null check(product_price > 0)
);

# 스프링부트 서버에서 DML쿼리를 DBMS로 전송
# DML - 1. insert 
insert into
	product(product_id, product_name, product_price)
values
	(1, "노트북", 15000);
    
/* 오류남 */

# 칼럼을 지정하면, 기존 칼럼의 순서가 상관없어진다
insert into
	product(product_name, product_id , product_price)
values
	("키보드", 2, 8000);

# auto_increment 활용alter
# auto_increment가 걸려있는 컬럼은 생략하거나 null을 입력해도, 자동 증가가 된다.
insert into
	product (product_name, product_price)
values
	("마우스", 30000);
    
    
# 전체 컬럼의 순서로 넣으면, 컬럼명을 생략할 수 있다
insert into
	product
values
	(null, "USB메모리", 20000);
    
# 여러행을 한번에 insert할 수 있다
insert into
	product
values
	(null, "HDMI 케이블", 8000),
    (null, "마이크" , 45000),
    (null, "헤드셋", 95000);
    

# 실습
# 1. "노트북쿨링패드"추가, 가격250000, id는 자동증가

insert into
	product
values 
	(null, "노트북쿨링패드" , 2500000);
    

# 2. 한번에 USB-C 케이블 : 7000 , 고속충전기 : 18000, 블루투스 리모컨 : 9000 을 추가해주세요

insert into
	product
values 
	(null, "USBC-CABLE" , 100000),
	(null, "고속충전기" , 1200000),
	(null, "블루트수리모컨" , 9000);
    
# primary key라서 id가 50인 row가 있으면, 에러가 발생한다
# 8번까지 1씩 잘 증가한다
# 그다음부터는 AUTO_INCREMENT값은 51번부터 시작
insert into 
	product
values
	(50, "갤럭시 S1", 1500000);



