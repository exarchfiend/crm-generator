package fun.mjauto.crm.controller;

import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.dto.SelectDto;
import fun.mjauto.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/select")
    public List<Customer> select(@RequestBody SelectDto condition) {
        return customerService.selectCustomers(condition);
    }
}
