package com.thoughtworks.training.zhangtian.todoservice.service;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.training.zhangtian.todoservice.model.Todo;
import com.thoughtworks.training.zhangtian.todoservice.model.User;
import com.thoughtworks.training.zhangtian.todoservice.repository.TodoRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    private User user;

    public List<Todo> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        int id = (int) authentication.getPrincipal();
        User user = (User) authentication.getPrincipal();

        List<Todo> allByUserId = todoRepository.findAllByUserId(user.getId());
        return allByUserId;
    }

    public Todo findById(int id) throws NotFoundException {
        return Optional.ofNullable(todoRepository.findOne(id))
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    public Integer create(Todo todo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        int id = (int) authentication.getPrincipal();
        User user = (User) authentication.getPrincipal();


        todo.setUserId(user.getId());
        return todoRepository.save(todo).getId();
    }

    public Integer update(Integer id, Todo todo) {
        Todo one = todoRepository.findOne(id);

        BeanUtils.copyProperties(todo, one, "id", "tasks");

        return todoRepository.save(one).getId();
    }

    public void delete(Integer id) {
        todoRepository.delete(id);
    }
}
