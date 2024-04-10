#### DispatcherServlet
* 클라이언트의 모든 요청을 한 곳으로 받아 처리
* 요청에 맞는 Handler로 요청을 전달
* Handler의 실행 결과를 Http Response 형태로 만들어서 반환
  1. DispatcherServlet
  2. HandlerMapping
  3. Controller
  4. ModelAndView -> DispatcherServlet
  5. ViewResolver -> DispatcherServlet
#### Richardson Maturity Model
* Level 0: The Swamp of POX
  * 기존에 가지고 있던 서버의 리소스(User, Post 등)를 웹 서비스 형태로 제공하기 위해 URI 매핑을 가지고 있는 형태
  * /server/getPosts
* Level 1: Resources
  * 외부로 공개하고자 하는 리소스에 대해 의미있는 URI 구성
  * GET, POST /server/accounts/10
* Level 2: HTTP Verbs
  * Level 1 + HTTP Methods
  * CRUD에 따른 HTTP Method 사용
* Level 3: Hypermedia Controls
  * Level2 + HATEOAS
  * 데이터 + 현재와 다음 작업의 가능한 행동들 제시 (상태 전이)
#### Best Practices
  * 사용자 입장에서 직관적인 API 설계
  * HTTP 메소드, Request, Response Type 등 HTTP 장점 최대한 살려야 함
  * Request Method: 최소한 Level 2단계의 리소스별 적절한 메서드 제공 필요
  * 200, 201, 204 등 적절한 상태코드 반환 필요
  * 사용자 비밀번호와 같이 민감 정보를 URI에 표현하면 안되고, Spring Security나 Token으로 보안 준수해야 함
  * 제공하려는 데이터는 단수 형태 URI가 아닌 복수 형태의 URI로 표현
  * 제공 데이터는 동사보다 명사 형태로 쓰는 것이 사용자 입장에서 직관적임
  * 메서드가 달라도 일관된 엔드포인트 사용

