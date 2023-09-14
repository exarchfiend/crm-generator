package fun.mjauto.crm.steps;

import fun.mjauto.crm.model.dto.ConditionReqDto;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.utils.HttpRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class CustomerSteps {

    public SelectDto selectDto = new SelectDto();
    public String res;

    @Given("管理员已经登录")
    public void 管理员已经登录() {
        // 登录验证
    }

    @When("符合这些{string}条件")
    public void setConditions(String conditions) {
        if(!conditions.isEmpty()){
            ConditionReqDto conditionReqDto = new ConditionReqDto();
            conditionReqDto.setCondition("Between");
            conditionReqDto.setValue(0);
            conditionReqDto.setSecondValue(600);
            conditionReqDto.setProperty("id");
            this.selectDto.add(conditionReqDto);
        }
    }

    @And("每页{string}条,第{string}页")
    public void setPage(String size, String page) {
        PageReqDto pageReqDto = new PageReqDto();
        pageReqDto.setPageNum(Integer.parseInt(page));
        pageReqDto.setPageSize(Integer.parseInt(size));
        pageReqDto.setFetchAll((!size.isEmpty() && !page.isEmpty()));
        this.selectDto.setPageReqDto(pageReqDto);
    }

    @And("按照{string}顺序{string}排序")
    public void setRank(String rankKey, String isReverse) {
        RankReqDto rankReqDto = new RankReqDto();
        rankReqDto.setRankKey(rankKey);
        rankReqDto.setReverse(Boolean.parseBoolean(isReverse));
        rankReqDto.setRankAll((!rankKey.isEmpty() && !isReverse.isEmpty()));
        this.selectDto.setRankReqDto(rankReqDto);
    }

    @And("点击查询")
    public void select() throws IOException {
        this.res = new HttpRequest().post("http://localhost:9527/customer/select",this.selectDto);
    }

    @Then("将该页数据返回给页面")
    public void returnCustomers() throws IOException {
        System.out.println(res);
    }
}
