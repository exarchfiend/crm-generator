package fun.mjauto.crm;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.Restrictions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmGeneratorApplicationTests {

    @Autowired
    private CustomerMapper customerMapper;

    DaoTest daoTest = new DaoTest();

    @Test
    void contextLoads() {
        daoTest.test_paging(customerMapper);
    }

    @Test
    void test_countByExample() {
        // 统计总行数
        Restrictions restrictions = new Restrictions();
        long count = customerMapper.countByExample(restrictions);
        System.out.println(count);

        // 统计id小于300的行数
        Restrictions.Criteria criteria = restrictions.createCriteria();
        criteria = criteria.singleValue("LessThan",300,"id","id");
        restrictions.or(criteria);
        count = customerMapper.countByExample(restrictions);
        System.out.println(count);

        // 逆序查询id在500与510之间的行
        restrictions.clear();
        Restrictions.Criteria criteria1 = restrictions.or();
        criteria1.betweenValue("Between",500,510,"id","id");
        restrictions.setOrderByClause("id DESC");
        List<Customer> customers = customerMapper.selectByExample(restrictions);
        System.out.println(customers);

        restrictions.setOrderByClause("id ASC");
        customers = customerMapper.selectByExample(restrictions);
        System.out.println(customers);
    }

}
