package fun.mjauto.crm.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.vo.ConditionReqDto;
import fun.mjauto.crm.model.vo.PageReqDto;
import fun.mjauto.crm.model.vo.RankReqDto;
import fun.mjauto.crm.utils.HttpRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerSteps {

    public CustomerReqDto data = new CustomerReqDto();
    public Customer row;
    public String res;

    @Given("管理员已经登录")
    public void 管理员已经登录() {
        // 登录验证
    }

    @When("字段:{string},条件:{string},值一:{string},值二:{string}")
    public void setConditions(String property, String condition, String value1, String value2) {
        if (!condition.isEmpty()) {
            ConditionReqDto conditionReqDto = new ConditionReqDto();
            conditionReqDto.setCondition(condition);
            if (condition.equals("In") || condition.equals("NotIn")) {
                // 转换为整数数组
                Object[] intArray = Arrays.stream(value1.split(","))
                        .map(String::trim)
                        .toArray();
                conditionReqDto.setValue(intArray);
            } else {
                conditionReqDto.setValue(value1);
            }
            conditionReqDto.setSecondValue(value2);
            conditionReqDto.setProperty(property);
            this.data.add(conditionReqDto);
        }
    }

    @And("每页:{string}条,第{string}页")
    public void setPage(String size, String page) {
        PageReqDto pageReqDto = new PageReqDto();
        pageReqDto.setPageNum(Integer.parseInt(page));
        pageReqDto.setPageSize(Integer.parseInt(size));
        pageReqDto.setFetchAll((!size.isEmpty() && !page.isEmpty()));
        this.data.setPageReqDto(pageReqDto);
    }

    @And("按照{string}排序,是否逆序:{string}")
    public void setRank(String rankKey, String isReverse) {
        RankReqDto rankReqDto = new RankReqDto();
        rankReqDto.setRankKey(rankKey);
        rankReqDto.setReverse(Boolean.parseBoolean(isReverse));
        rankReqDto.setRankAll((!rankKey.isEmpty() && !isReverse.isEmpty()));
        this.data.setRankReqDto(rankReqDto);
    }

    @When("插入数据:{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void setCustomer(String arg1, String arg2, String arg3, String arg4, String arg5,
                            String arg6, String arg7, String arg8, String arg9, String arg10) {
        this.row = new Customer(null, arg1, arg2, Integer.parseInt(arg3),
                arg4, arg5, Integer.parseInt(arg6), arg7, Integer.parseInt(arg8), Integer.parseInt(arg9), arg10);
    }

    @Then("返回该页数据,共{string}条")
    public void returnCustomers(String res) throws JsonProcessingException {
        List<Customer> customers = new ObjectMapper().readValue(this.res, new TypeReference<List<Customer>>() {
        });
        for (Customer row : customers) {
            System.out.println(row);
        }
        assertEquals(Integer.parseInt(res), customers.size());
    }

    @Then("返回一条数据,ID为{string}")
    public void 返回一条数据共条(String res) throws JsonProcessingException {
        Customer customer = new ObjectMapper().readValue(this.res, new TypeReference<Customer>() {
        });
        System.out.println(customer);
        assertEquals(Integer.parseInt(res), customer.getId());
    }

    @Then("返回行数,共{string}条")
    public void returnDeleteRows(String res) {
        assertEquals(res, this.res);
    }

    @And("点击获取客户信息")
    public void select() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/select").postJson(this.data);
    }

    @And("点击获取客户人数")
    public void selectCount() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/selectCount").postJson(this.data);
    }

    @And("点击删除客户信息")
    public void deleteRows() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/deleteRows").postJson(this.data);
    }

    @When("点击通过主键值:{string}删除客户信息")
    public void deleteById(String id) throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/deleteById").postForm("id=" + id);
    }

    @And("点击插入数据")
    public void insert() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/insert").postJson(this.row);
    }

    @And("点击插入部分数据")
    public void insertSelective() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/insertSelective").postJson(this.row);
    }

    @When("点击通过主键值:{string}查询客户信息")
    public void selectById(String id) throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/selectById").postForm("id=" + id);
    }

    @And("更新部分客户信息")
    public void updateRowsSelective() throws IOException {
        this.data.setRow(this.row);
        this.res = new HttpRequest("http://localhost:9527/customer/updateRowsSelective").postJson(this.data);
    }

    @And("更新客户信息")
    public void updateRows() throws IOException {
        this.data.setRow(this.row);
        this.res = new HttpRequest("http://localhost:9527/customer/updateRows").postJson(this.data);
    }

    @And("根据主键值{string}更新部分客户信息")
    public void updateByIdSelective(String id) throws IOException {
        this.row.setId(Integer.parseInt(id));
        this.res = new HttpRequest("http://localhost:9527/customer/updateByIdSelective").postJson(this.row);
    }

    @And("根据主键值{string}更新客户信息")
    public void updateById(String id) throws IOException {
        this.row.setId(Integer.parseInt(id));
        this.res = new HttpRequest("http://localhost:9527/customer/updateById").postJson(this.row);
    }
}
