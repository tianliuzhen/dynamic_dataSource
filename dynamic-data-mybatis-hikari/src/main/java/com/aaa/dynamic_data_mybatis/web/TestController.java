package com.aaa.dynamic_data_mybatis.web;

import com.aaa.dynamic_data_mybatis.dao.test1.Test1Mapper;
import com.aaa.dynamic_data_mybatis.dao.test1.TkMapperTest1;
import com.aaa.dynamic_data_mybatis.dao.test2.Test2Mapper;
import com.aaa.dynamic_data_mybatis.entity.A;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/13
 */
@RestController
public class TestController {

    @Autowired
    private Test1Mapper test1Mapper;

    @Autowired
    private Test2Mapper test2Mapper;

    @Autowired
    private TkMapperTest1 tkMapperTest1;

    @ApiOperation("test测试接口")
    @GetMapping("/test")
    public void test1(){
        List<Map> list=test1Mapper.getAll2();
        for (Map map : list) {
            System.out.println(map.get("a")+"_"+map.get("b"));
        }
        System.out.println("---------数据源分割线------------");
        List<Map> list2=test2Mapper.getAll();
        for (Map map : list2) {
            System.out.println(map.get("a")+"_"+map.get("b"));
        }
    }
    @ApiOperation("test测试接口")
    @GetMapping("/testTKmapper")
    public void testTKmapper(){

        A a=new A();
        a.setA("12");
        a.setB(2222);
        tkMapperTest1.insert(a);

        System.out.println(tkMapperTest1.selectAll().size());;
    }

    @ApiOperation("单个数据源事务测试")
    @GetMapping(value = "/txDbOne")
    @Transactional(value="test1TransactionManager",rollbackFor = Exception.class,timeout=36000)
    public void txDbOne(){
        //这种只能回滚一个 数据源
        test1Mapper.updateByA(1);
        test2Mapper.updateByA(1);
        throw new RuntimeException("测试是否会回滚");
    }

    @ApiOperation("多数据源事务测试")
    @GetMapping(value = "/txDb2")
    @Transactional()
    public void txDb2(){
        //使用atomikos+jta解决分布式事务问题

        test1Mapper.updateByA(1);
        test2Mapper.updateByA(1);
        throw new RuntimeException("测试是否会回滚");
    }
}
