package fun.mjauto.crm.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectDto {
    PageReqDto pageReqDto;
    RankReqDto rankReqDto;
    List<ConditionReqDto> conditionList;

    public SelectDto() {
        this.conditionList = new ArrayList<>();
    }

    public void add(ConditionReqDto conditionReqDto) {
        conditionList.add(conditionReqDto);
    }
}
