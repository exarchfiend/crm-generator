package fun.mjauto.crm;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.vo.ConditionReqDto;
import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.unit.DaoTest;
import fun.mjauto.crm.utils.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class CrmGeneratorApplicationTests {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void contextLoads() {
        new DaoTest(customerMapper);
    }
}
