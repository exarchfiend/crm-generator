package fun.mjauto.crm.service.impl;

import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.dto.PageReqDto;
import fun.mjauto.crm.model.dto.RankReqDto;
import fun.mjauto.crm.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> selectPagingCustomers(PageReqDto page) {
        return null;
    }

    @Override
    public List<Customer> selectPagingCustomers(RankReqDto rank) {
        return null;
    }

    @Override
    public List<Customer> selectRankCustomers(PageReqDto page, RankReqDto rank) {
        return null;
    }

    @Override
    public List<Customer> selectByCustomerKey(Customer customer) {
        return null;
    }
}
