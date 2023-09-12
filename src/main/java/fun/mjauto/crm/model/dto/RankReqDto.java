package fun.mjauto.crm.model.dto;

import lombok.Data;

@Data
public class RankReqDto {
    /**
     * 排序键，默认为主键 id
     */
    private String rankKey = "id";

    /**
     * 是否为逆序，默认为顺序
     */
    private boolean isReverse = false;

    /**
     * 是否排序，默认不排序 为 true 时，rankKey 和 isReverse 无效
     */
    private boolean rankAll = false;
}
