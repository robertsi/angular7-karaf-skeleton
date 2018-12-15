package com.example.skeleton.angularkaraf.dataservice.impl;

import com.example.skeleton.angularkaraf.model.Task;
import com.example.skeleton.angularkaraf.model.TaskService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class InitHelper {

    Logger LOG = LoggerFactory.getLogger(InitHelper.class);

    @Inject
    TaskService taskService;

    @PostConstruct
    public void addDemoTasks() {
        LOG.info("Executing addDemoTasks");
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (taskService.getTask(1) == null) {
                        addSampleTask();
                    }
                } catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
                }
            }
        });

    }

    private void addSampleTask() {
        LOG.info("Executing addSampleTask");
        Task task = new Task(1, "Just a sample init task");
        taskService.addTask(task);
    }
}
