package fun.mjauto.crm.model.dto;

import lombok.Data;

@Data
public class ConditionReqDto {
    /**
     * 表示查询条件的字符串
     */
    private String conditionCode = null;
    /**
     * 表示查询条件的值
     */
    private Object value = null;
    /**
     * 用于表示某些条件下的第二个值，例如范围查询
     */
    private Object secondValue = null;
    /**
     * 表示实体类的键
     */
    private String property = null;
}
