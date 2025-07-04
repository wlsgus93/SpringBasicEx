# 📘 SpringBasic

SpringBoot를 활용하여 다음과 같은 웹 기능들을 통합 구현한 프로젝트입니다:

- 🧮 **계산기 (Calculator)**
- 📋 **메모장 (MemoPad)**
- ✅ **할 일 목록 (ToDoList)**
- 📊 **매출 대시보드 (Sales Dashboard)**

---

## 🔧 기술 스택

| 분류       | 기술                                             |
|------------|--------------------------------------------------|
| Language   | Java 17                                          |
| Framework  | Spring Boot 3.x, Spring Data JDBC                |
| Template   | Thymeleaf                                        |
| DB         | PostgreSQL                                       |
| Deploy     | EC2 (Amazon Linux 2), Koyeb                      |
| Build Tool | Gradle                                           |

---

## 📂 프로젝트 구조

```
src/
└── main/
    ├── java/com/jkh/Example/
    │   ├── controller/        # 기능별 웹 컨트롤러
    │   ├── service/           # 비즈니스 로직 처리
    │   ├── repository/        # JDBC 기반 DB 연결
    │   ├── model/             # 데이터 모델
    │   └── config/            # DB 설정 등 설정 파일
    └── resources/
        ├── templates/         # Thymeleaf 템플릿
        ├── static/            # CSS, JS 등 정적 파일
        └── application.yml    # 환경 설정
```

---

## 💡 주요 기능

### 1. 계산기
- 기본적인 사칙연산 수행
- 결과 페이지 렌더링

### 2. 메모장
- 메모 CRUD 기능
- 수정 및 삭제 가능
- 제목, 내용 기반 저장

### 3. ToDo 리스트
- 할 일 추가, 체크박스 기반 완료 처리
- 삭제 기능

### 4. 매출 대시보드
- 판매 내역 등록
- 일별 매출 리스트 출력
- 고객별 순위 확인

---

## 🚀 배포 환경

- **백엔드**: EC2 인스턴스 (Amazon Linux 2)에서 Spring Boot JAR 실행
- **프론트엔드**: EC2 인스턴스 (Amazon Linux 2)에서 Spring Boot JAR 실행(Thymeleaf 통해 구현)
- **DB**: EC2에 PostgreSQL 설치 및 원격 연결 설정

---

## 🛠 실행 방법

### 로컬에서 실행
```bash
./gradlew clean build -x test
java -jar build/libs/SpringBasic-0.0.1-SNAPSHOT.jar
```

### 환경변수 (.env 또는 export 방식)
```env
APP_NAME=SpringBasic
DB_HOST=localhost
DB_PORT=5432
DB_NAME=memo_app
DB_USERNAME=memo_app
DB_PASSWORD=password
```

---

## 📌 커밋 컨벤션 예시

- `feat: 매출 대시보드 추가`
- `fix: 메모 수정 시 오류 수정`
- `style: style.css 작성 및 적용`
- `chore: settings.gradle 프로젝트명 변경`

---
