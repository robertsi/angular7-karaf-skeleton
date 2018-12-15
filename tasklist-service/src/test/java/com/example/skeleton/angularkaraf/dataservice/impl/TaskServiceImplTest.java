package com.example.skeleton.angularkaraf.dataservice.impl;

import com.example.skeleton.angularkaraf.model.Task;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    @Test
    public void testWriteRead() throws Exception {
        TaskServiceImpl taskService = new TaskServiceImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tasklistTEST", System.getProperties());
        EntityManager em = emf.createEntityManager();
        taskService.em = em;

        em.getTransaction().begin();
        Task task = new Task();
        task.setId(1);
        task.setTitle("Test task");
        taskService.addTask(task);
        em.getTransaction().commit();
        Collection<Task> persons = taskService.getTasks();

        assertEquals(1, persons.size());
        Task task1 = persons.iterator().next();
        assertEquals(new Integer(1), task1.getId());
        assertEquals("Test task", task1.getTitle());
    }
}