
[demo,详见github上的程序](https://github.com/BenniaoManfei/sharding-sphere-demo/tree/master/sharding-jdbc-demo/sharding-demo-01)

#### 1. 新建一个springboot项目 ####

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
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>
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
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

```

---



#### 2. 数据库准备 ####



1. 在主库新建数据库`demo_ds_0`,`demo_ds_1`，按照如下脚本生成`t_order_0`,`t_order_1`,`t_order_item_0`,`t_order_item_1`

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

注意保持两个库的数据结构一致


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

# 这里配置的策略大概如下:1. 默认数据源为ds_0,如果没有配置相应的分库分表策略，则默认不使用，直接使用默认数据源完成操作

2. 默认按照user_id将数据分到ds_0,ds_1两个库中，

3. order_id,t_order_item的数据按照order_id % 2的规则拆分到不同的表中


sharding:
  jdbc:
    datasource:
      names: ds_0,ds_1
      ds_0:
        type: org.apache.commons.dbcp.BasicDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://192.168.134.128:3306/demo_ds_0
        username: root
        password: 123456
      ds_1:
        type: org.apache.commons.dbcp.BasicDataSource
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://192.168.134.128:3306/demo_ds_1
        username: root
        password: 123456
    config:
      sharding:
        # 默认指针列值生成器类类名称，缺省使用io.shardingsphere.core.keygen.DefaultKeyGenerator
        # 如果自定义实现io.shardingsphere.core.keygen.KeyGenerator接口并提供无参构造方法
        default-key-generator-class-name: com.daniel.sharding.sphere.shardingdemo01.util.keygenerator.MyKeyGenerator
        props:
          sql:
            show: true # 显示sql
          #executor:
            #size: 4 # 工作线程数量，默认是CPU核数
        # 未配置分片规则的表将通过默认的数据源定位
        default-data-source-name: ds_0
        # 数据库分片策略
        default-database-strategy:
          inline:
            sharding-column: user_id
            algorithm-expression: ds_$->{user_id % 2}
        # 默认的分表策略
        #default-table-strategy:
        tables:
          t_order_item:
            actual-data-nodes: ds_$->{0..1}.t_order_item_$->{0..1}
            table-strategy:
              inline:
                sharding-column: order_id
                algorithm-expression: t_order_item_$->{order_id % 2}
            key-generator-column-name: order_item_id
            key-generator-class-name: com.daniel.sharding.sphere.shardingdemo01.util.keygenerator.MyKeyGenerator
          t_order:
            actual-data-nodes: ds_$->{0..1}.t_order_$->{0..1}
            table-strategy:
              inline:
                sharding-column: order_id
                algorithm-expression: t_order_$->{order_id % 2}
            key-generator-column-name: order_id
            key-generator-class-name: com.daniel.sharding.sphere.shardingdemo01.util.keygenerator.MyKeyGenerator
server:
  port: 9011

```


#### 5. 主键策略 ####

默认的主键策略为`io.shardingsphere.core.keygen.DefaultKeyGenerator`,使用了twitter的`snowflake`算法，但是该算法最后4位是在统一毫秒内的访问递增值，因此如果毫秒内并发度并不高，最后4为为零的概率很大。换句话说，并发度不高的应用生成偶数主键的几率会更高

下面做了一个调整，将4为序列号改成了4为随机数

```java

@Slf4j
public final class MyKeyGenerator implements KeyGenerator {

    public MyKeyGenerator () {
        super();
    }

    public static final  Random RANDOM = new Random();

    public static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    @Setter
    private static TimeService timeService = new TimeService();

    private static long workerId;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    private long sequence;

    private long lastTime;

    /**
     * Set work process id.
     *
     * @param workerId work process id
     */
    public static void setWorkerId(final long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
        MyKeyGenerator.workerId = workerId;
    }

    /**
     * Generate key.
     *
     * @return key type is @{@link Long}.
     */
    @Override
    public synchronized Number generateKey() {
        long currentMillis = timeService.getCurrentMillis();
        Preconditions.checkState(lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis);

        sequence = RANDOM.nextInt(4000);
        lastTime = currentMillis;
        if (log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTime)), workerId, sequence);
        }
        return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private long waitUntilNextTime(final long lastTime) {
        long time = timeService.getCurrentMillis();
        while (time <= lastTime) {
            time = timeService.getCurrentMillis();
        }
        return time;
    }
}

```

---



#### 6. 测试 #### 

这里是基于springboot的测试。判断是否生效，我们可以更改dataSoucre中的用户，例如使用只读用户完成读，等

```java

@Slf4j
public class OrderRepositoryTest extends ShardingDemo01ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Test
//    @Transactional
    public void insert() {
        DefaultKeyGenerator generator = new DefaultKeyGenerator();
        for (long i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            long orderId = orderRepository.save(order).getOrderId();

            System.err.println(Long.toBinaryString(orderId));
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);


            orderItemRepository.save(item);
        }
    }

    @Test
    public void findAll() {
        List<Order> orders = orderRepository.findAll();

        log.error("findAll(logged by DaiZM):<><>【{}】",orders);
    }

}

```


```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingDemo01ApplicationTests {

	@Test
	public void contextLoads() {
	}

}

```

