package fun.mjauto.crm.unit;

import fun.mjauto.crm.dao.CustomerMapper;
import fun.mjauto.crm.model.po.Customer;
import fun.mjauto.crm.model.bo.Restrictions;

import java.util.Arrays;

public class DaoTest {

    private final CustomerMapper customerMapper;
    private final Restrictions restrictions;

    public DaoTest(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.restrictions = new Restrictions();
        test_countByExample();
        test_deleteByExample();
        test_deleteByPrimaryKey();
        test_insert();
        test_insertSelective();
        test_selectByPrimaryKey();
        test_updateByExampleSelective();
        test_updateByExample();
        test_updateByPrimaryKeySelective();
        test_updateByPrimaryKey();
    }

    void test_countByExample() {
        // 统计总行数
        System.out.println(customerMapper.countByExample(this.restrictions));
    }

    void test_deleteByExample() {
        // 批量删除
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(531, 532, 543), null, "id", "id");
        System.out.println(customerMapper.deleteByExample(this.restrictions));
    }

    void test_deleteByPrimaryKey() {
        // 根据主键删除
        System.out.println(customerMapper.deleteByPrimaryKey(202));
    }

    void test_insert() {
        // 插入一行数据
        Customer customer = new Customer();
        customer.setCustomerName("王富贵");
        customer.setCustomerContacts("张三");
        customer.setPhoneNumber(123456);
        customer.setCompanyPosition("法外狂徒");
        customer.setCustomerGender("男");
        customer.setUnifiedCreditCode(9528);
        customer.setBusinessLicence("木有");
        customer.setCustomerSource(1);
        customer.setCustomerCooperationIntention(1);
        customer.setCustomerIntroduction("这是一个帅哥");
        System.out.println(customerMapper.insert(customer));
    }

    void test_insertSelective() {
        // 插入一行数据 只插入非空字段
        Customer customer = new Customer();
        customer.setCustomerName("王富贵");
        customer.setCustomerContacts("张三");
        customer.setPhoneNumber(123456);
        customer.setUnifiedCreditCode(9528);
        customer.setCustomerSource(1);
        customer.setCustomerCooperationIntention(1);
        // 插入前要检查必填项
        System.out.println(customerMapper.insertSelective(customer));
    }

    void test_selectByPrimaryKey(){
        System.out.println(customerMapper.selectByPrimaryKey(300));
    }

    void test_updateByExampleSelective(){
        // 修改符合条件的多行数据 只修改非空字段 没有符合条件的行并不会报错
        Customer customer = new Customer();
        customer.setCustomerName("王富贵");
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(511, 512, 299), null, "id", "id");
        System.out.println(customerMapper.updateByExampleSelective(customer,this.restrictions));
    }

    void test_updateByExample(){
        // 修改符合条件的多行数据 没有符合条件的行并不会报错
        Customer customer = new Customer();
        customer.setCustomerName("王富贵");
        customer.setCustomerContacts("张三");
        customer.setPhoneNumber(123456);
        customer.setUnifiedCreditCode(9528);
        customer.setCustomerSource(1);
        customer.setCustomerCooperationIntention(1);
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(511, 512, 299), null, "id", "id");
        // 没有值的字段会被置空，所以插入前要检查必填项
        System.out.println(customerMapper.updateByExample(customer,this.restrictions));
    }

    void test_updateByPrimaryKeySelective(){
        // 根据主键修改一行数据 只插入非空字段
        Customer customer = new Customer();
        customer.setId(500); // 不存在的行不会报错
        customer.setCustomerName("王富贵");
        System.out.println(customerMapper.updateByPrimaryKeySelective(customer));
    }

    void test_updateByPrimaryKey(){
        // 根据主键修改一行数据
        Customer customer = new Customer();
        customer.setId(506);
        customer.setCustomerName("王富贵");
        customer.setCustomerContacts("张三");
        customer.setPhoneNumber(123456);
        customer.setUnifiedCreditCode(9528);
        customer.setCustomerSource(1);
        customer.setCustomerCooperationIntention(1);
        customer.setCustomerName("王富贵");
        // 没有值的字段会被置空，所以插入前要检查必填项
        System.out.println(customerMapper.updateByPrimaryKey(customer));
    }
}
