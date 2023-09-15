package fun.mjauto.crm.utils;

import fun.mjauto.crm.model.bo.Restrictions;
import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.vo.ConditionReqDto;
import fun.mjauto.crm.model.vo.PageReqDto;
import fun.mjauto.crm.model.vo.RankReqDto;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class EntityHandler {

    public final Restrictions restrictions;

    public EntityHandler(CustomerReqDto condition) {
        this.restrictions = new Restrictions();
        if (condition.isSelect()) {
            pagingHandler(condition.getPageReqDto());
            rankHandler(condition.getRankReqDto());
        }
    }

    public abstract String getProperty(String property);

    public void conditionsHandler(List<ConditionReqDto> conditions) {
        String condition, key, property = null;
        Object value1, value2 = null;
        for (ConditionReqDto conditionReqDto : conditions) {
            condition = conditionReqDto.getCondition();
            value1 = conditionReqDto.getValue();
            value2 = conditionReqDto.getSecondValue();
            key = getProperty(conditionReqDto.getProperty());
            property = conditionReqDto.getProperty();
            if (condition != null) {
                this.restrictions.or().createCriteria(condition, value1, value2, key, property);
            }
        }
    }

    public void pagingHandler(PageReqDto page) {
        // 设置偏移量和行数大小（第2页，每页10行）
        if (page.isFetchAll()) {
            this.restrictions.setPaging((page.getPageNum() - 1) * page.getPageSize() + "," + page.getPageSize());
        }
    }

    public void rankHandler(RankReqDto rank) {
        // 设置排序规则
        if (rank.isRankAll()) {
            String orderByClause = rank.getRankKey() + " ";
            if (rank.isReverse()) {
                orderByClause += "DESC";
            } else {
                orderByClause += "ASC";
            }
            this.restrictions.setOrderByClause(orderByClause);
        }
    }
}
