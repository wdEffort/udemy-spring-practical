# Spring Framework MVC Practical

---

## 게시판 프로젝트 구성

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. Oracle 데이터베이스를 사용하는 경우 pom.xml 파일에 Oracle 데이터베이스 드라이버 사용을 위한 의존 설정 추가
   ```xml
   <repositories>
      <repository>
         <id>oracle</id>
         <name>ORACLE JDBC Repository</name>
         <url>https://repo.spring.io/plugins-release/</url>
      </repository>
   </repositories>
   
   <!-- https://mvnrepository.com/artifact/oracle/ojdbc6 -->
   <dependencies>
      <dependency>
         <groupId>com.oracle</groupId>
         <artifactId>ojdbc6</artifactId>
         <version>11.2.0.3</version>
      </dependency>
   </dependencies>
   ```

---

## 스프링 JDBC를 위한 템플릿

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. 주요 기능 : 기존 방식과 같이 데이터베이스 Connection을 구하고, try-catch-finally로 자원을 관리하는 등의 중복된 코드를 제거할 수 있다.
2. JDBC를 위한 세 개의 템플릿 클래스
    - JdbcTemplate : 스프링의 가장 기본적인 템플릿으로 `색인된 파라미터(Indexed Parameter)` 기반의 쿼리를 통해서 데이터베이스에 액세스하는 기능을 제공한다.
    - NamedParameterJdbcTemplate : SQL과 값들을 색인된 파라미터 대신에 `명련된 파라미터(Named Parameter)`로 바인딩하여 쿼리를 수행할 수 있게 해준다.
    - SimpleJdbcTemplate : Java 5부터 지원하는 기능 중에 오토 박싱, 제네릭스, 가변 파라미터 목록 등을 활용해서 쉽게 템플릿을 사용할 수 있도록 하는 기능을 제공한다.(스프링 3.1에서
      제거 되었다.)
3. 단, Java 1.4 이하의 버전에서는 사용할 수 없다.

---

## JdbcTemplate 사용 방법

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. pom.xml 파일에 `org.springframework.spring-jdbc` 의존 설정
    ```xml
   <!-- JdbcTemplate -->
   <dependencies>
        <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.2.6.RELEASE</version>
        </dependency>
   </dependencies>
    ```
2. servlet-context.xml 설정 파일에 `DataSource`과 `JdbcTemplate` Bean 설정
   ```xml
   <!-- Oracle DataSource -->
   <bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
      <property name="url" value="jdbc:oracle:thin:@[IP]:[PORT]:[SID]"/>
      <property name="username" value="[username]"/>
      <property name="password" value="[password]"/>
   </bean>

   <!-- JdbcTemplate -->
   <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
      <!-- DataSource Bean을 주입 -->
      <property name="dataSource" ref="dataSource"/>
   </bean>
   ```

---

## JdbcTemplate에서 사용할 수 있는 메소드

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. query()  : SELECT 쿼리를 실행할 때 사용하는 메소드이다.
    - public \<T\> List\<T\> `query(String sql, Object[] args, RowMapper<T> rowMapper)` throws DataAccessException
    - public \<T\> List\<T\> `query(String sql, RowMapper<T> rowMapper)` throws DataAccessException : `sql` 파라미터는 실행시킬
      쿼리(문자열)이고, `rowMapper` 파라미터는 쿼리 실행 결과가 담길 RowMapper 객체이다.
        1) RowMapper\<T\>는 인터페이스이며 mapRow() 메소드를 정의하고 있다. 이는 ResultSet에서 읽어온 값을 이용해서 `원하는 타입의 객체`를 생성한뒤 반환한다.
            - T mapRow(ResultSet rs, int rowNum) throws SQLException;
            - 구현 클래스 : BeanPropertyRowMapper\<T\>
2. queryForObject() : 쿼리 실행 결과의 행의 개수가 한 개인 경우 사용하는 메소드이다. 전달되는 각 파라미터가 query() 메소드와 동일하지만, List 객체를 반환하지 않고, 한 개의 객체를
   반환한다. 만약, 반환되는 행(레코드)의 개수가 여러 개인 경우 IncorrectResultSizeDataAccessException이 발생한다.
    - public \<T\> T `queryForObject(String sql, RowMapper<T> rowMapper)` throws DataAccessException
    - public \<T\> T `queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)` throws DataAccessException
3. queryForInt(), queryForLong() : 반환 타입이 Object가 아니고, int, Long 타입의 결과를 구할 때 사용하는 메소드이다.
4. update() : INSERT, UPDATE, DELETE 쿼리를 실행할 때 사용하는 메소드이다. 쿼리 실행 결과 변경된 행의 개수를 반환한다.
    - public int `update(String sql)` throws DataAccessException :
    - public int `update(String sql, Object[] args)` throws DataAccessException :
    - public int `update(String sql, @Nullable PreparedStatementSetter pss)` throws DataAccessException :
    - public int `update(PreparedStatementCreator psc)` throws DataAccessException :
5. execute() : Connection을 직접 사용해야 하는 경우에 사용하는 메소드이다. Connection의 생성과 종료는 JdbcTemplate에서 처리하기 때문에 직접 할 필요는 없다.

---

## 트랜잭션 전략

#### 프로젝트 패키지 : com.udemy.spring.practical.ticket

1. 스프링에서 제공하는 프로그래밍적인 트랙잭션 관리 방법
2. PlatformTransactionManager를 사용하는 방법
    - org.springframework.jdbc.datasource.DataSourceTransactionManager 클래스를 servlet-context.xml 설정 파일에서 Bean으로 등록
      ```xml
       <!-- TransactionManager -->
       <bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <property name="dataSource" ref="dataSource"/>
       </bean>
      ```
    - org.springframework.transaction.TransactionDefinition과 org.springframework.transaction.TransactionStatus 클래스를 사용
        1) 트랙잭션 초기화
        2) 트랙잭션 커밋(Commit), 롤백(Rollback)
3. TransactionTemplate을 사용하는 방법
   ```xml
   <!-- TransactionTemplate -->
    <beans:bean name="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
        <beans:property name="transactionManager" ref="transactionManager"/>
    </beans:bean>
   ```
    - `Callback` 접근 방법을 사용하는 방식이며, 리소스 획득과 해제 작업으로부터 어플리케이션 코드를 해방시켜주는 방법이다.
        1) 즉, try-catch-finally를 할 필요가 없다.
    - TransactionCallback 인터페이스 구현 객체를 사용한다.
        1) `doInTransaction()` 메소드를 구현해야 한다.
    - TransactionCallbackWithoutResult를 사용한다.
        1) `doInTransactionWithoutResult()` 메소드를 구현해야 한다.

---

## 트랜잭션 전파(Propagation)

#### 프로젝트 패키지 : com.udemy.spring.practical.ticket

1. 트랙잭션을 시작하거나 기존 트랙잭션에 참여하는 방법을 결정하는 것이다.
2. 전파 속성
    - required(상수 값: 0) : (Default) 하나의 트랙잭션에 의해 다른 트랙잭션까지 묶여서 처리된다.
    - supports(상수 값: 1) : 트랜잭션을 필요로 하지 않지만 기존의 트랜잭션이 존재하는 경우 트랜잭선을 사용한다.
    - mandatory(상수 값: 2) : 메소드 실행시 트랜잭션이 필요하다라는 것을 의미한다. 진행중인 트랜잭션이 없을 경우 예외를 발생시킨다.
    - requires_new(상수 값: 3) : 각각 트랜잭션을 처리하는 것을 의미한다.
    - not_supported(상수 값: 4) : 트랜잭션을 필요로 하지 않는다. 기존의 트랜잭션이 존재하면 일시 중지하고 메소드 실행이 끝난 후에 트랜잭션을 계속 진행한다.
    - never(상수 값: 5) : 트랜잭션을 필요로 하지 않는다. 진행중인 트랜잭션이 존재하면 예외를 발생시킨다.

---

## 스프링을 이용한 파일 업로드

#### 프로젝트 패키지 : com.udemy.spring.practical.upload

1. pom.xml 설정 파일에 `commons-fileupload` 의존 설정
   ```xml
   <dependencies>
       <!-- File Upload -->
       <dependency>
           <groupId>commons-fileupload</groupId>
           <artifactId>commons-fileupload</artifactId>
           <version>1.3.3</version>
       </dependency>
   </dependencies>
   ```
2. servlet-context.xml 설정 파일에 `MultipartResolver` Bean 설정
    - MultipartResolver 프로퍼티 종류
        1) maxUploadSize : 최대 업로드 가능한 바이트 크기
        2) maxInMemorySize : 디스크에 임시 파일을 생성하기 전에 메모리에 보관할 수 있는 최대 바이트 크기
        3) defaultEncoding : 요청을 파싱할 때 사용할 캐릭터 인코딩(기본 값 : ISO-8859-1)
   ```xml
    <!-- MultipartResolver -->
    <bean name="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="10485760"/>
    </bean>
   ```

---

## AbstractView를 이용한 파일 다운로드 구현

#### 프로젝트 패키지 : com.udemy.spring.practical.upload

1. servlet-context.xml 설정 파일에 `BeanNameViewResolver` Bean 설정
    - 이때 InternalResourceViewResolver 보다 높은 우선 순위를 갖도록 해야 한다.
      ```xml
      <!-- Resolves views seleted for rendering by @Controllers to .jsp resources in the /WEB-INF/views -->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <property name="prefix" value="/WEB-INF/views/"/>
         <property name="suffix" value=".jsp"/>
         <!-- 항상 다른 ViewResolver 보다 낮은 우선순위를 갖도록 설정 -->
         <property name="order" value="1"/>
      </bean>

      <!-- BeanNameViewResolver -->
      <bean name="beanNameViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
         <!-- InternalResourceViewResolver 보다 높은 우선순위를 갖도록 설정 -->
         <property name="order" value="0"/>
      </bean>
      ```
2. AbstractView 클래스를 이용하여 파일 다운로드 비즈니스 로직 구현
    - void `renderMergedOutputModel()` 메소드를 구현해야 한다.
   ```java
   public class FileDownloadView extends AbstractView {
   
       public FileDownloadView() {
           setContentType("application/download; charset=utf-8");
       }
   
       @Override
       protected void renderMergedOutputModel(Map<String, Object> map,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
           System.out.println("FileDownloadService.renderMergedOutputModel() called ...");
   
           String uploadPath = "/Users/yoman/Workspace/Java/intelliJ/personal/udemy/spring-practical/upload"; // 파일 업로드 경로
           String savedFileName = "test.txt";
           File file = new File(uploadPath + "/" + savedFileName); // 파일 경로에서 파일을 가져옴
   
           response.setContentType(getContentType()); // 컨텐츠 타입(MIME 타입 지정, 캐릭터 인코딩 지정 등)
           response.setContentLength(100); // 파일의 크기
   
           String userAgent = request.getHeader("User-Agent"); // 헤더 정보에서 접속 기기 정보를 가져옴
           String fileName = null;
   
           // 한글 파일명에 대한 깨짐 현상 처리
           if (userAgent.indexOf("MSIE") > -1) { // 이용하는 브랑우저가 IE인 경우
               System.out.println();
               fileName = URLEncoder.encode(file.getName(), "UTF-8");
           } else {
               fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
           }
   
           response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
           response.setHeader("Content-Transfer-Encoding", "binary");
   
           OutputStream out = response.getOutputStream();
           FileInputStream in = null;
   
           try {
               in = new FileInputStream(file);
               FileCopyUtils.copy(in, out);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               if (in != null) {
                   try {
                       in.close();
                   } catch (IOException e) {
                       System.out.println("IOException : " + e.toString());
                   }
               }
           }
   
           out.flush();
       }
   
   }
   ```
3. AbstractView 클래스를 구현한 클래스를 servlet-context.xml 설정 파일에 Bean으로 등록
    - BeanNameViewResolver 해당 Bean을 View로 사용하게 된다.
      ```xml
      <!-- 파일 다운로드 View -->
      <bean name="fileDownloadView" class="com.udemy.spring.practical.upload.view.FileDownloadView"/>
      ```

---

## Excel 다루기

#### 프로젝트 패키지 : com.udemy.spring.practical.excel

1. pom.xml 설정 파일에 `commons-fileupload` 의존 설정
   ```xml
   <dependencies>
        <!-- Apache POI -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.1.2</version>
        </dependency>
   </dependencies>
   ```
2. servlet-context.xml 설정 파일에 `ResourceBundleViewResolver` Bean 설정
    - 이때 InternalResourceViewResolver 보다 높은 우선 순위를 갖도록 해야 한다.
      ```xml
      <!-- Resolves views seleted for rendering by @Controllers to .jsp resources in the /WEB-INF/views -->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <property name="prefix" value="/WEB-INF/views/"/>
         <property name="suffix" value=".jsp"/>
         <!-- 항상 다른 ViewResolver 보다 낮은 우선순위를 갖도록 설정 -->
         <property name="order" value="1"/>
      </bean>

      <!-- ResourceBundleViewResolver -->
      <bean name="resourceBundleViewResolver" class="org.springframework.web.servlet.view.ResourceBundleViewResolver">
        <!-- properteis 파일명 설정 -->
        <beans:property name="basename" value="views"/>
         <!-- InternalResourceViewResolver 보다 높은 우선순위를 갖도록 설정 -->
         <property name="order" value="0"/>
      </bean>
      ```
3. View를 담당할 properties 파일 생성(경로: /src/main/resources)
    - /src/main/resources/views.properties

--- 

## Maven 하이버네이트(Hibernate) Validator 적용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.member

1. pom.xml 설정 파일에 `Hibernate` 의존 설정
   ```xml
    <!-- 저장소 -->
    <repositories>
        <!-- Hibernate Repository(JBossdp 포함되어 있음) -->
        <repository>
            <id>jboss-repo</id>
            <name>Hibernate Repository</name>
            <url>https://repository.jboss.org/nexus/content/groups/public</url>
        </repository>
    </repositories>
   
    <dependencies>
        <!-- Hibernate Validator -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.1.5.Final</version>
        </dependency>

        <!-- Hibernate Annotaion -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-annotations</artifactId>
            <version>3.5.3-Final</version>
        </dependency>
    </dependencies>
   ```
2. Controller의 핸들러 메소드에서 `@Valid(javax.validation.Valid)` 어노테이션을 이용하여 메소드 파라미터로 있는 커맨드 객체에 대한 검증 처리를 진행한다.
   ```java
   @Controller
   @RequestMapping("/member")
   public class MemberController {

       @RequestMapping(value = "/join", method = RequestMethod.POST)
       public String join(@Valid Member member, BindingResult result) {
            // code
       }
   }
   ``` 
3. Form Check 진행
    - 예제
      ```java
      import javax.persistence.*;
      import javax.validation.constraints.NotNull;
      import javax.validation.constraints.Pattern;
      import javax.validation.constraints.Size;
      
      @Entity // 데이터베이스 테이블에 대응되는 개념
      public class Member {
         @Id // 테이블 Primary Key에 대응되는 개념
         @Column
         @Size(min = 4, max = 8, message = "아이디는 4 ~ 8자리 이내로 입력해 주세요.") // 입력 길이 제약사항 설정
         // @GeneratedValue(strategy = GenerationType.AUTO) // Oracle의 Sequence, MySQL의 auto_increment와 같은 개념
         private String id;
      
         @Column // 테이블 컬럼에 대응되는 개념
         @NotNull // Not Null 제약사항 설정
         @Size(min = 2, max = 5, message = "이름은 2 ~ 5자리 이내로 입력해 주세요.") // 입력 길이 제약사항 설정
         private String name;
         
         @Column
         @NotNull
         @Size(min = 1, max = 3, message = "나이는 1 ~ 3자리 숫자로 입력해 주세요.") // 입력 길이 제약사항 설정
         // private int age; // 하이버네이트의 Validator는 Integer형에 대한 검증을 지원하지 않음.
         private String age;
      
         // Getter, Setter
      }
      ```

---

## java.util.regex 정규화 API 이용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.member

1. [오라클 Java 8 API 문서](https://docs.oracle.com/javase/8/docs/api/index.html) 에서 java.util.regex에 해당하는 내용을 참고한다.
2. 사용 방법
   ```java
   import javax.persistence.*;
   import javax.validation.constraints.NotNull;
   import javax.validation.constraints.Pattern;
   import javax.validation.constraints.Size;
   
   @Entity 
   public class Member {
   
      @Id 
      @Column
      @Size(min = 4, max = 8, message = "아이디는 4 ~ 8자리 이내로 입력해 주세요.")
      private String id;
   
      @Column 
      @NotNull 
      @Pattern(regexp = "\\S{2,5}", message = "이름은 2 ~ 5자리 이내로 입력해 주세요.") // 정규 표현식을 이용한 제약사항 설정(java.util.regex)
      private String name;
   
      @Column
      @NotNull
      @Pattern(regexp = "\\d{1,3}", message = "나이는 1 ~ 3자리 숫자로 입력해 주세요.")
      private String age;
   
      // Getter, Setter
   }
   ```

---

## 마이바티스(MyBatis) 사용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. 마이바티스가 무엇이고, 사용법에 대한 상세한 내용은 [마이바티스 홈페이지](https://mybatis.org/mybatis-3/ko/index.html) 를 참고한다.
2. 마이바티스 설정 작업
    - 스프링 프레임워크와 마이바티스를 연동하기 위한 라이브러리를 설정한다.
        1) MyBatis 프레임워크 추가
        2) MyBatis-Spring 모듈 추가
            - 마이바티스 프레임워크와 스프링 프레임워크를 연결해주는 역할
        3) Spring-JDBC 라이브러리 추가
            ```xml
            <dependencies>
                 <!-- mybatis -->
                 <dependency>
                     <groupId>org.mybatis</groupId>
                     <artifactId>mybatis</artifactId>
                     <version>3.5.4</version>
                 </dependency>
              
                 <!-- mybatis-spring -->
                 <dependency>
                     <groupId>org.mybatis</groupId>
                     <artifactId>mybatis-spring</artifactId>
                     <version>2.0.4</version>
                 </dependency>
              
                 <!-- spring-jdbc -->
                 <dependency>
                     <groupId>org.springframework</groupId>
                     <artifactId>spring-jdbc</artifactId>
                     <version>${spring-webmvc-version}</version>
                 </dependency>
            </dependencies>
           ```
        4) Spring-Test 라이브러리 추가
            ```xml
            <dependencies>
                <!-- spring-test -->
                <dependency>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-test</artifactId>
                    <version>${spring-webmvc-version}</version>
                </dependency>
            </dependencies>
           ```
    - 데이터베이스와 연결을 담당하는 `DataSouce` 객체를 설정한다.
        1) servelt-context.xml에 설정
            ```xml
           <!-- Oracle DataSource -->
           <bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
              <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
              <property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
              <property name="username" value="udemy"/>
              <property name="password" value="udemy"/>
           </bean>
   
           <!-- JdbcTemplate -->
           <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
              <!-- DataSource Bean을 주입 -->
              <property name="dataSource" ref="dataSource"/>
           </bean>
            ```
    - 마이바티스의 핵심인 `SqlSessionFactory` 객체를 설정한다.
        1) servelt-context.xml에 설정
         ```xml
         <!-- MyBatis SqlSessionFactory -->
         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- DataSource Bean을 주입 -->
            <property name="dataSource" ref="dataSource"/>
            <!-- SQL Mapper XML 파일 경로 -->
            <property name="mapperLocations" value="classpath:mapper/*.xml"/>
         </bean>
   
         <!-- MyBatis sqlSessionTemplate -->
         <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
            <!-- SqlSessionFactoryBean 주입 -->
            <constructor-arg ref="sqlSessionFactory"/>
         </bean>
         ```

---

## 마이바티스(MyBatis) 구조

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs

1. 기존 방식에서 DAO와 DB의 연결을 JDBC를 이용해서 했다면, MyBatis를 사용하는 경우 MyBatis-Spring이라는 `Persisrtance 프레임워크`를 이용한다.
    - MyBatis-Spring은 `SqlSessionTemplate`으로부터 마이바티스와 스프링을 연결시키는 역할을 하고, 내부에 MyBatis를 가지고 있다.
        1) SqlSessionTemplate : MyBatis-Spring이 가지고 있으며, DAO에 주입되어 실질적으로 마이바티스와 스프링을 연결시켜주는 역할을 한다.
    - MyBatis에서는 마이바티스의 핵심인 `SqlSessionFactory`를 가지고 있다.
        1) SqlSessionFactory : 실제 DB와 연결하고, 쿼리를 실행시킬 수 있다.
            - `DataSource`를 포함시킨다.
            - SQL Query를 매핑시킬 수 있는 `Mapper(XML 또는 인터페이스로 구현)`가 있다.
2. SqlSessionTemplate을 DAO에 연결시켜 쿼리를 실행시키는 구조이므로, SqlSessionTemplate 안에 SqlSessionFactory를 포함시키는 설정을 해주어야 한다.

---

## 스프링 + 마이바티스

#### 프로젝트 패키지 : com.udemy.spring.practical.bbs, com.udemy.spring.practical.hr

1. 마이바티스를 사용하는데 있어서 SQL문을 사용하는 방식
    - (권장) XML만을 이용해서 SQL문을 설정하고 DAO에서 XML을 찾아서 실행하는 코드를 작성하는 방식이다.
        1) 장점 : SQL문은 별도의 XML로 작성되기 때문에 SQL문의 수정이나 유지보수가 많은 경우 관리하기가 좋다.
        2) 단점 : 개발시 코드의 양이 많아진다.(복잡성 증가)
    - 어노테이션과 인터페이스만을 이용해서 SQL문을 설정하는 방식
        1) 장점 : DAO 없이도 개발이 가능하기 때문에 생산성이 향상된다.
        2) 단점 : SQL문을 어노테이션으로 작성하기 때문에 매번 수정이 일어나는 경우 재컴파일을 해야한다.
    - 인터페이스와 XML로 작성된 SQL문을 활용하는 방식
        1) 장점 : 간단한 SQL문은 어노테이션을 활용하고, 복잡한 SQL문은 XML로 처리하므로 유연성이 증가된다.
        2) 단점 : 유지보수가 많은 프로젝트에서는 부적합한 방식이다.
2. 개발 순서
    - DB 설계 및 개발 준비
    - 도메인 객체의 설계 및 클래스 작성
    - `DAO 인터페이스` 작성
    - XML SQL Mapper 생성과 SQL문 작성
    - 마이바티스에서 XML SQL Mapper를 인식하도록 설정
    - DAO 구현(SqlSessionTemplate 설정 및 구현 클래스 작성)

---

## 마이바티스 Generator 플러그인 사용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.mbgmember, com.udemy.spring.practical.newmember

> - [메이븐 환경 프로젝트에서 MyBatis Generator 설정하기 참고 링크](https://mybatis.org/generator/running/runningWithMaven.html)
> - [메이븐 환경 프로젝트에서 MyBatis Generator 상세 설정하기 참고 링크](https://www.fatalerrors.org/a/super-detailed-configuration-of-mybatis-generator.html)

1. 마이바티스 제너레이터(MyBatis Generator, MBG)는 설계된 데이터베이스에 연결해 Model, DAO, SQL Mapper를 자동으로 생성해주는 코드 생성기이다.
2. pom.xml 설정 파일에 `MyBatis Generator` 플러그인 추가
   ```xml
   <plugins>
      <!-- MyBatis Generator Plugin -->
      <plugin>
          <groupId>org.mybatis.generator</groupId>
          <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.4.0</version>
          <configuration>
              <configurationFile>${basedir}/src/main/resources/mybatis-generator-config.xml
              </configurationFile>
              <overwrite>true</overwrite>
              <verbose>true</verbose>
          </configuration>
          <executions>
              <execution>
                  <id>generate</id>
                  <goals>
                      <goal>generate</goal>
                  </goals>
              </execution>
          </executions>
          <dependencies>
              <!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-core -->
              <dependency>
                  <groupId>org.mybatis.generator</groupId>
                  <artifactId>mybatis-generator-core</artifactId>
                  <version>1.4.0</version>
              </dependency>
          </dependencies>
      </plugin>
   </plugins>
   ```
3. pom.xml 설정한 `MyBatis Generator` 플러그인의 설정파일 경로에 Generator Config XML 파일 생성
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE generatorConfiguration
           PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
           "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
   <generatorConfiguration>
       <!-- Oracle JDBC 드라이버 위치 설정 -->
       <classPathEntry location="/Users/yoman/.m2/repository/com/oracle/ojdbc6/11.2.0.3/ojdbc6-11.2.0.3.jar"/>
   
       <context id="UdemyTables" targetRuntime="MyBatis3">
           <!-- Oracle 접속 정보 설정 -->
           <jdbcConnection driverClass="oracle.jdbc.OracleDriver"
                           connectionURL="jdbc:oracle:thin:@localhost:1521:xe"
                           userId="udemy"
                           password="udemy"/>
   
           <!--
           <javaModelGenerator/>
           targetPackage 속성 : DTO가 위치할 패키지 경로
           targetProject 속성 : 프로젝트명 또는 프로젝트 소스 경로
           -->
           <javaModelGenerator targetPackage="com.udemy.spring.practical.mbgmember.vo"
                               targetProject="src/main/java"/>
   
           <!--
           <sqlMapGenerator/>
           targetPackage 속성 : SQL MAPPER(.xml) 파일이 위치할 패키지 경로
           targetProject 속성 : 프로젝트명 또는 프로젝트 리소스 경로
           -->
           <sqlMapGenerator targetPackage="mapper.mbgmember"
                            targetProject="src/main/resources"/>
   
           <!--
           <javaClientGenerator/>
           targetPackage 속성 : DAO가 위치할 패키지 경로
           targetProject 속성 : 프로젝트명 또는 프로젝트 소스 경로
           type 속성 : 형식
           -->
           <javaClientGenerator targetPackage="com.udemy.spring.practical.mbgmember.repository"
                                targetProject="src/main/java"
                                type="XMLMAPPER"/>
   
           <!--
            <table/>
            schema 속성 : DB 계정 이름
            tableName : 테이블명
            -->
           <table schema="udemy" tableName="MEMBER"/>
       </context>
   </generatorConfiguration>
   ```

---

## JPA(Java Persistence API) Validation 사용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.newmember

1. pom.xml 설정 파일에 `JBoss` repository 설정
   ```xml
    <repositories>
        <!-- Hibernate Repository(JBoss(미들뒈어 역할을 함)에 포함되어 있음) -->
        <repository>
            <id>org.jboss.repository.releases</id>
            <name>JBoss Maven Release Repository</name>
            <url>https://repository.jboss.org/nexus/content/repositories/releases</url>
        </repository>
    </repositories>
   ```
2. pom.xml 설정 파일에 `Hibernate`, `javax.validation` 의존 설정
    - JPA의 표준 문법으로 Hibernate를 사용하고 있으므로 Hibernate 의존 설정을 통해 Validation 하게 된다.
      ```xml
      <dependencies>
         <dependency>
             <groupId>org.hibernate</groupId>
             <artifactId>hibernate-entitymanager</artifactId>
             <version>5.4.28.Final</version>
         </dependency>
         <dependency>
             <groupId>javax.validation</groupId>
             <artifactId>validation-api</artifactId>
             <version>2.0.1.Final</version>
         </dependency>
      </dependencies>
      ```
3. `Entity` 클래스를 생성하고, 프로퍼티에 `javax.validation`에서 제공하는 어노테이션을 사용하여 바인딩 되는 값에 대해 유효성 검증을 처리하도록 설정
   ```java
   @Entity
   public class NewMember {
   
       @Id
       @Size(min = 6, max = 15, message = "아이디는 6 ~ 15자리 이내로 입력해 주세요.")
       private String id;
       @Size(min = 8, max = 16, message = "비밀번호는 8 ~ 16자리 이내로 입력해 주세요.")
       private String pwd;
       @Size(min = 2, max = 5, message = "이름은 2 ~ 5자리 이내로 입력해 주세요.")
       private String name;
       @Size(min = 12, max = 13, message = "연락처는 12 ~ 13자리 이내로 입력해 주세요.")
       private String tel;
       @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "이메일은 형식에 맞게 입력해 주세요.")
       private String email;
   
       // Getter, Setter ...
   }
   ```
4. Controller 핸들러 메소드의 파라미터인 커맨드 객체에 `@Valid(javax.validation.Valid)` 어노테이션을 붙이고, 유효성 검증에 대한 에러 정보를 담기
   위한 `BindingResult` 객체를 선언한다.
    - 에러 정보를 VIEW에서 출력하는 방법은 스프링 form 태그의 `<form:errors path="커맨드 객체 프로퍼티"/>`를 사용하면 된다.
   ```java
   @Controller
   @RequestMapping("/newmember")
   public class NewMemberController {
   
       @Autowired
       private NewMemberSerivce newMemberSerivce;
   
       public String insertMember(@Valid NewMember newMember, BindingResult result) {
           if (result.hasErrors()) {
               System.out.println("회원가입 처리중 오류가 발생하였습니다.");
               return "newmember/join";
           }
   
           newMemberSerivce.insertMember(newMember);
   
           return "redirect:/newmember/list";
       }
   }
   ```

---

## 스프링에서 Session 사용하기

#### 프로젝트 패키지 : com.udemy.spring.practical.session

1. 스프링에서 세션을 사용하는 방법에는 크게 두 가지가 있다.
    - `HttpSession` 인터페이스를 이용하는 방법
      ```java
      @Controller
      @RequestMapping("/http")
      public class HttpSessionController {
      
          @RequestMapping(value = "/session", method = RequestMethod.GET)
          public String httpSession(HttpSession session, Model model) {
              // 세션 객체에 "admin"이라는 값을 "id"라는 key로 저장한다.
              session.setAttribute("id", "admin");
      
              // 모델 객체에 현재 클래스 이름을 "className"이라는 key로 저장한다.
              model.addAttribute("className", this.getClass());
      
              return "session/httpSession";
          }
      }
      ```
    - `@SessionAttributes` 어노테이션을 이용하는 방법
      ```java
      @Controller
      @RequestMapping("/spring")
      @SessionAttributes({"id", "name"}) // 모델 객체에 "id", "name"라는 key에 값이 설정될 때 세션에 저장하게 된다.(여러 개 설정 가능)
      public class SpringSessionController {
      
          @RequestMapping(value = "/session", method = RequestMethod.GET)
          public String springSession(Model model) {
              // 모델 객체에 "admin"이라는 값을 "id"라는 key로 저장한다.
              model.addAttribute("id", "admin");
      
              // 모델 객체에 "관리자"이라는 값을 "name"라는 key로 저장한다.
              model.addAttribute("name", "관리자");
      
              // 모델 객체에 현재 클래스 이름을 "className"이라는 key로 저장한다.
              model.addAttribute("className", this.getClass());
      
              return "session/springSession";
          }
      }
      ```
2. 세션을 사용할 때의 장점
    - 회원가입 또는 회원정보를 수정할 때 사용하는 객체에 대한 내용을 세션에 저장하여 보호하면서 사용할 수 있다.
        1) `@SessionAttributes("커맨드 객체 별칭")`을 사용한다.
           ```java 
           @Controller @RequestMapping("/newmember")
           @SessionAttributes("newMember")
           public class NewMemberController { 
               // code ...
           }
           ```

---

## 스프링 시큐리티(Security)

#### 프로젝트 패키지 : com.udemy.spring.practical.security

1. 스프링 시큐리티란 `ACEGI` 보안으로부터 시작된 프로젝트이다.
    - ACEGI는 강력한 보안 프레임워크의 하나이지만 많은 양의 XML 설정 코드가 필요하다라는 단점을 가지고 있다.
    - ACEGI는 스프링 2.0부터 스프링 시큐리티로 이름이 변경되었다.
        1) 보안 설정에 필요한 수백 줄의 코드를 간소화 시켰음.
    - 스프링 3.0에서 시큐리티 보안 설정을 한층 더 간소화 시켰다.
2. 인증(Authentication)
    - 크리덴셜(Credential, 자격) 기반 인증 : 일반적으로 웹에서 사용하는 대부분의 인증 방식은 크리덴셜 기반이다.
        1) 권한을 부여 받기 위해 한 차례의 인증 과정을 거친 후에 `사용자명(Principle, 아아디)`과 `비밀번호(Credential)`를 입력 받는다.
        2) DB에 저장된 비밀번호와 일치하는지 확인한다.
    - 이중 인증(Two-factor Authentication) : 한번에 두 가지 방식으로 인증을 받는 방식이다. 예를 들어 금융, 은행 웹 애플리케이션을 이용해서 온라인 거래를 할 때 로그인, 보안 인증서의
      두 방법으로 인증을 하는 것이다.
    - 물리적 인증 : 가장 효과적인 보안 수단으로 생체인식(지문인식, 홍채인식), 키를 이용하는 방식이다.
3. 인가(Authorization, 권한)
    - 부여된 권한(Granted Authority) : 적절한 절차로 사용자가 인증(Authentication)이 되었다면, 권한을 부여(Granted Authority)해야 한다.
        1) 회원가입 등을 통해서 반영구적인 권한이 부여되었다면, 이렇게 부여된 권한을 어디에 저장해야 한다.
    - 리소스의 권한(Intercept) : 권한이 없는 자들이 원천적으로 리소스에 접근할 수 없도록 막아내는 것이다.
        1) 즉, 적절한 권하을 가진 자만이 해당 리소스에 접근할 수 있도록 외부요청을 원천적으로 가로채는(Intercept) 것이다.

---

## 스프링 시큐리티(Security) 설정

#### 프로젝트 패키지 : com.udemy.spring.practical.security

1. pom.xml 설정 파일에 `Spring Security` 의존 설정 추가
   ```xml
   <dependencies>
        <!-- s:Spring Security -->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
            <version>5.2.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-core</artifactId>
            <version>5.2.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-web</artifactId>
            <version>5.2.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-taglibs</artifactId>
            <version>5.2.6.RELEASE</version>
        </dependency>
        <!-- e:Spring Security -->
    </dependencies>
   ```
2. security-context.xml 설정 파일 생성 후 시큐리티 관련 설정 추가
3. web.xml 설정 파일에 새롭게 생성한 security-context.xml 설정 파일을 읽어들이도록 수정
   ```xml
    <!-- 추가적으로 설정할 환경설정 파일 등록 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            /WEB-INF/spring/root-context.xml
            <!-- 스프링 시큐리티 환경설정 파일 추가 -->
            /WEB-INF/spring/appServlet/security-context.xml
        </param-value>
    </context-param>
   ```
4. web.xml 설정 파일에 `DelegatingFilterProxy` 설정 추가
    - 요청을 가로채서 스프링 시큐리티에서 인증과 인가를 확인하도록 지시할 수 있게 Filter를 등록한다.
        1) 인증과 인가가 모두 확인 되었다면 실제 요청을 처리하게 되는 구조이다.
    - 이때 `filter-name`은 `springSecurityFilterChain`으로 등록해야 하는데, DelegatingFilterProxy가 해당 Bean에 위임하여 시큐리티 처리를 하기 때문이다.
       ```xml
       <!-- Spring Security Filter 설정 -->
       <filter>
           <filter-name>springSecurityFilterChain</filter-name>
           <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
       </filter>
       <filter-mapping>
           <filter-name>springSecurityFilterChain</filter-name>
           <url-pattern>/*</url-pattern>
       </filter-mapping>
       ```

---

## 스프링 시큐리티 커스텀 로그인 설정하기

#### 프로젝트 패키지 : com.udemy.spring.practical.security

> - 스프링 시큐리티 3.x 버전 이상 부터는 설정에 대한 많은 변화가 있었다.
> - 따라서 스프링 시큐리티 4.x 버전 이상을 사용하는 경우 [Spring Security 3.x에서 4.x로 마이그레이션 하는 방법(XML 구성)](https://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-xml.html#m3to4-xmlnamespace-form-login) 을 참고하면서 설정하도록 한다.

1. 스프링 시큐리티에서 기본적으로 제공하는 로그인 양식을 사용하지 않고, 개발자가 직접 지정한 로그인 양식을 사용하도록 설정할 수 있다.
    - security-context.xml 설정 파일에서 `<form-login/>` 태그 설정
       ```xml
       <security:http auto-config="true" use-expressions="false">
         <!--
         <security:form-login/> 태그는 시큐리티 커스텀 로그인을 설정하는 태그이다.
         login-page 속성 : 커스텀 로그인 URL을 설정한다.(기본값 : /login)
         login-processing-url 속성 : 로그인을 처리하는 URL을 설정한다.(기본값 : /login)
         default-target-url 속성 : 로그인 성공 후 이동할 URL을 설정한다.(기본값 : /)
         always-use-default-target 속성 : 로그인 성공 후 default-target-url로 갈 것인지를 설정한다.(기본값: FALSE)
         authentication-failure-url 속성 : 로그인에 실패할 경우 처리할 URL을 설정한다.
         authentication-failure-handler-ref 속성 : 로그인 실패시 별도로 처리할 수 있는 클래스를 설정한다.(로그인 실패 횟수, 자동 로그인 방지 등)
         authentication-success-handler-ref 속성 : 로그인 성공시 별도로 처리할 수 있는 클래스를 설정한다.
         username-parameter 속성 : 아이디 파라미터명을 설정한다.(input 태그 name 속성 값과 동일해야 함, 기본값 : username)
         password-parameter 속성 : 비밀번호 파라미터명을 설정한다.(input 태그 name 속성 값과 동일해야 함, 기본값 : password)
         -->
         <security:form-login login-page="/security/login"
                              default-target-url="/security/logon"
                              authentication-failure-url="/security/login?fail"/>
         <!--
         <security:csrf/> 태그는 CSRF(Cross Site Request Forgery, 교차 사이트 요청 위조) Token을 설정한다.
         disabled 속성 : CSRF Token을 사용하지 않을 것인지를 설정한다.(기본값 : FALSE)
         -->
         <security:csrf disabled="true"/>
       </security:http>
       ```
    - `<form-login/>` 태그에 지정된 `login-page` 속성 값(URL)에 매핑되는 핸들러 메소드 작성
      ```java
      @Controller
      public class SecurityCustomLoginController {
      
          @RequestMapping(value = "/security/login", method = RequestMethod.GET)
          public String getSecurityCustomLoginPage() {
              return "security/login";
          }
      }
      ```
    - 핸들러 메소드에서 반환하는 VIEW에서 로그인 양식 구성
        1) 이때 아이디, 비밀번호 `<input/>` 태그의 속성 값은 기본적으로 스프링 시큐리티가 제공하는 `username`과 `password`를 사용하도록 한다.
         ```html
         <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
         <!DOCTYPE html>
         <html lang="ko">
            <head>
                <meta charset="UTF-8"/>
                <title>스프링 시큐리티 커스텀 로그인</title>
            </head>
            <body>
               <div align="center">
                   <h2>로그인</h2>
                   <hr/>
                   <!--
                   스프링 시큐리티에서 제공하는 action 속성 값을 이용한다.
                   스프링 시큐리티 4 버전 이전에는 '/j_spring_security_check'를 사용했으나,
                   그 이후 버전부터는 '/login'을 사용하도록 한다.
                   CSRF Token을 사용할 경우 '<input type="hidden" name="_csrf" value="Token value"/>'를 사용하도록 한다.
                   -->
                   <form action="<c:url value="${pageContext.request.contextPath}/login"/>" method="post">
                       <%--<sec:csrfInput/>--%>
                       <%--<inupt type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                       <table border="1" cellpadding="3" cellspacing="0">
                           <!-- 로그인이 실패 했을 때 출력되는 태그 -->
                           <c:if test="${param.fail ne null}">
                               <tr>
                                   <td colspan="2" align="center" style="color:red;">
                                       로그인에 실패했습니다.
                                   </td>
                               </tr>
                           </c:if>
                           <tr>
                               <td>아이디</td>
                               <td>
                                   <!--
                                   스프링 시큐리티에서 제공하는 name 속성 값을 사용한다.
                                   스프링 시큐리티 4 버전 이전에는 'j_username'를 사용했으나,
                                   그 이후 버전부터는 'username'을 사용하도록 한다.
                                   -->
                                   <input type="text" name="username"/>
                               </td>
                           </tr>
                           <tr>
                               <td>비밀번호</td>
                               <td>
                                   <!--
                                   스프링 시큐리티에서 제공하는 name 속성 값을 사용한다.
                                   스프링 시큐리티 4 버전 이전에는 'j_password'를 사용했으나,
                                   그 이후 버전부터는 'password'을 사용하도록 한다.
                                   -->
                                   <input type="password" name="password"/>
                               </td>
                           </tr>
                           <tr>
                               <td colspan="2" align="center">
                                   <input type="submit" value="로그인"/>
                               </td>
                           </tr>
                       </table>
                   </form>
               </div>
            </body>
         </html>
         ```
2. 커스텀 로그인 장점
    - 스프링 시큐리티에서 기본으로 제공하는 로그인, 로그아웃 양식을 사용하지 않고, 커스텀 로그인, 로그아웃 양식을 사용할 수 있다.
    - 로그인 성공 또는 실패에 따른 별도 처리 작업이 가능하다.
    - `CSRF`(Cross Site Request Forgery, 교차 사이트 요청 위조) Token을 사용하여 보안 취약점을 해소할 수 있다.

