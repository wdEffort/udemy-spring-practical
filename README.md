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

1. 주요 기능 : 기존 방식과 같이 데이터베이스 Connection을 구하고, try-catch-finally로 자원을 관리하는 등의 중복된 코드를 제거할 수 있다.
2. JDBC를 위한 세 개의 템플릿 클래스
    - JdbcTemplate : 스프링의 가장 기본적인 템플릿으로 `색인된 파라미터(Indexed Parameter)` 기반의 쿼리를 통해서 데이터베이스에 액세스하는 기능을 제공한다.
    - NamedParameterJdbcTemplate : SQL과 값들을 색인된 파라미터 대신에 `명련된 파라미터(Named Parameter)`로 바인딩하여 쿼리를 수행할 수 있게 해준다.
    - SimpleJdbcTemplate : Java 5부터 지원하는 기능 중에 오토 박싱, 제네릭스, 가변 파라미터 목록 등을 활용해서 쉽게 템플릿을 사용할 수 있도록 하는 기능을 제공한다.(스프링 3.1에서
      제거 되었다.)
3. 단, Java 1.4 이하의 버전에서는 사용할 수 없다.

---

## JdbcTemplate 사용 방법

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
