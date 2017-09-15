package com.phicomm.big.data.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.phicomm.big.data.module.luck.TestRequestModel;
import com.phicomm.big.data.module.luck.TestResponseModel;

import springfox.documentation.annotations.ApiIgnore;

/**
 * H5页面接口
 *
 * @author yufei.liu
 */
@Controller
@ApiIgnore
public class TestController {

    /**
     * 返回单一静态页面
     */
    @RequestMapping("test1")
    public ModelAndView test1() {
        return new ModelAndView("test1");
    }

    /**
     * 返回带有资源文件的静态页面
     */
    @RequestMapping("test2")
    public ModelAndView test2() {
        return new ModelAndView("test2");
    }

    /**
     * 返回动态页面
     */
    @RequestMapping("test3")
    public String test3( Model model ) {
    	
    	model.addAttribute("key", "value");
    	
     /*   ModelAndView modelAndView = new ModelAndView("test3");
        modelAndView.getModel().put("key", "value");
        return modelAndView;*/
    	
    	return "test3";
    }

    /**
     * 请求上传的正文是{"userId": "aaaaaaa"}，自动将json字符串转为java对象，同时返回json对象
     */
    @RequestMapping("test4")
    @ResponseBody
    public TestResponseModel test4(@RequestBody TestRequestModel testRequestModel) {
        System.out.println(testRequestModel);
        return new TestResponseModel();
    }

    /**
     * 请求上传的正文是{"userId": "aaaaaaa"}，自动将json字符串转为java对象，同时返回json对象
     *
     * 只接受post请求
     */
    @RequestMapping(value = "test5", method = RequestMethod.POST)
    @ResponseBody
    public TestResponseModel test5(@RequestBody TestRequestModel testRequestModel) {
        System.out.println(testRequestModel);
        return new TestResponseModel();
    }

    /**
     * 从header中取值
     */
    @RequestMapping(value = "test6", method = RequestMethod.POST)
    @ResponseBody
    public TestResponseModel test6(@RequestHeader("phicomm-key") String phicommKey,
                                   @RequestBody TestRequestModel testRequestModel) {
        System.out.println(phicommKey);
        System.out.println(testRequestModel);
        return new TestResponseModel();
    }

}

