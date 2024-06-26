package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * description 插入员工数据
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/25 18:58
     * @param: [employee]
     * @return void
     **/
    @Insert("insert into employee(name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user, status) " +
            "values " +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    void insert(Employee employee);

    /**
     * description：分页查询
     * @author: sichenyong
     * @email: sichenyongwork@163.com       
     * @date: 2024/6/26 18:14
     * @param: [employeePageQueryDTO]
     * @return com.github.pagehelper.Page<com.sky.entity.Employee>
     **/
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * description 根据id更新对象字段
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 18:59
     * @param: [employee]
     * @return void
     **/
    void update(Employee employee);

    /**
     * description 根据用户id查询员工信息
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 19:16
     * @param: [id]
     * @return com.sky.entity.Employee
     **/
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
