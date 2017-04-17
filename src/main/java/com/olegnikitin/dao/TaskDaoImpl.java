package com.olegnikitin.dao;

import com.olegnikitin.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by olegnikitindev on 06.03.2017.
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public List<Task> findAll() {
        return mongo.findAll(Task.class);
    }

    @Override
    public Task find(String id) {
        return mongo.findOne(new Query(Criteria.where("id").is(id)), Task.class);
    }

    @Override
    public void create(Task task) {
        mongo.save(task);
    }

    @Override
    public void update(Task task) {
        mongo.updateFirst(new Query(Criteria.where("id").is(task.getId())), Update.update("name", task.getName()), Task.class);
    }

    @Override
    public void delete(Task task) {
        mongo.remove(task);
    }
}
