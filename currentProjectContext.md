## 10. 구현 현황 (Implementation Status by Version)
(이 섹션은 기능이 개발/병합(merge)될 때마다 수동으로 업데이트되어야 합니다.)
(AI 에이전트는 이 목록을 보고 **v1.0(MVP)부터 순차적으로** 다음 작업을 파악해야 합니다.)

예시:
* [x] GNB (AppSidebar.vue, AppHeader.vue) - (2025-11-18 완료)

### v1.0: MVP (핵심 가치 검증)
(사용자 가입 -> 기록 -> AI 추천 -> 통계 확인의 핵심 플로우)

* **[Layout]**
    * [ ] GNB (AppSidebar.vue, AppHeader.vue)
    * [ ] FAB (메인 기록 버튼)
* **[Onboarding]**
    * [ ] Onboarding-01: 계정 생성 (Backend: /auth/signup API)
    * [ ] Onboarding-01: 계정 생성 (Frontend: OnboardingView.vue UI)
    * [ ] Onboarding-02: 로그인 (Backend: /auth/login API)
    * [ ] Onboarding-02: 로그인 (Frontend: LoginView.vue UI)
* **[Dashboard (Core)]**
    * [ ] 관리-01: AI 추천 요청 (Backend: /dashboard/ai-recommendation API)
    * [ ] 관리-01: AI 추천 요청 (Frontend: AiRecommendationWidget.vue)
    * [ ] 관리-02: 식단 기록 (Backend: /dashboard/meal-record API)
    * [ ] 관리-02: 식단 기록 (Frontend: 식단 기록 모달)
    * [ ] 관리-02: 헬스케어 기록 (API, View)
    * [ ] 관리-03: 통계/AI 분석 조회 (Backend: /dashboard/statistics API)
    * [ ] 관리-03: 통계/AI 분석 조회 (Frontend: WeightChart.vue, SideEffectsWidget.vue 등)
* **[Profile (Basic)]**
    * [ ] 내 프로필 조회 (Backend: /my/profile API)
    * [ ] 내 프로필 조회 (Frontend: MyProfileView.vue)

### v1.1: 소셜 및 커뮤니티 확장
(MVP 검증 후, 사용자 간의 '연결' 기능 추가)

* **[Community (Group)]**
    * [ ] 그룹 검색/추천 (Backend: /group API)
    * [ ] 그룹 검색/추천 (Frontend: GroupView.vue)
    * [ ] 그룹 상세/프로필 (Backend: /group/{groupId} API)
    * [ ] 그룹 상세/프로필 (Frontend: GroupDetailView.vue)
* **[Social (Feed)]**
    * [ ] 포스트 작성 (Backend: /feed/post API)
    * [ ] 포스트 작성 (Frontend: PostCreateView.vue)
    * [ ] 소셜 피드 조회 (Backend: /feed API)
    * [ ] 소셜 피드 조회 (Frontend: FeedView.vue)

### v1.2: 고급 기능 및 고도화
(커뮤니티 활성화 및 AI 기능 심화)

* **[Community (Advanced)]**
    * [ ] 그룹 채팅 (Backend: WebSocket 설정 및 API)
    * [ ] 그룹 채팅 (Frontend: Chat.vue)
    * [ ] 주차별 미션 (API, View)
* **[Social (Advanced)]**
    * [ ] AI 세이프 필터 (Backend: /feed/safe-filter API)
    * [ ] AI 세이프 필터 (Frontend: FeedView.vue 필터 적용)

    지금까지 구현한 것
                                                                                                               
  - 공통 응답/예외 인프라: ApiResponse(backend/src/main/java/com/ssafy/minibite/global/dto/ApiResponse.java:1),    ErrorCode, BusinessException, GlobalExceptionHandler 추가.                                                 
  - 도메인/DTO/매퍼:                                                                                           
      - User, Preference 도메인(backend/src/main/java/com/ssafy/minibite/user/domain/*.java)                   
      - 온보딩/로그인/프로필 DTO (UserSignupRequest, LoginRequest, LoginResponse, ProfileResponse,             
        SignupResponse in backend/src/main/java/com/ssafy/minibite/user/dto/)                                  
      - MyBatis 매퍼 인터페이스와 XML (UserMapper, PreferenceMapper + backend/src/main/resources/mappers/      
        UserMapper.xml, PreferenceMapper.xml)                                                                  
  - 서비스 레이어:                                                                                             
      - AuthService(backend/src/main/java/com/ssafy/minibite/user/service/AuthService.java:1):                 
          - /auth/signup용 회원가입 로직 (ID 중복 체크 → user/preference insert, birthDate에서 age 계산,       
            onboardingDate = 오늘).                                                                            
          - /auth/login용 로그인 로직 (ID/비밀번호 검증, 임시 랜덤 access/refresh 토큰 생성).                  
      - UserService(backend/src/main/java/com/ssafy/minibite/user/service/UserService.java:1):                 
          - /my/profile용 프로필 조회 (USER + PREFERENCE 조합해서 응답 DTO 생성).                              
  - 컨트롤러:                                                                                                  
      - AuthController(backend/src/main/java/com/ssafy/minibite/user/controller/AuthController.java:1):        
          - POST /auth/signup → 201 + ApiResponse<SignupResponse> (message: "회원가입 완료").                  
          - POST /auth/login → 200 + ApiResponse<LoginResponse>.                                               
      - MyProfileController(backend/src/main/java/com/ssafy/minibite/user/controller/                          
        MyProfileController.java:1):                                                                           
          - GET /my/profile?userId=... → 200 + ApiResponse<ProfileResponse>                                    
            (JWT 붙기 전까지는 userId 쿼리 파라미터로 대체).                                                   
  - 테스트:                                                                                                    
      - AuthServiceTest(backend/src/test/java/com/ssafy/minibite/user/service/AuthServiceTest.java:1): 회원가  
        입/로그인 성공·실패 케이스 단위 테스트.                                                                
      - AuthControllerTest(backend/src/test/java/com/ssafy/minibite/user/controller/                           
        AuthControllerTest.java:1): /auth/signup, /auth/login WebMvc 테스트.                                   
                                                                                                               
  당신이 해줘야 할 일 (필수)                                                                                   
                                                                                                               
  1. MySQL DB/계정 준비                                                                                        
      - DB 생성: CREATE DATABASE minibite CHARACTER SET utf8mb4;                                               
      - backend/src/main/resources/application.yml:8 에서 username, password를 실제 MySQL 계정으로 변경.       
  2. USER / PREFERENCE 테이블 생성 (DDL 예시)                                                                  
     minibite DB 내에서 아래 실행:                                                                             
                                                                                                               
     CREATE TABLE user (                                                                                       
       user_id           VARCHAR(50)  PRIMARY KEY,                                                             
       password          VARCHAR(255) NOT NULL,                                                                
       nickname          VARCHAR(50)  NOT NULL,                                                                
       phone             VARCHAR(20)  NOT NULL,                                                                
       height            INT          NOT NULL,                                                                
       weight            FLOAT        NOT NULL,                                                                
       gender            VARCHAR(10)  NOT NULL,                                                                
       age               INT          NOT NULL,                                                                
       persona_type      VARCHAR(50),                                                                          
       onboarding_date   DATE         NOT NULL,                                                                
       birth_date        DATE         NOT NULL                                                                 
     );                                                                                                        
                                                                                                               
     CREATE TABLE preference (                                                                                 
       user_id            VARCHAR(50) PRIMARY KEY,                                                             
       allergies          TEXT,                                                                                
       dislikes           TEXT,
       preferred_textures TEXT,                                                                                
       preferred_flavors  TEXT,                                                                                
       CONSTRAINT fk_preference_user                                                                           
         FOREIGN KEY (user_id) REFERENCES user(user_id)                                                        
     );                                                                                                        
      - UserSignupRequest.birthDate는 yyyy-MM-dd 포맷이어야 합니다 (예: "1996-05-12").                         
  3. 로컬에서 빌드/테스트 실행 (선택 권장)                                                                     
      - cd backend                                                                                             
      - ./mvnw test 또는 mvnw.cmd test로 단위 테스트 실행.                                                     
                                                         
  알아두면 좋은 점 / 향후 할 일                                                                                
                                                                                                               
  - 현재 로그인 토큰은 임시 랜덤 문자열입니다. 추후 JwtProvider + SecurityConfig 추가해서 실제 JWT 발급/검증으 
    로 교체해야 합니다.
  - /my/profile는 아직 JWT에서 사용자 정보를 읽지 않고, userId 쿼리 파라미터를 사용합니다. 보안 구성 이후      
    @AuthenticationPrincipal 기반으로 바꾸는 걸 다음 단계로 두면 됩니다.
  - currentProjectContext.md의 체크박스는 인코딩 이슈로 자동 수정이 어려워,
    다음 항목을 직접 [x]로 바꿔두면 좋습니다:
      - Onboarding-01: 계정 생성 (Backend: /auth/signup API)
      - Onboarding-02: 로그인 (Backend: /auth/login API)
      - 프로필 조회 (Backend: /my/profile API)

  추천 커밋 메시지

  - feat(user): 온보딩 백엔드 기본 API 구현