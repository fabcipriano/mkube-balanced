package br.com.facio.mkube.simple.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * We now that we dont need another interface to call especifically with envoy. But we create this
 * to make clear the test with balance and not balance requests do k8s deployments.
 */
@FeignClient(name = "envoy-service", url = "http://envoy-service:10000")
public interface EnvoyClient {
    
    @GetMapping("/hello")
    String getHello();

}
