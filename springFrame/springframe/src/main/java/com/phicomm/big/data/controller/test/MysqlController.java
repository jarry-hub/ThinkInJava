package com.phicomm.big.data.controller.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phicomm.big.data.model.common.CommonResponse;
import com.phicomm.big.data.module.test.TestModel;
import com.phicomm.big.data.service.TestService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 测试mysql
 */
@Controller
public class MysqlController {
	
	private final TestService testService;
	
	@Autowired
    public MysqlController(TestService testService) {
        this.testService = testService;
        Assert.notNull(this.testService);
    }
	
	@RequestMapping(value = "test/mysql/{splitTableFlag}/{id}", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "获取mysql_test中id对应的记录")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常情况", response = TestModel.class)
    })
    public TestModel getData(@PathVariable() Long splitTableFlag, @PathVariable() Long id) {
        return testService.getData(splitTableFlag, id);
    }
	
	@RequestMapping(value = "test/mysql/insert", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	@ResponseBody
	@ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常情况", response = CommonResponse.class)
    })
	public CommonResponse testSplitTable(@RequestBody TestModel data) {
		data.setCreateTime(new Date());
        testService.saveData(data);
        return CommonResponse.ok();
    }

/*    @RequestMapping(value = "test/mysql/split/table", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Integer testSplitTable(@RequestBody int n) {
        return testService.testSplitTable(n);
    }*/

}
