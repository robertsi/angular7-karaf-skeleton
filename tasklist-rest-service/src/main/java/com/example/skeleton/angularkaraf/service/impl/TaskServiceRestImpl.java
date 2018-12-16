package com.example.skeleton.angularkaraf.service.impl;

import com.example.skeleton.angularkaraf.model.Task;
import com.example.skeleton.angularkaraf.model.TaskService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Named

@Bean
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@CrossOriginResourceSharing(
        allowAllOrigins = true
//        allowOrigins = {
//                "http://localhost:4200"
//        }
//        allowCredentials = true,
//        maxAge = 1,
//        allowHeaders = {
//                "X-custom-1", "X-custom-2"
//        },
//        exposeHeaders = {
//                "X-custom-3", "X-custom-4"
//        }
)
@Tag(name = "tasks")
@Path("/")
public class TaskServiceRestImpl {


    @Inject
    @Reference
    TaskService taskService;

    @Context
    UriInfo uri;

    @GET
    @Path("{id}")
    @Operation(summary = "Find task by ID",
            //tags = {"tasks"},
            description = "Returns a task when ID > 0",
            responses = {
                    @ApiResponse(description = "The task", content = @Content(
                            schema = @Schema(implementation = Task.class)
                    )),
                   // @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            })
    public Response getTask(
            @PathParam("id")
            @Parameter(description = "Task ID",  schema = @Schema(
                    type = "integer",
                    format = "int64",
                    description = "param ID of task that needs to be fetched",
                    allowableValues = {"1","2","3"}
            ), required = true)  Integer id) {
        Task task = taskService.getTask(id);
//        Task task = new Task(1, "Some dummy task");
        return task == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(task).build();
    }

    @POST
    public Response addTask(Task task) {
        taskService.addTask(task);
        URI taskURI = uri.getRequestUriBuilder().path(TaskServiceRestImpl.class, "getTask").build(task.getId());
        return Response.created(taskURI).build();
    }

    @GET
    @Operation(
            summary = "List all tasks",
            description = "returns a list of all tasks",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Task.class))
                            ),
                            responseCode = "200"
                    )
            }
    )
    public Collection<Task> getTasks() {
        Collection<Task> tasks = taskService.getTasks();
        return tasks;
    }

    @PUT
    @Path("{id}")
    public void updateTask(@PathParam("id") Integer id, Task task) {
        task.setId(id);
        taskService.updateTask(task);
    }

    @DELETE
    @Path("{id}")
    public void deleteTask(@PathParam("id") Integer id) {
        taskService.deleteTask(id);
    }
}
