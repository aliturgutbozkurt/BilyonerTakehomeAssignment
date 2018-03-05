package com.bilyoner.microservices.clients;

import com.bilyoner.microservices.contracts.CustomDataServiceContract;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("custom-data-service")
public interface CustomDataServiceClient extends CustomDataServiceContract {

}
