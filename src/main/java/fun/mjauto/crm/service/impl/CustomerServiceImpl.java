package fun.mjauto.crm.service.impl;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.Customer;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.service.CustomerService;
import fun.mjauto.crm.utils.TypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> selectRankCustomers(SelectDto condition) {
        return customerMapper.selectByExample(new TypeHandler().typeHandler(condition));
    }

    @Override
    public List<Customer> selectByCustomerKey(Customer customer) {
        return null;
    }
}
