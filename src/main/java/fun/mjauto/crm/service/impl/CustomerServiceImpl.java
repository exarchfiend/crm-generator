package fun.mjauto.crm.service.impl;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.service.CustomerService;
import fun.mjauto.crm.utils.impl.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> selectCustomers(SelectDto condition) {
        return customerMapper.selectByExample(new CustomerHandler(condition).getRestrictions());
    }
}
