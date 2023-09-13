package fun.mjauto.crm.utils.impl;

import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.utils.EntityHandler;

public class CustomerHandler extends EntityHandler{

    public CustomerHandler(SelectDto condition) {
        super();
        super.conditionsHandler(condition.getConditionList());
        super. pagingHandler(condition.getPageReqDto());
        super.rankHandler(condition.getRankReqDto());
    }

    @Override
    public String getProperty(String key) {
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
