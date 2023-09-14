package fun.mjauto.crm.unit;

import fun.mjauto.crm.model.vo.ConditionReqDto;
import fun.mjauto.crm.model.vo.PageReqDto;
import fun.mjauto.crm.model.vo.RankReqDto;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.service.CustomerService;

public class ServiceTest {

    private final CustomerService customerService;
    public ServiceTest(CustomerService customerService) {
        this.customerService = customerService;
        test_paging();
    }

    void test_paging(){
        SelectDto selectDto = new SelectDto();
        ConditionReqDto conditionReqDto = new ConditionReqDto();
        conditionReqDto.setCondition("Between");
        conditionReqDto.setValue(500);
        conditionReqDto.setSecondValue(600);
        conditionReqDto.setProperty("id");
        selectDto.add(conditionReqDto);
        PageReqDto pageReqDto = new PageReqDto();
        pageReqDto.setPageNum(1);
        pageReqDto.setPageSize(10);
        pageReqDto.setFetchAll(true);
        selectDto.setPageReqDto(pageReqDto);
        RankReqDto rankReqDto = new RankReqDto();
        rankReqDto.setRankKey("id");
        rankReqDto.setReverse(true);
        rankReqDto.setRankAll(true);
        selectDto.setRankReqDto(rankReqDto);
        System.out.println(customerService.selectCustomers(selectDto));
    }
}
