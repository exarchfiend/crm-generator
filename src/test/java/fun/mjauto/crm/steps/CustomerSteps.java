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

    public CustomerReqDto selectDto = new CustomerReqDto();
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
            if(condition.equals("In") || condition.equals("NotIn")){
                // 转换为整数数组
                int[] intArray = Arrays.stream(value1.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                conditionReqDto.setValue(intArray);
            }else {
                conditionReqDto.setValue(value1);
            }
            conditionReqDto.setSecondValue(value2);
            conditionReqDto.setProperty(property);
            this.selectDto.add(conditionReqDto);
        }
    }

    @And("每页:{string}条,第{string}页")
    public void setPage(String size, String page) {
        PageReqDto pageReqDto = new PageReqDto();
        pageReqDto.setPageNum(Integer.parseInt(page));
        pageReqDto.setPageSize(Integer.parseInt(size));
        pageReqDto.setFetchAll((!size.isEmpty() && !page.isEmpty()));
        this.selectDto.setPageReqDto(pageReqDto);
    }

    @And("按照{string}排序,是否逆序:{string}")
    public void setRank(String rankKey, String isReverse) {
        RankReqDto rankReqDto = new RankReqDto();
        rankReqDto.setRankKey(rankKey);
        rankReqDto.setReverse(Boolean.parseBoolean(isReverse));
        rankReqDto.setRankAll((!rankKey.isEmpty() && !isReverse.isEmpty()));
        this.selectDto.setRankReqDto(rankReqDto);
    }

    @And("点击获取客户信息")
    public void select() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/select").postJson(this.selectDto);
    }

    @Then("返回该页数据,共{string}条")
    public void returnCustomers(String res) throws JsonProcessingException {
        List<Customer> customers = new ObjectMapper().readValue(this.res, new TypeReference<List<Customer>>() {});
        for (Customer row : customers){
            System.out.println(row);
        }
        assertEquals(Integer.parseInt(res), customers.size());
    }

    @Then("返回行数,共{string}条")
    public void returnDeleteRows(String res) {
        assertEquals(res, this.res);
    }

    @And("点击获取客户人数")
    public void selectCount() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/selectCount").postJson(this.selectDto);
    }

    @And("点击删除客户信息")
    public void deleteRows() throws IOException {
        this.res = new HttpRequest("http://localhost:9527/customer/deleteRows").postJson(this.selectDto);
    }
}
