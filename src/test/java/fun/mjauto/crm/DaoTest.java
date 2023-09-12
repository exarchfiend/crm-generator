package fun.mjauto.crm;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.Restrictions;
import fun.mjauto.crm.model.dto.ConditionReqDto;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;

import java.util.List;

public class DaoTest {
    void test_paging(CustomerMapper customerMapper) {
        // 统计总行数
        Restrictions restrictions = new Restrictions();
        long count = customerMapper.countByExample(restrictions);
        System.out.println(count);

        // 设置限制条件（要求id小于等于540）
        ConditionReqDto conditionReqDto = new ConditionReqDto();
        conditionReqDto.setCondition("LessThanOrEqualTo");
        conditionReqDto.setValue(540);
        conditionReqDto.setProperty("id");
        if (conditionReqDto.getCondition() != null) {
            switch (conditionReqDto.getCondition()) {
                case "IsNull", "IsNotNull" -> restrictions.or().noValue(conditionReqDto.getCondition(),
                        getProperty(conditionReqDto.getProperty()));
                case "EqualTo", "LessThan", "NotEqualTo", "GreaterThan", "GreaterThanOrEqualTo", "LessThanOrEqualTo", "In", "NotIn" ->
                        restrictions.or().singleValue(conditionReqDto.getCondition(),
                                conditionReqDto.getValue(),
                                getProperty(conditionReqDto.getProperty()),
                                conditionReqDto.getProperty());
                case "Between", "NotBetween" -> restrictions.or().betweenValue(conditionReqDto.getCondition(),
                        conditionReqDto.getValue(),
                        conditionReqDto.getSecondValue(),
                        getProperty(conditionReqDto.getProperty()),
                        conditionReqDto.getProperty());
                case "Like", "NotLike" -> restrictions.or().singleStringValue(conditionReqDto.getCondition(),
                        conditionReqDto.getValue().toString(),
                        getProperty(conditionReqDto.getProperty()),
                        conditionReqDto.getProperty());
            }
        }

        // 设置排序规则（根据id逆序查询）
        RankReqDto rank = new RankReqDto();
        rank.setRankAll(true);
        rank.setRankKey("id");
        rank.setReverse(true);
        if (rank.isRankAll()) {
            String orderByClause = rank.getRankKey() + " ";
            if (rank.isReverse()) {
                orderByClause += "DESC";
            } else {
                orderByClause += "ASC";
            }
            restrictions.setOrderByClause(orderByClause);
        }

        // 设置偏移量和行数大小（第2页，每页10行）
        PageReqDto page = new PageReqDto();
        page.setFetchAll(true);
        page.setPageSize(10);
        page.setPageNum(2);
        if (page.isFetchAll()) {
            restrictions.setPaging((page.getPageNum() - 1) * page.getPageSize() + "," + page.getPageSize());
        }

        List<Customer> customers = customerMapper.selectByExample(restrictions);
        System.out.println(customers);
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
