package com.zhang.fileshare.fileControllerTest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/12 16:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void queryByTypeTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/queryByType/jpg", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void searchFileTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/searchFile/报名图片", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void previewTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/preview/2", requestEntity,String.class);
        Assertions.assertThat(response).contains("https://atguigu-yygh-zhazhalin.oss-cn-chengdu.aliyuncs.com/1zhang/2022-08-11/2a7fc1de1a454c5a8835a983475cab56报名照片.jpg");
    }
    @Test
    public void reclaimTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/reclaim/2", requestEntity,String.class);
        Assertions.assertThat(response).contains("您的文件已放置回收站");
    }
    @Test
    public void showReclaimTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/showReclaim/1/1/1", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void unReclaim(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/unReclaim/2", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void queryPrivateTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/queryPrivate/1/1/1", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
    @Test
    public void updatePrivateTest(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDMzMDY1NbA01FEqLU4t8kwBikGYfom5qUAtVRmJeelKtQBix5OdQgAAAA.siK1IDDrP6-TXaHqSD34TYvCUURN7jJLmNuvpB7rsWsVLuRq7YuwarLEY3lrET41XEBkHeKqiRi1Xwh_QDQtNA");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String response = restTemplate.postForObject("/myfile/updatePrivate/2/0", requestEntity,String.class);
        Assertions.assertThat(response).contains("成功");
    }
}
