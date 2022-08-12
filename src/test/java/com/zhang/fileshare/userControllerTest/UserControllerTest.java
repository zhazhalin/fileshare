package com.zhang.fileshare.userControllerTest;

import com.zhang.fileshare.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/12 14:22
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void loginTest() {
        String response = restTemplate.getForObject("/user/login/zhang/zhang", String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void registerTest() {
        User user=new User();
        user.setUserName("zhazhalin");
        user.setUserPwd("zhazhalin");
        String response = restTemplate.postForObject("/user/register", user,String.class);
        Assertions.assertThat(response).contains("成功");
    }
}
