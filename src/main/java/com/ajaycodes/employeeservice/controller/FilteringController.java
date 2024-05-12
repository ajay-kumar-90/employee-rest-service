package com.ajaycodes.employeeservice.controller;

import com.ajaycodes.employeeservice.dao.UserDao;
import com.ajaycodes.employeeservice.model.UserDetails;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilteringController {
    private final UserDao userDao;

    public FilteringController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/filter1")
    public MappingJacksonValue filter1() {
        List<UserDetails> userDetails = userDao.getDetails();
        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId", "panNumber");
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserDetails", propertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDetails);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
