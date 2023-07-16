package com.TwoDay.Homework.Services;

import com.TwoDay.Homework.Models.EnclosureObject;
import com.TwoDay.Homework.Repo.EnclosureObjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnclosureObjectService {
    @Autowired
    private EnclosureObjectsRepository enclosureObjectRepository;

    public void addEnclosureObject(EnclosureObject enclosureObject) {
        enclosureObjectRepository.save(enclosureObject);
    }

    public List<EnclosureObject> getAllEnclosureObjects() {
        return enclosureObjectRepository.findAll();
    }
}
