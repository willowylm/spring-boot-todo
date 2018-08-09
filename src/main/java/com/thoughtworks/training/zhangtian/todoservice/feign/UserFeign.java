package com.thoughtworks.training.zhangtian.todoservice.feign;


import com.thoughtworks.training.zhangtian.todoservice.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user", url = "http://localhost:8081/api")
public interface UserFeign {
    @PostMapping("/validate")
    User validateUser(String token);
}
