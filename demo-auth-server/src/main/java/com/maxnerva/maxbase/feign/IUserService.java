package com.maxnerva.maxbase.feign;

import com.maxnerva.maxbase.msg.ObjectRestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "maxbase-admin")
public interface IUserService {
    /**
     * 通过账户\密码的方式登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,String>> validate(@RequestParam("username") String username, @RequestParam("password") String password);
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,String>> getUserInfoByUsername(@RequestParam("username") String username);
}