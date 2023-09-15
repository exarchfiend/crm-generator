package fun.mjauto.crm.service.impl;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.po.Customer;
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
    public long countCustomers(CustomerReqDto condition) {
        return customerMapper.countByExample(new CustomerHandler(condition).getRestrictions());
    }

    @Override
    public int deleteCustomers(CustomerReqDto condition) {
        return customerMapper.deleteByExample(new CustomerHandler(condition).getRestrictions());
    }

    @Override
    public int deleteCustomerByPrimaryKey(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertCustomer(Customer row) {
        return customerMapper.insert(row);
    }

    @Override
    public int insertCustomerSelective(Customer row) {
        return customerMapper.insertSelective(row);
    }

    @Override
    public List<Customer> selectCustomers(CustomerReqDto condition) {
        return customerMapper.selectByExample(new CustomerHandler(condition).getRestrictions());
    }

    @Override
    public Customer selectCustomerByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCustomersSelective(Customer row, CustomerReqDto condition) {
        return customerMapper.updateByExampleSelective(row,new CustomerHandler(condition).getRestrictions());
    }

    @Override
    public int updateCustomers(Customer row, CustomerReqDto condition) {
        return customerMapper.updateByExample(row,new CustomerHandler(condition).getRestrictions());
    }

    @Override
    public int updateCustomerByPrimaryKeySelective(Customer row) {
        return customerMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int updateCustomerByPrimaryKey(Customer row) {
        return customerMapper.updateByPrimaryKey(row);
    }
}
