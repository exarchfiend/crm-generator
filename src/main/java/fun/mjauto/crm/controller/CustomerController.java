package fun.mjauto.crm.controller;

import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/selectCount")
    public long countCustomers(@RequestBody CustomerReqDto condition) {
        return customerService.countCustomers(condition);
    }

    @PostMapping("/deleteRows")
    public int deleteCustomers(@RequestBody CustomerReqDto condition) {
        condition.setSelect(false);
        return customerService.deleteCustomers(condition);
    }

    @PostMapping("/deleteById")
    public int deleteCustomerByPrimaryKey(@RequestParam Integer id) {
        return customerService.deleteCustomerByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insertCustomer(@RequestBody Customer row) {
        return customerService.insertCustomer(row);
    }

    @PostMapping("/insertSelective")
    public int insertCustomerSelective(@RequestBody Customer row) {
        return customerService.insertCustomerSelective(row);
    }

    @PostMapping("/select")
    public List<Customer> selectCustomers(@RequestBody CustomerReqDto condition) {
        return customerService.selectCustomers(condition);
    }

    @PostMapping("/selectById")
    public Customer selectCustomerByPrimaryKey(@RequestParam Integer id) {
        return customerService.selectCustomerByPrimaryKey(id);
    }

    @PostMapping("/updateRowsSelective")
    public int updateCustomersSelective(@RequestBody CustomerReqDto condition) {
        return customerService.updateCustomersSelective(condition.getRow(),condition);
    }

    @PostMapping("/updateRows")
    public int updateCustomers(@RequestBody CustomerReqDto condition) {
        return customerService.updateCustomers(condition.getRow(),condition);
    }

    @PostMapping("/updateByIdSelective")
    public int updateCustomerByPrimaryKeySelective(@RequestBody Customer row) {
        return customerService.updateCustomerByPrimaryKeySelective(row);
    }

    @PostMapping("/updateById")
    public int updateCustomerByPrimaryKey(@RequestBody Customer row) {
        return customerService.updateCustomerByPrimaryKey(row);
    }
}
