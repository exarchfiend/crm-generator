package fun.mjauto.crm.utils;

import fun.mjauto.crm.model.Restrictions;
import fun.mjauto.crm.model.dto.ConditionReqDto;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;
import fun.mjauto.crm.model.dto.SelectDto;

import java.util.List;

public class TypeHandler {

    private Restrictions restrictions;

    public Restrictions typeHandler(SelectDto condition) {
        this.restrictions = new Restrictions();
        conditionsHandler(condition.getConditionList());
        pagingHandler(condition.getPageReqDto());
        rankHandler(condition.getRankReqDto());
        return this.restrictions;
    }

    void conditionsHandler(List<ConditionReqDto> conditions) {
        for (ConditionReqDto conditionReqDto : conditions) {
            String condition = conditionReqDto.getCondition();
            Object value = conditionReqDto.getValue();
            Object secondValue = conditionReqDto.getSecondValue();
            String key = getProperty(conditionReqDto.getProperty());
            String property = conditionReqDto.getProperty();
            if (conditionReqDto.getCondition() != null) {
                switch (conditionReqDto.getCondition()) {
                    case "IsNull", "IsNotNull" ->
                            restrictions.or().noValue(condition,key);
                    case "EqualTo", "LessThan", "NotEqualTo", "GreaterThan", "GreaterThanOrEqualTo", "LessThanOrEqualTo", "In", "NotIn" ->
                            restrictions.or().singleValue(condition, value, key, property);
                    case "Between", "NotBetween" ->
                            restrictions.or().betweenValue(condition, value, secondValue, key, property);
                    case "Like", "NotLike" ->
                            restrictions.or().singleStringValue(condition, value.toString(), key, property);
                }
            }
        }
    }

    void pagingHandler(PageReqDto page) {
// 设置偏移量和行数大小（第2页，每页10行）
        if (page.isFetchAll()) {
            this.restrictions.setPaging((page.getPageNum() - 1) * page.getPageSize() + "," + page.getPageSize());
        }
    }

    void rankHandler(RankReqDto rank) {
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

    private static String getProperty(String key) {
        return switch (key) {
            case "id" -> key = "id";
            case "customerName" -> key = "customer_name";
            case "customerContacts" -> key = "customer_contacts";
            case "phoneNumber" -> key = "phone_number";
            case "companyPosition" -> key = "company_position";
            case "customerGender" -> key = "customer_gender";
            case "unifiedCreditCode" -> key = "unified_credit_code";
            case "businessLicence" -> key = "business_licence";
            case "customerSource" -> key = "customer_source";
            case "customerCooperationIntention" -> key = "customer_cooperation_intention";
            case "customerIntroduction" -> key = "customer_introduction";
            default -> throw new IllegalStateException("Unexpected value: " + key);
        };
    }
}
