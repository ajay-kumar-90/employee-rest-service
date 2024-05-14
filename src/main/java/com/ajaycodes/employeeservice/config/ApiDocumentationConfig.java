package com.ajaycodes.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "Employee Service",
        description = "Employee Service with Swagger Documentation",
        termsOfService = "https://www.ajaycodes.com?etc",
        contact = @Contact(name = "Daniel",
                url = "https://www.ajaycodes.com",
                email = "support@mail.com"),
        license = @License(name = "Employee License Version 1.0", url = "https://www.ajaycodes.com/license"),
        version = "3.0"))
public class ApiDocumentationConfig {
}
