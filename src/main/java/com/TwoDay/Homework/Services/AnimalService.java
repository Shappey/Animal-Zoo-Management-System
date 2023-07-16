package com.TwoDay.Homework.Services;

import com.TwoDay.Homework.Models.Animal;
import com.TwoDay.Homework.Repo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public void addSingleAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void addAllAnimals(List<Animal> animals) {
        animalRepository.saveAll(animals);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public boolean deleteAnimal(long id){
        Optional<Animal> deleteCandidate = animalRepository.findById(id);
        if(deleteCandidate.isPresent()){
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
