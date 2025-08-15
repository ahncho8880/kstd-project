# kstd-project

### 개발 언어
- Java 21
### 프레임워크
- Spring Boot 3.5.4
### RDMBS
- MariaDB 11.8.2
### 애플리케이션 실행 방법
1. MariaDB 설치 및 실행 
2. MariaDB 데이터베이스 생성
   - `kstd` 데이터베이스 생성
   - `DDL` 테이블 생성
2. application-local.yml 파일 수정
   - `spring.r2dbc.url` : MariaDB 접속 URL
   - `spring.r2dbc.username` : MariaDB 사용자명
   - `spring.r2dbc.password` : MariaDB 비밀번호
### API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
- `post /coins/apply` : 응모 코인 획득
- `get /coins/{coinId}/report` : 전체 응모 코인 현황 조회
- `post /coupons/apply` : 휴가 쿠폰 응모
- `put /coupons/cancel` : 휴가 쿠폰 취소
- `get /coupons/{couponType}` : 휴가 쿠폰별 전체 응모 현황 조회
- `get /users/{userId}/coupon` : 사용자 응모 현황 조회
- `get /users/{userId}/coins` : 사용자 응모 코인 수량 조회
### 회고 (과제를 진행하시면서 느낀점을 간단히 써주세요.)
- r2dbc를 사용하면 사용할수록 느끼는거지만 JPA보다 코드레벨에서의 생산성과 편의성이 떨어집니다. 물론 아직 JPA 보다는 익숙하지않다는 이유가 제일 크겠지만, JPA를 선택했을때 정보의 양도 훨씬많고 코드상에서 활용할 수 있는 기능들이 더 많다고 느껴집니다. 역시 사용자풀이 많은 스택은 그 자체로 확실한 장점이라고 생각합니다.
