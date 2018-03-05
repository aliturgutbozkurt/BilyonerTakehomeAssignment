package com.bilyoner.microservices.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bilyoner.microservices.homework.domain.CustomData;

public interface CustomDataServiceContract  {
    @RequestMapping("/number-service")
    String testNumberService();
    
    @RequestMapping(value="/custom-datas/findAll",method = RequestMethod.GET)
    ResponseEntity<List<CustomData>> findAllCustomDatas(@RequestParam(required=false,value="orderType",defaultValue="ascending" ) String orderType);
    
    @RequestMapping(value="/custom-datas/create",method = RequestMethod.POST)
    ResponseEntity<?> createCustomData(CustomData customData);
    
    @RequestMapping(value="/custom-datas/find-by-number/{number}")
    ResponseEntity<CustomData> findCustomDataByNumber(@PathVariable Long number);
    
    @RequestMapping(value="/custom-datas/find-by-max-number")
    ResponseEntity<CustomData> findCustomDataByMaximumNumber(@PathVariable Long number);
    
    @RequestMapping(value="/custom-datas/find-by-min-number")
    ResponseEntity<CustomData> findCustomDataByMinumumNumber(@PathVariable Long number);
    
    @RequestMapping(value="/custom-datas/delete-by-number/{number}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCustomDataByNumber(@PathVariable Long number);
}
