package com.example.skeleton.angularkaraf.dataservice.impl;

import com.example.skeleton.angularkaraf.model.Task;
import com.example.skeleton.angularkaraf.model.TaskService;
import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.AutoExport;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;

@Bean
//@Service(autoExport = AutoExport.INTERFACES, properties = @ServiceProperty(name="service.exported.interfaces", values="*"))
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    @PersistenceContext(unitName="tasklist")
    EntityManager em;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Task getTask(Integer id) {
        LOG.info("Executing getTask(" + id +");");
        return em.find(Task.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void addTask(Task task) {
        em.persist(task);
        em.flush();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Collection<Task> getTasks() {
        CriteriaQuery<Task> query = em.getCriteriaBuilder().createQuery(Task.class);
        return em.createQuery(query.select(query.from(Task.class))).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void updateTask(Task task) {
        em.merge(task);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteTask(Integer id) {
        em.remove(getTask(id));
    }

}

