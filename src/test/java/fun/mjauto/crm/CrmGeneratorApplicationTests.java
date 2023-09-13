package fun.mjauto.crm;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.Restrictions;
import fun.mjauto.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrmGeneratorApplicationTests {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
//        new DaoTest(customerMapper);
        new ServiceTest(customerService);
    }

    @Test
    void test_countByExample() {
        // 统计总行数
        Restrictions restrictions = new Restrictions();
        long count = customerMapper.countByExample(restrictions);
        System.out.println(count);
    }

}
