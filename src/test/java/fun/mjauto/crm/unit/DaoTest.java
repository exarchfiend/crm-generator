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
        System.out.println("总行数期望为:34,实际为:" + customerMapper.countByExample(this.restrictions));
    }

    void test_deleteByExample() {
        // 批量删除
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(22, 23), null, "id", "id");
        System.out.println("批量删除期望为:2,实际为:" + customerMapper.deleteByExample(this.restrictions));
    }

    void test_deleteByPrimaryKey() {
        // 根据主键删除
        System.out.println("根据主键删除期望为:1,实际为:" + customerMapper.deleteByPrimaryKey(28));
    }

    void test_insert() {
        // 插入一行数据
        Customer customer = new Customer(null,
                "Mr.Zhu",
                "张三",
                123,
                "法外狂徒",
                "男",
                9528,
                null,
                1,
                1,
                "插入数据");
        System.out.println("插入数据期望为:1,实际为:" + customerMapper.insert(customer));
    }

    void test_insertSelective() {
        // 插入一行数据 只插入非空字段
        Customer customer = new Customer(null,
                "Mr.Zhu",
                "张三",
                123,
                "法外狂徒",
                "男",
                9528,
                null,
                1,
                1,
                "插入部分数据");
        // 插入前要检查必填项
        System.out.println("插入数据(非空字段)期望为:1,实际为:" + customerMapper.insertSelective(customer));
    }

    void test_selectByPrimaryKey(){
        System.out.println("根据主键查询数据期望为:id为1,实际为:" + customerMapper.selectByPrimaryKey(1));
    }

    void test_updateByExampleSelective(){
        // 修改符合条件的多行数据 只修改非空字段 没有符合条件的行并不会报错
        Customer customer = new Customer(null,
                "Mr.Zhu",
                "张三",
                123,
                "法外狂徒",
                "男",
                9528,
                null,
                1,
                1,
                "更新部分客户信息");
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(29, 30), null, "id", "id");
        System.out.println("修改符合条件的数据(非空字段)期望为:2,实际为:" + customerMapper.updateByExampleSelective(customer,this.restrictions));
    }

    void test_updateByExample(){
        // 修改符合条件的多行数据 没有符合条件的行并不会报错
        Customer customer = new Customer(null,
                "Mr.Qin",
                "李四",
                456,
                "超人强",
                "女",
                9529,
                null,
                2,
                2,
                "更新客户信息");
        this.restrictions.clear();
        this.restrictions.createCriteria()
                .createCriteria("In", Arrays.asList(31, 32), null, "id", "id");
        // 没有值的字段会被置空，所以插入前要检查必填项
        System.out.println("修改符合条件的数据期望为:2,实际为:" + customerMapper.updateByExample(customer,this.restrictions));
    }

    void test_updateByPrimaryKeySelective(){
        // 根据主键修改一行数据 只插入非空字段
        Customer customer = new Customer(33,
                "Mr.Zhu",
                "张三",
                123,
                "法外狂徒",
                "男",
                9528,
                null,
                1,
                1,
                "根据主键值更新部分客户信息");
        System.out.println("根据主键修改数据(非空字段)期望为:1,实际为:" + customerMapper.updateByPrimaryKeySelective(customer));
    }

    void test_updateByPrimaryKey(){
        // 根据主键修改一行数据
        Customer customer = new Customer(34,
                "Mr.Qin",
                "李四",
                456,
                "超人强",
                "女",
                9529,
                null,
                2,
                2,
                "根据主键值更新客户信息");
        // 没有值的字段会被置空，所以插入前要检查必填项
        System.out.println("根据主键修改数据期望为:1,实际为:" + customerMapper.updateByPrimaryKey(customer));
    }
}
