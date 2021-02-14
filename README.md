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

---

## JdbcTemplate에서 사용할 수 있는 메소드

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

1. 트랙잭션을 시작하거나 기존 트랙잭션에 참여하는 방법을 결정하는 것이다.
2. 전파 속성
    - required(상수 값: 0) : (Default) 하나의 트랙잭션에 의해 다른 트랙잭션까지 묶여서 처리된다.
    - supports(상수 값: 1) : 트랜잭션을 필요로 하지 않지만 기존의 트랜잭션이 존재하는 경우 트랜잭선을 사용한다.
    - mandatory(상수 값: 2) : 메소드 실행시 트랜잭션이 필요하다라는 것을 의미한다. 진행중인 트랜잭션이 없을 경우 예외를 발생시킨다.
    - requires_new(상수 값: 3) : 각각 트랜잭션을 처리하는 것을 의미한다.
    - not_supported(상수 값: 4) : 트랜잭션을 필요로 하지 않는다. 기존의 트랜잭션이 존재하면 일시 중지하고 메소드 실행이 끝난 후에 트랜잭션을 계속 진행한다.
    - never(상수 값: 5) : 트랜잭션을 필요로 하지 않는다. 진행중인 트랜잭션이 존재하면 예외를 발생시킨다.