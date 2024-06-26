package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * description 新增员工方法
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/25 18:50
     * @param: [employeeDTO]
     * @return void
     **/
    void save(EmployeeDTO employeeDTO);

    /**
     * description： 分页查询方法
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 18:09
     * @param: [employeePageQueryDTO]
     * @return com.sky.result.PageResult
     **/
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * description：启用、禁用员工账号
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 18:56
     * @param: [status, id]
     * @return void
     **/
    void changStatus(Integer status, Long id);

    /**
     * description 根据用户id查询用户信息
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 19:14
     * @param: [id]
     * @return com.sky.entity.Employee
     **/
    Employee getById(Long id);

    /**
     * description 编辑员工信息
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 19:20
     * @param: [employeeDTO]
     * @return void
     **/
    void update(EmployeeDTO employeeDTO);
}
