package com.example.skeleton.angularkaraf.service.impl;

import com.example.skeleton.angularkaraf.model.Task;
import com.example.skeleton.angularkaraf.model.TaskService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Named

@Bean
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
@CrossOriginResourceSharing(
        allowOrigins = {
                "http://localhost:4200"
        }
//        allowCredentials = true,
//        maxAge = 1,
//        allowHeaders = {
//                "X-custom-1", "X-custom-2"
//        },
//        exposeHeaders = {
//                "X-custom-3", "X-custom-4"
//        }
)
public class TaskServiceRestImpl {


    @Inject
    @Reference
    TaskService taskService;

    @Context
    UriInfo uri;

    @GET
    @Path("{id}")
    public Response getTask(@PathParam("id") Integer id) {
        Task task = taskService.getTask(id);
//        Task task = new Task(1, "Some dummy task");
        if(task == null && id == 1)
        {
            taskService.addTask(new Task(1, "Task added by rest"));
        }
        return task == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(task).build();
    }

    @POST
    public Response addTask(Task task) {
        taskService.addTask(task);
        URI taskURI = uri.getRequestUriBuilder().path(TaskServiceRestImpl.class, "getTask").build(task.getId());
        return Response.created(taskURI).build();
    }

    @GET
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
