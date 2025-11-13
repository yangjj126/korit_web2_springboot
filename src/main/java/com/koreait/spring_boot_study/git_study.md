

# git ? 
- 코드 변경에 따른 버전 관리 툴

# git 명령어
1. git init ; 명령어가 실행되는 경로 - 코드추적을 시작하겠다 
2. git add : 변경사항 임시 저장 -> 스테이징 영역-저장

   git add 경로/파일이름.java(정석적인 방법)
   git add . : 모든 변경사항 임시 저장 -- 주로 쓰임

3. git commit : 이때까지 add한 부분 -- 하나의 버전으로 저장
4. git remote add origin 깃허브 저장소 url
5. git push -u origin main : origin(원격저장소)와 main(로컬)을 동시에 동기화해서 전송하겠다 
   ( 최초1회만 기입 , 그이후에는 git push만 입력)
