package com.TwoDay.Homework.Services;

import com.TwoDay.Homework.Models.Animal;
import com.TwoDay.Homework.Models.Enclosure;
import com.TwoDay.Homework.Repo.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {
    @Autowired
    private EnclosureRepository enclosureRepository;

    public void addSingleEnclosure(Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }

    public void addEnclosures(List<Enclosure> enclosures) {
        enclosureRepository.saveAll(enclosures);
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    public boolean deleteEnclosure(long id){
        Optional<Enclosure> deleteCandidate = enclosureRepository.findById(id);
        if(deleteCandidate.isPresent()){
            enclosureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
