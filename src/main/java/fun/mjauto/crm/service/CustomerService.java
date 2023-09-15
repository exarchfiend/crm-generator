package fun.mjauto.crm.service;

import fun.mjauto.crm.model.dto.CustomerReqDto;
import fun.mjauto.crm.model.po.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * 统计符合查询条件的行数
     *
     * @param condition 查询条件
     * @return 符合查询条件的行数
     */
    long countCustomers(CustomerReqDto condition);

    /**
     * 删除符合查询条件的行
     *
     * @param condition 查询条件
     * @return 影响的行数
     */
    int deleteCustomers(CustomerReqDto condition);

    /**
     * 根据主键删除行
     *
     * @param id 查询条件
     * @return 影响的行数
     */
    int deleteCustomerByPrimaryKey(Integer id);

    /**
     * 插入一整行数据
     *
     * @param row 需要插入的数据
     * @return 影响的行数
     */
    int insertCustomer(Customer row);

    /**
     * 插入一行数据 只插入非空字段
     *
     * @param row 需要插入的数据
     * @return 影响的行数
     */
    int insertCustomerSelective(Customer row);

    /**
     * 查询符合条件的行
     *
     * @param condition 查询条件
     * @return 满足条件的所有行
     */
    List<Customer> selectCustomers(CustomerReqDto condition);

    /**
     * 根据主键查询行
     *
     * @param id 主键值
     * @return 主键所在行
     */
    Customer selectCustomerByPrimaryKey(Integer id);

    /**
     * 更新符合条件的行 只插入非空字段
     *
     * @param row       需要插入的数据
     * @param condition 查询条件
     * @return 影响的行数
     */
    int updateCustomersSelective(Customer row, CustomerReqDto condition);

    /**
     * 更新符合条件的行
     *
     * @param row       需要插入的数据
     * @param condition 查询条件
     * @return 影响的行数
     */
    int updateCustomers(Customer row, CustomerReqDto condition);

    /**
     * 根据主键更新行 只插入非空字段
     *
     * @param row 需要插入的数据
     * @return 影响的行数
     */
    int updateCustomerByPrimaryKeySelective(Customer row);

    /**
     * 根据主键更新行
     *
     * @param row 需要插入的数据
     * @return 影响的行数
     */
    int updateCustomerByPrimaryKey(Customer row);
}
