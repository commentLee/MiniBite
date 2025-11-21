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
    * [x] Onboarding-01: 계정 생성 (Backend: /auth/signup API)
    * [x] Onboarding-01: 계정 생성 (Frontend: OnboardingView.vue UI)
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
    * [x] 내 프로필 조회 (Backend: /my/profile API)
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


