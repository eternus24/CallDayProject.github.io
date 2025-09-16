   
   9.16 시작 
   4조 헬스 커뮤니티  프로젝트 
   
   지영 - 부위별 운동  
   
 
서비스 계층의 역할과 흐름

src/main/java
 └── com.spring.boot
     ├── SpringBootBoardApplication.java   ← 메인 실행 클래스
     ├── controller
     │     └── ExerciseController.java     ← 운동 컨트롤러
     ├── dto
     │     └── HealthDTO.java            ← 전체 데이터 객체
     ├── mapper
     │     └── HealthMapper.java         ← 전체 매퍼 인터페이스
     ├── service
     │     ├── ExerciseService.java        ← 서비스 인터페이스
     │     └── ExerciseServiceImpl.java    ← 서비스 구현체
     └── util
           └── MyUtil.java                 ← 공용 유틸 (페이징 등)

src/main/resources
 └── mapper
      └── exerciseMapper.xml               ← MyBatis SQL 매퍼

Controller → Service → Mapper

 
Controller

웹 브라우저나 클라이언트에서 ExerciseController로 
운동 목록 조회, 상세 정보 요청 등과 같은 요청

Service (중간 다리 역할)
ExerciseController는 비즈니스 로직을 직접 처리하지 않고, ExerciseService의 메소드를 호출해 작업을 위임

Mapper
단순히 Mapper를 호출하는 것뿐만 아니라 여러 Mapper의 기능을 조합
추가적인 데이터 가공, 유효성 검사 등의 비즈니스 로직을 담당

   
   1. HealthDTO =====================================
   
   HealthDTO -> // xercise DTO 를 만들어서 
   내가 맡은 파트의 모든 변수들을 넣어서 저장
   
   모든 DTO 의 변수들은 하나의 DTO로 사용하기로 했음
   
   =====================================================
   
   2. ExerciseController
   
   	HTTP 요청을 받아 응답하는 컴보넌트이다.
  	스프링 부트가 자동으로  Bean을 생성한다. 
  	Spring MVC 구조에서JSP 같은 View 파일이 
  	없어도 실행은 가능하기 때문에 직관적으로
  	확인하면서 진행할 수 있어서 좋다. 
  	

영어 뜻 그대로 "조종하는 사람"
→ 사용자의 요청(Request)을 받고, 
응답(Response)을 주는 입구/출구 역할.

웹 브라우저나 Postman 같은 클라이언트가 
URL 요청을 보내면 Controller가 먼저 받음.

비즈니스 로직(계산, 데이터 처리) 은 직접 하지 않음. 
대신 Service에게 위임.

주 기능: URL 매핑 (@GetMapping, @PostMapping 등)
요청 파라미터 받아오기 (예: @RequestParam, @PathVariable)
Service 호출

View(JSP) 또는 JSON 리턴
  	
==========================================================
 고민과 해결 
==========================================================
   
 1) @GetMapping("/exPart")
 
 public String getExerciseByPart
	
사용자가 어떤부위(part)에 해당하는 운동 목록을 보려고 /exPart로 요청하면, 컨트롤러가 그 요청을 받아서 서비스에게 DB 조회를 요청하고,결과 리스트를 **모델에 담아 JSP(view)**로 보내 화면에 보여준다.
	
	@GetMapping("/exPart")
	이 메소드는 브라우저에서 GET 방식으로 /exPart 요청이 들어오면 실행
	modeladdAttribute("exList", exList);

내가 jsp에서 부위별 운동목록을 링크로 보여줄지 , 
아니면 반복문으로 돌려서 운동정보를 출력할지 정해지지 
않아서 아직은 고민중.. 

링크로 넘긴다면 

@GetMapping("{part}")
	이 메소드는 브라우저에서 GET 방식으로 /exPart 요청이 들어오면 실행
	이게 맞는 방식이고 
	
	<c:forEach>로 반복문 돌려서 운동 정보를 출력 방식을 택한다면 
 @GetMapping("/exPart/{part}")
    public String getExerciseByPart(@PathVariable("part") String part, Model model) {
    
    "/exPart/{part} 으로 매핑을 설정하고 @PathVariable 넣어주면됨 
    <h2>운동 부위: ${exPart}</h2>

<c:forEach var="item" items="${exList}">
    <div>
        <p>운동 이름: ${item.exName}</p>
        <p>효과: ${item.effect}</p>
        <p>방법: ${item.method}</p>
        <hr>
    </div>
</c:forEach>
----------------------------------------------------------------------------
	  
   

    
    
    Mapper 연동: 
ExerciseServiceImpl은 ExerciseService 인터페이스의 구현체로서,
실제 데이터베이스와 소통하는 ExerciseMapper를 호출. 
이 과정에서 Service는 Mapper를 통해 데이터베이스에 접근.


데이터 처리: ExerciseMapper는 SQL 쿼리를 실행하여 데이터베이스의
 데이터를 조회, 삽입, 수정, 삭제하는 작업을 수행.

결과 반환:

ExerciseMapper는 처리 결과를 ExerciseServiceImpl에 반환 ExerciseServiceImpl은 이 결과를 그대로 반환하거나 필요에 따라
가공하여 ExerciseController에 전달.

컨트롤러 응답:
ExerciseController는 ExerciseService로부터 
최종 결과를 받아 사용자에게 보여줄 화면(View)을 렌더링하거나 JSON 형식.

인터페이스와 구현체를 분리하는 이유

유연성: ExerciseService라는 인터페이스를 사용하면 
나중에 데이터베이스를 변경하거나 비즈니스 로직을 수정해야 할 때 ExerciseServiceImpl만 바꾸고 Controller는 수정할 필요가 없고
코드의 유연성을 높여줌

테스트 용이성:
인터페이스를 사용하면 실제 데이터베이스 연결 없이도 테스트(Mocking)를
쉽게 할 수 있음.

ExerciseService를 가짜 객체(Mock Object)로 만들어서 컨트롤러의
기능을 독립적으로 테스트할 수 있습니다.

결합도 감소:
Controller는 ExerciseServiceImpl이라는 구체적인 클래스에 직접 
의존하는 대신, ExerciseService라는 추상화된 인터페이스에 의존. 
이렇게 하면 각 계층 간의 결합도가 낮아져 유지보수가 쉬워짐
  
    
