import com.iris.mapper.UserDaoImpl;
import com.iris.mapper.UserMapper;
import com.iris.mapper.UserMapperImpl;
import com.iris.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void test1() throws IOException {
        // String resources = "mybatis-config.xml";
        // InputStream in = Resources.getResourceAsStream(resources);
        // SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // List<User> userList = mapper.query();
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserMapperImpl userMapper = context.getBean(UserMapperImpl.class);
        List<User> userList = userMapper.query();
        userMapper.query().forEach(System.out::println);
    }
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);
        userDao.query().forEach(System.out::println);
    }
}
