package br.com.facio.mkube.simple.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nginx-service", url = "http://nginx-service")
public interface NginxClient {

    @GetMapping("/hello")
    String getHello();
}
