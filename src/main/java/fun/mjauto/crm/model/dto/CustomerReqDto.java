package fun.mjauto.crm.model.dto;

import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.vo.ConditionReqDto;
import fun.mjauto.crm.model.vo.PageReqDto;
import fun.mjauto.crm.model.vo.RankReqDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerReqDto {
    PageReqDto pageReqDto;
    RankReqDto rankReqDto;
    List<ConditionReqDto> conditionList;
    Customer row;
    boolean isSelect = true;

    public CustomerReqDto() {
        this.conditionList = new ArrayList<>();
        this.pageReqDto = new PageReqDto();
        this.rankReqDto = new RankReqDto();
        this.row = new Customer();
    }

    public void add(ConditionReqDto conditionReqDto) {
        conditionList.add(conditionReqDto);
    }
}
