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
        System.out.println(Arrays.asList(511, 512, 299));
        System.out.println(Arrays.toString(new int[]{546, 547, 548}));
        new DaoTest(customerMapper);
    }

    CustomerReqDto getSelectDto() {
        CustomerReqDto selectDto = new CustomerReqDto();
        ConditionReqDto conditionReqDto = new ConditionReqDto();
        conditionReqDto.setCondition("Between");
        conditionReqDto.setValue(1);
        conditionReqDto.setSecondValue(10);
        conditionReqDto.setProperty("id");
        selectDto.add(conditionReqDto);
        return selectDto;
    }

    @Test
    void test_selectCount() throws IOException {
        // 统计总行数
        CustomerReqDto selectDto = getSelectDto();
        System.out.println(new HttpRequest("http://localhost:9527/customer/selectCount").postJson(selectDto));
    }

    @Test
    void test_deleteRows() throws IOException {
        // 删除符合条件的行
        CustomerReqDto selectDto = getSelectDto();
        ConditionReqDto conditionReqDto = new ConditionReqDto();
        conditionReqDto.setCondition("In");
        conditionReqDto.setValue(new int[]{546, 547, 548});
        conditionReqDto.setProperty("id");
        selectDto.add(conditionReqDto);
        System.out.println(new HttpRequest("http://localhost:9527/customer/deleteRows").postJson(selectDto));
    }

    @Test
    void test_deleteById() throws IOException {
        // 根据主键删除行
        System.out.println(new HttpRequest("http://localhost:9527/customer/deleteById")
                .postForm("id=547"));
    }

    @Test
    void test_insert() throws IOException {
        // 插入一条数据
        Customer row = new Customer(null,
                "王富贵",
                "张三",
                123456,
                "法外狂徒",
                "男",
                9528,
                null,
                1,
                1,
                "这是一个帅哥");
        System.out.println(new HttpRequest("http://localhost:9527/customer/insert").postJson(row));
    }

    @Test
    void test_insertSelective() throws IOException {
        // 插入一条数据，只插入非空字段
        Customer row = new Customer(null,
                "王富贵",
                "张三",
                123456,
                null,
                null,
                9528,
                null,
                1,
                1,
                null);
        System.out.println(new HttpRequest("http://localhost:9527/customer/insertSelective").postJson(row));
    }

    @Test
    void test_select() throws IOException {
        // 查询符合条件的行，进行排序和分页后返回
        CustomerReqDto selectDto = getSelectDto();
        System.out.println(new HttpRequest("http://localhost:9527/customer/select").postJson(selectDto));
    }

    @Test
    void test_selectById() throws IOException {
        // 根据主键查询行
        System.out.println(new HttpRequest("http://localhost:9527/customer/selectById")
                .postForm("id=1"));
    }

    @Test
    void test_updateRowsSelective() throws IOException {
        // 更新符合条件的行，只更新非空字段
        CustomerReqDto selectDto = getSelectDto();
        Customer row = new Customer();
        row.setCustomerName("王富贵");
        selectDto.setRow(row);
        System.out.println(new HttpRequest("http://localhost:9527/customer/updateRowsSelective").postJson(selectDto));
    }

    @Test
    void test_updateRows() throws IOException {
        // 更新符合条件的行
        CustomerReqDto selectDto = getSelectDto();
        Customer row = new Customer(null,
                "王富贵",
                "张三",
                123456,
                null,
                null,
                9528,
                null,
                1,
                1,
                null);
        selectDto.setRow(row);
        System.out.println(new HttpRequest("http://localhost:9527/customer/updateRows").postJson(selectDto));
    }

    @Test
    void test_updateByIdSelective() throws IOException {
        // 根据主键更新一条数据，只插入非空字段
        Customer row = new Customer(1,
                "王富贵",
                "张三",
                null,
                null,
                "男",
                9528,
                null,
                1,
                1,
                null);
        System.out.println(new HttpRequest("http://localhost:9527/customer/updateByIdSelective").postJson(row));
    }

    @Test
    void test_updateById() throws IOException {
        // 根据主键更新一条数据
        Customer row = new Customer(10,
                "王富贵",
                "张三",
                123456,
                null,
                "女",
                9528,
                null,
                1,
                1,
                null);
        System.out.println(new HttpRequest("http://localhost:9527/customer/updateById").postJson(row));
    }
}
