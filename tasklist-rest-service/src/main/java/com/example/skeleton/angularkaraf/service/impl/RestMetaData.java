package com.example.skeleton.angularkaraf.service.impl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(
        info = @Info(
                title = "Task Management API",
                version = "1.0.0-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = @Server(url = "http://localhost:8181/cxf/tasks", description = "DEV Server")
)
@ApplicationPath("cfx/tasks")
public class RestMetaData {
}
