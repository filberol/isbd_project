package com.isbd.coursework.entities;

import com.isbd.coursework.services.WorkerService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class Worker implements WorkerService {
    @Override
    public Collection<String> getWorkers() {
        ArrayList<String> list = new ArrayList<>();
        list.add("777");
        list.add("666");
        return list;
    }
}
