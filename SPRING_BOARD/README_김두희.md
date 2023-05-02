# 🎬 Duhui_Board (Spring Framework)

<br>

__✔ 작성 정보__

- 작  성  자 : 서울 8반 김두희

- 날        짜 : 2023-04-25

- 환        경 : Windows 10

- 개발도구 : Spring Tool Suite

<br>

✔ **중점을 둔 기능**

- 게시판 CRUD 기본 기능 구현

- 게시글 별 다중 File 관리기능 구현

- 회원관리 기능 구현 및 권한에 따른 인가 차등 부여

- Spring Security Framework를 이용한 비밀번호 암호화

- 로그인한 회원 별 마이페이지 생성

- 게시글 좋아요 기능 구현

<br>

## 🔹프로그램 개요

<br>

 📒 **<u>로그인, 로그아웃 기능</u>**

- Spring Security Framework를 활용한 Password 암호화

- 권한(관리자, 일반유저)에 따른 접근 권한 차등 부여

- 회원가입 시 아이디 중복 방지



 📒 **<u>마이페이지 기능</u>**

- 자신의 정보를 확인할 수 있는 페이지 구현

- 좋아요를 누른 게시글 조회 및 좋아요 취소 기능 구현

- 회원탈퇴 기능 구현



 📒 **<u>게시글 작성</u>**

- 다중 파일 업로드 및 다운로드 기능 구현

- 게시글 수정 시 파일 수정도 가능하게 구현

- 로그인한 사용자 정보에 따라 글쓴이 자동 적용

  

 📒 **<u>게시글 수정 및 삭제</u>**

- 게시글 수정 시 파일 수정 및 삭제 가능하게 구현

  - 개별 파일을 독립적으로 삭제 가능하게 구현

  - 수정 시 추가로 파일 업로드 가능하게 구현

  - UUID를 활용하여 같은 이름의 파일 중복되지 않도록 생성

- 게시글 작성자 또는 권한이 "관리자"일 경우에만 게시글의 수정 및 삭제 가능



 📒 **<u>게시글 좋아요 기능</u>**

- 로그인한 사용자 정보에 따라 해당 게시글을 좋아요 누르지 않았다면 좋아요 설정버튼, 눌렀다면 좋아요 취소 버튼이 뜨도록 구현

- 좋아요 설정 시 마이페이지에서 해당 게시글 조회 기능 구현

  

 📒 **<u>리뷰 작성</u>**

- 게시글 별 리뷰 작성 가능

- 게시글 삭제 시 리뷰도 함께 삭제되어 DB 최적화

  

 📒 **<u>리뷰 수정 및 삭제</u>**

- 로그인한 사용자 정보에 따라 리뷰에 대한 수정 및 삭제 권한 부여 



 📒 **<u>게시글 검색 및 정렬</u>**

- 작성자, 제목 등 검색조건에 따라 게시글 조회 및 정렬 기능 구현



 📒 **<u>파일 다운로드</u>**

- 원래의 파일명으로 다운로드 되도록 구현



 📒 **<u>Interceptor를 활용한 권한 인가</u>**

- 미로그인 시 회원가입 및 로그인 외 이용 불가

- 회원정보 페이지는 관리자만 접근 가능하게 만듦

  

 📒 **<u>ExceptionHandler를 이용한 에러 처리</u>**

- 에러 발생 시 사용자가 Error명을 확인할 수 없도록 하기 위해 에러페이지를 생성

- 로그인한 유저가 관리자일 경우 Error명 확인 가능

<br>

## 🔹 API 명세서

![image](https://user-images.githubusercontent.com/118238663/235281690-0ec2f04f-be1e-4207-a749-721f9c449a11.png)

<br>

## 🔹 ERD Diagram

![image](https://user-images.githubusercontent.com/118238663/235281575-3bd5d3d7-5de5-41e2-8925-ea2e73228680.png)

<br>

## 🔹 폴더 구조

📂 **<u>폴더 구조</u>**

```
📂 src/main/java
  📂 com.ssafy.board.controller
    ㄴ BoardController.java
    ㄴ ExceptionController.java
    ㄴ UserController.java
    
  📂 com.ssafy.board.interceptor
    ㄴ LoginInterceptor.java
    ㄴ ManagerInterceptor.java
    
  📂 com.ssafy.board.model.dao
    ㄴ BoardDao.java
    ㄴ UploadFileDao.java
    ㄴ UserDao.java
    ㄴ ReviewDao.java
    
  📂 com.ssafy.board.model.dto
    ㄴ Board.java
    ㄴ SearchCondition.java
    ㄴ UploadFile.java
    ㄴ User.java
    ㄴ Review.java
    
  📂 com.ssafy.board.model.service
    ㄴ BoardService.java
    ㄴ UploadFileService.java
    ㄴ UserService.java
    ㄴ ReviewService.java
    ㄴ BoardServiceImpl.java
    ㄴ UploadFileServiceImpl.java
    ㄴ UserServiceImpl.java
    ㄴ ReviewServiceImpl.java
    
  📂 com.ssafy.board.model.view
    ㄴ FileDownLoadView.java
    
    
📂 src/main/resources
  📂 mappers
    ㄴ boardMapper.xml
    ㄴ uploadFileMapper.xml
    ㄴ userMapper.xml
  ㄴ DUHUIschema.xml
  ㄴ log4j.xml
  
📂 src/main/webapp/WEB-INF
  📂 spring
    📂 appServlet
      ㄴ servlet-context.xml
    ㄴ root-context.xml
    
  📂 views
    📂 board
      ㄴ detail.jsp
      ㄴ list.jsp
      ㄴ updateform.jsp
      ㄴ writeform.jsp
      
    📂 common
      ㄴ bootstrap.jsp
      ㄴ header.jsp
      
    📂 error
      ㄴ 404.jsp
      ㄴ commonError.jsp
      
    📂 review
      ㄴ modifyForm.jsp
      ㄴ review.jsp
      
    📂 user
      ㄴ myPage.jsp
      ㄴ loginform.jsp
      ㄴ signupform.jsp
```

<br>

## 🔹 구현 결과

🖥 **<u>구현 결과</u>**

1. **일반 유저**

   1. **메인화면**
   
      ![image-20230429120225022](https://user-images.githubusercontent.com/118238663/235281870-196442d3-0b1d-49ca-b154-d4edad32f921.png)
   
      <br>
   
   2. **로그인** 
   
      ![image-20230429120236595](https://user-images.githubusercontent.com/118238663/235281889-f9f75c57-774d-4069-a33c-8732264ed3da.png)
   
   ​	<br>
   
   2. **일반회원은 마이페이지와 회원탈퇴 기능이 보인다.**
   
      ![image-20230429115356548](https://user-images.githubusercontent.com/118238663/235280688-971bad8b-1046-420e-a342-54bacee98432.png)
   
      <br>
   
   3. **마이페이지에서는 회원정보, 좋아요한 글 조회가 가능하고, 회원탈퇴가 가능하다.**
   
      ![image-20230429120316550](https://user-images.githubusercontent.com/118238663/235280720-0d16089b-4ac4-4625-8130-2edd929a551d.png)
   
      <br>
   
   4. **글등록 - 다중 파일 업로드 가능**
   
      ![image-20230429120404712](https://user-images.githubusercontent.com/118238663/235280721-678c19bb-58b1-4c2f-9c86-e7ebbbdccb1a.png)
   
      ![image-20230429120416821](https://user-images.githubusercontent.com/118238663/235280722-6160d781-abe1-4ef9-bfbf-064cfd378437.png)
   
      
   
      <br>
   
   5. **글 작성 테스트 / 여러 개의 파일 중 원하는 파일만 선택하여 다운로드 가능**
   
      ![image](https://user-images.githubusercontent.com/118238663/235280803-a57ccae1-b460-4837-a0df-614971abc5c5.png)
   
      <br>
   
   6. **여러 개의 파일 중 원하는 파일만 삭제 가능**
   
      ![image](https://user-images.githubusercontent.com/118238663/235280921-5317b42a-ba2a-4f5d-9439-a71fb872cede.png)
   
      <br>
   
   7. **자신이 작성한 글 아니면 수정 및 삭제 불가**
   
      ![image-20230429121348938](https://user-images.githubusercontent.com/118238663/235282012-02570525-60f9-4ec2-a223-3dd90b1472fe.png)

   
      <br>
   
   8. **댓글 작성 기능**
   
      ![image-20230429121411198](https://user-images.githubusercontent.com/118238663/235281015-e9b7ad61-eda3-4ec5-a05f-2dd3663e0713.png)
   
      <br>
   
   9. **댓글 수정 기능**
   
      ![image-20230429121427501](https://user-images.githubusercontent.com/118238663/235281043-2adf60fa-94fb-4614-aa1a-76bca95f1b5d.png)
   
   
      <br>
   
   11. **검색 및 정렬 결과**
   
       ![image](https://user-images.githubusercontent.com/118238663/235280992-81ae25ec-fc9c-4df4-b9e4-1a11c334d24d.png)
   
       <br>
   
   12. **게시글 좋아요 기능**
   
       ![image](https://user-images.githubusercontent.com/118238663/235281156-5bc03ec6-ed42-4545-a763-0fade2b07852.png)
   
       <br>
   
   13. **회원가입**
   
       ![image](https://user-images.githubusercontent.com/118238663/235281214-733a06c9-8e73-4ccb-ab67-9445b3d46189.png)
   
       ![image-20230429122150595](https://user-images.githubusercontent.com/118238663/235281235-cf846cec-f798-4432-944d-5e74e7865c0f.png)

<br><br>

2. **관리자**

   1. **관리자는 회원관리와 로그아웃 기능 가능(회원탈퇴는 막아놓음)**

      ![image](https://user-images.githubusercontent.com/118238663/235281255-b6a7d210-a27b-46ec-ace0-bbf044c2dc8b.png)

      <br>

   2. **관리자 로그인의 경우 자신이 작성한 글이 아니어도 수정 및 삭제 가능**

      ![image](https://user-images.githubusercontent.com/118238663/235281275-f997bd9d-2331-4ba7-b2e2-39da675814c3.png)

      <br>

   3. **회원관리 페이지**

      ![image-20230429122351108](https://user-images.githubusercontent.com/118238663/235282043-942d186d-3f24-4b6d-bb31-b7eb8315ed22.png)


      <br>

   4. **관리자가 아닐 시 url을 이용하여 회원관리 페이지 접근 불가**

      ![image-20230429120121681](https://user-images.githubusercontent.com/118238663/235280691-f6b2d869-a075-43b5-b66d-faef3c3b1f29.png)

      <br>

3.  데이터베이스
    1. **회원의 비밀번호는 암호화해서 저장되어있으며 역할 지정되어있음**

   ![image](https://user-images.githubusercontent.com/118238663/235281297-71ef6939-e77f-4808-a430-7d6a95abd6e6.png)

<br>

## 🧐 느낀 점 

<br>

**:ballot_box_with_check: 아쉬운 점** (만족도 : 별 4개)

1. ~~게시글 당 파일 여러 개를 업로드하고 각각을 download, 수정 및 삭제 할 수 있도록 구현하려고 했는데, 너무 일이 커져버려서 하지 못했다.~~ 
     <br>=> **다중 파일 업로드 및 다운로드 / 수정 및 삭제 구현 완료**
2. ~~리뷰 기능도 추가하면 좋았을 것 같다. .~~
     <br>=> **리뷰 기능 추가 완료**
3. ~~UUID를 활용해서 파일을 저장할 때 다른 이름으로 저장하는 것은 했는데, 다운로드 시에 원래의 이름으로 다운로드 되게 하지는 못했다. 아직 파일 다운로드에 대해서 제대로 파악하지 못했고, 기존에 있던 코드에서 파일을 넣는 부분만 할 수 있었다.~~

     => **원본 파일명으로 다운로드 기능 추가**
4. ~~유저에 대한 마이페이지~~나 찜 기능을 구현해보면 좋았을 것 같다. 다른 유저를 검색해보고 친구추가하는 기능 같은 것도 조금 더 설계를 꼼꼼히 했으면 가능했을 것 같다.
     <br>=> **마이페이지 추가 완료**
5. **게시글 좋아요 기능 추가 완료**



**:chart_with_upwards_trend: Spring에 대한 이해도 조금은 상승**

​	아직 배워야할게 많은 것 같다! 그래도 이번에 **Spring Security 프레임워크**를 한 번 써봤는데, 직접 구현하려고 했으면 훨씬 오래 걸렸을 일을 짧은 시간 내에 해결할 수 있었다. 원래는 학기말 프로젝트 때 써보려고 알아보고 있었는데, 생각보다 어렵진 않았다. 다만 해시 암호 알고리즘이라 암호화는 되는데 복호화가 불가능했다. SESSION을 활용한 방법도 있다고 들었는데, 다음엔 그것까지 적용해보고 싶다.

​	이번에는 완전 노베이스는 아니고 SSAFY_BOARD를 가져와서 어느정도 틀이 갖춰져 있는 상태에서 진행했는데 다음에는 프로젝스 생성부터 설정까지 해봐야겠다. 그래도 월말평가를 대비하며 어떤 의존성을 주입해야하고, 어떤 것들을 context에 등록해야하는지 익숙해질 수 있었다.

​	아직 Spring 끝나지 않았으니 나머지도 잘 배워서 써먹어봐야겠다. Boot도 기대된다!!

