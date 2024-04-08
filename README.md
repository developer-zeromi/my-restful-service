#### DispatcherServlet
* 클라이언트의 모든 요청을 한 곳으로 받아 처리
* 요청에 맞는 Handler로 요청을 전달
* Handler의 실행 결과를 Http Response 형태로 만들어서 반환
  1. DispatcherServlet
  2. HandlerMapping
  3. Controller
  4. ModelAndView -> DispatcherServlet
  5. ViewResolver -> DispatcherServlet

