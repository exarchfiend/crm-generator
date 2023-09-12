package fun.mjauto.crm.service;

import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;

import java.util.List;

public interface CustomerService {

    /**
     * 分页查询
     *
     * @return 符合查询条件的行
     */
    List<Customer> selectPagingCustomers(PageReqDto page);

    /**
     * 排序查询
     *
     * @return 符合查询条件的行
     */
    List<Customer> selectPagingCustomers(RankReqDto rank);

    /**
     * 排序后分页查询
     *
     * @return 符合查询条件的行
     */
    List<Customer> selectRankCustomers(PageReqDto page, RankReqDto rank);

    /**
     * 按字段查询
     *
     * @return 符合查询条件的行
     */
    List<Customer> selectByCustomerKey(Customer customer);
}
