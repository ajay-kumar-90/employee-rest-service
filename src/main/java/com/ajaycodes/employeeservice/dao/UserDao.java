package com.ajaycodes.employeeservice.dao;

import com.ajaycodes.employeeservice.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDao {

    public List<UserDetails> getDetails() {
        return Arrays.asList(new UserDetails(12, "Daniel", "AVGPOAMU"),
                new UserDetails(13, "Mike", "AVGPASMU"),
                new UserDetails(14, "Mike", "VATADD")
        );
    }
}
