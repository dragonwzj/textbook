package com.example.textbook.service;

import com.example.textbook.domain.Classes;
import com.example.textbook.domain.Distribution;
import com.example.textbook.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    public Classes findClassesByClassCode(Integer code) {
        return classesRepository.findClassesByClassCode(code);
    }

    public boolean createClasses(Classes classes) {
        try {
            classesRepository.save(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    public List<Distribution> getPickBookOrderByClassCode(Integer classCode) {
        Classes classes = classesRepository.findClassesByClassCode(classCode);
        return classes.getDistributionList();
    }


}
