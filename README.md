# uckgisagi-server

# 🌏 억지로 지구를 사랑하는 지구인들 🌏
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=JAVA&logoColor=white">
<img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=white">

## 개요
2022학년도 2학기 오픈소스 기반 기초 설계 학기 프로젝트 과제
## 깃 커밋 컨벤션
* `feat`: 새로운 기능 개발
* `fix`: 버그 수정
* `add`: 새로운 기능 추가
* `chore`: 빌드 업무 수정 (build.gradle 수정)
* `refactor`: 코드 리팩토링
* `style`: 코드 포맷팅, 주석 삭제 등 코드 변경이 없는 경우
* `test`: 테스트 코드, 리팩토링 테스트 코드 개발 및 추가
## 코드 컨벤션


## 사용한 스택
### 백엔드
* 스프링 부트 2.7.4
* 언어: 자바
* JAVA 11, Gradle
* MySQL
* Spring JPA
* QueryDsl
* Spring Security
* Swagger
    * http://localhost:8080/api/swagger-ui/index.html
* Sl4j Logging
* Validation
* 그 외 스타터 라이브러리


### JPA 사용을 위한 application.yml 에서의 datasource 설정
```
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/uckgisagi_dev?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
    username: root

  jpa:
    database: mysql
    hibernate:
      ddl-auto: create
```
## 구현 내용

### User

### Post

### Home

### Review

### Store



### 감사합니다
