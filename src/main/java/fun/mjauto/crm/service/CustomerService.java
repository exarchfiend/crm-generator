package fun.mjauto.crm.service;

import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.dto.SelectDto;

import java.util.List;

public interface CustomerService {
    /**
     * 条件,排序和分页组合查询
     *
     * @return 符合查询条件的行
     */
    List<Customer> selectCustomers(SelectDto condition);
}
