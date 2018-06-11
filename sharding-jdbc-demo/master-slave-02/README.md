
[demo,详见github上的程序](https://github.com/BenniaoManfei/sharding-sphere-demo/tree/master/sharding-jdbc-demo/master-slave-01)

#### 1. 新建一个springboot项目 ####

1. 包含jpa，jdbc以及mysql

2. 添加数据源以及sharding-jdbc的依赖支持

```xml

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.10.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<!-- 数据源jar包 -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>

		<!-- sharding-jdbc的配置 -->
		<dependency>
			<groupId>io.shardingsphere</groupId>
			<artifactId>sharding-jdbc-spring-boot-starter</artifactId>
			<version>3.0.0.M1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

```

jdbc2.x版本没有尝试

---

#### 2. 数据库准备 ####

1. 主库:192.168.134.128:3306,从库192.168.134.128:3316，主从已经配置好。两个数据库是基于docker构建的两个mysql容器

2. 在主库新建数据库master-slave，执行脚本

```sql

DROP TABLE IF EXISTS t_order;
CREATE TABLE `t_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS t_order_item;
CREATE TABLE `t_order_item` (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



```
这里会自动同步从库中，不需要在从库中执行这步操作


---


#### 3. entity以及repository ####

```java
@Entity
@Table(name = "t_order")
@ToString
@Data
public class Order implements Serializable {
    
    private static final long serialVersionUID = 661434701950670670L;
    
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "status")
    private String status;
    
}

```


```java

import com.daniel.sharding.sphere.masterslave01.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

```

#### 4. 配置 ####

如果有多个从库，可以继续添加

```yml

sharding:
  jdbc:
    datasource:
      names: ds_master,ds_slave_0
      ds_master:
        type: org.apache.commons.dbcp.BasicDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://192.168.134.128:3306/master-slave
        username: root
        password: 123456
      ds_slave_0:
        type: org.apache.commons.dbcp.BasicDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://192.168.134.128:3316/master-slave
        username: slave_reader
        password: 123456
    config:
      masterslave: # 这里主从用了这个配置，如果是分片‘sharding.jdbc.config.sharding’
        name: ds_ms
        # 主库数据源名称
        master-data-source-name: ds_master
        # 从库数据源名称列表
        slave-data-source-names: ds_slave_0
        #从库负载均衡算法类名称。该类需实现MasterSlaveLoadBalanceAlgorithm接口且提供无参数构造器
        #load-balance-algorithm-class-name:
        #从库负载均衡算法类型，可选ROUND_ROBIN,RANDOM,如果load-balance-algorithm-class-name存在，这个将被忽略
        load-balance-algorithm-type: ROUND_ROBIN

```


#### 5. 测试 #### 

这里是基于springboot的测试。判断是否生效，我们可以更改dataSoucre中的用户，例如使用只读用户完成读，等

```java

@Slf4j
public class OrderRepositoryTest extends MasterSlave01ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;


    @Test
    @Transactional
    public void insert() {
        Order order = new Order();
        Random random = new Random();
        order.setOrderId(1003L+random.nextLong());
        order.setUserId(2002);
        order.setStatus(RandomStringUtils.randomNumeric(4));
//        orderRepository.saveAndFlush(order);
        orderRepository.save(order);
        System.err.println(order);
    }

    @Test
    public void findAll() {

        List<Order> order = orderRepository.findAll();
        System.err.println(order);
    }

}

```


```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterSlave01ApplicationTests {

	@Test
	public void contextLoads() {
	}

}

```

> JPA整合sharding-jdbc和原来的单数据源并无二致，只是原来的但数据源配置改成了sharding-jdbc的多数据配置罢了。