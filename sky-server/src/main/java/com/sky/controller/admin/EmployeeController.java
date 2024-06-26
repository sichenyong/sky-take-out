package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;


    @ApiOperation("修改员工信息")
    @PutMapping
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("编辑员工信息：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     * description 根据用户id查询用户信息
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 19:18
     * @param: [id]
     * @return com.sky.result.Result<com.sky.entity.Employee>
     **/
    @GetMapping("/{id}")
    @ApiOperation("根据用户id查询用户信息")
    public Result<Employee> getById(@PathVariable Long id){
        return Result.success(employeeService.getById(id));
    }
    /**
     * description: 启用、禁用员工账号
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 18:55
     * @param: [status, id]
     * @return com.sky.result.Result
     **/
    @PostMapping("/status/{status}")
    @ApiOperation("启用/禁用员工")
    public Result changStatus(@PathVariable("status") Integer status, Long id) {
        log.info("启用、禁用员工账号：{}, {}", status, id);
        employeeService.changStatus(status, id);
        return Result.success();
    }

    /**
     * description： 员工分页查询
     * @author: sichenyong
     * @email: sichenyongwork@163.com
     * @date: 2024/6/26 18:07
     * @param: [employeePageQueryDTO]
     * @return com.sky.result.Result<com.sky.result.PageResult>
     **/
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询， 参数：{}", employeePageQueryDTO);

        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    @ApiOperation("新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);

        employeeService.save(employeeDTO);
        return Result.success();
    }


    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation(value = "员工退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
