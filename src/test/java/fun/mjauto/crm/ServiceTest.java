package fun.mjauto.crm;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.dto.ConditionReqDto;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.service.CustomerService;

import java.util.List;

public class ServiceTest {
    void test_paging(CustomerService customerService){
        SelectDto selectDto = new SelectDto();

        // 设置限制条件（要求id小于等于540）
        ConditionReqDto conditionReqDto = new ConditionReqDto();
        conditionReqDto.setCondition("LessThanOrEqualTo");
        conditionReqDto.setValue(540);
        conditionReqDto.setProperty("id");
        selectDto.add(conditionReqDto);

        // 设置排序规则（根据id逆序查询）
        RankReqDto rank = new RankReqDto();
        rank.setRankAll(true);
        rank.setRankKey("id");
        rank.setReverse(true);
        selectDto.setRankReqDto(rank);

        // 设置偏移量和行数大小（第2页，每页10行）
        PageReqDto page = new PageReqDto();
        page.setFetchAll(true);
        page.setPageSize(10);
        page.setPageNum(2);
        selectDto.setPageReqDto(page);

        List<Customer> customers = customerService.selectRankCustomers(selectDto);
        System.out.println(customers);
    }
}
