package com.TwoDay.Homework.Services;

import com.TwoDay.Homework.Models.Animal;
import com.TwoDay.Homework.Models.Enclosure;
import com.TwoDay.Homework.Repo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalTransferService {

    @Autowired
    private AnimalRepository animalRepository;

    public boolean transferAnimalsToZoo(List<Animal> animals, List<Enclosure> enclosures){
        List<Enclosure> availableEnclosures = new ArrayList<>(enclosures);

        // Transfer herbivore animals to the first enclosure
        List<Animal> herbivoreAnimals = animals.stream()
                .filter(animal -> "Herbivore".equals(animal.getFood()))
                .collect(Collectors.toList());
        transferHerbivoreAnimals(herbivoreAnimals, availableEnclosures.get(0));
            System.out.println("Herbivores transferred to first enclosure");

        // Transfer carnivore animals to other enclosures
        List<Animal> carnivoreAnimals = animals.stream()
                .filter(animal -> "Carnivore".equals(animal.getFood()))
                .collect(Collectors.toList());
        boolean carnivoreTransferSuccessful = transferCarnivoreAnimals(carnivoreAnimals, availableEnclosures);

        if (!carnivoreTransferSuccessful) {
                // Rollback changes and give an error message
                for (Animal animal : animals) {
                    animal.setEnclosure(null);
                    animalRepository.save(animal);
                }
                System.out.println("NOT enough enclosure space for animals");
                return false;
            }
        return true;
    }

    private void transferHerbivoreAnimals(List<Animal> animals, Enclosure enclosure){
        for (Animal animal : animals) {
            transferAnimalToEnclosure(animal, enclosure);
        }
    }

    private boolean transferCarnivoreAnimals(List<Animal> animals, List<Enclosure> enclosures) {
        Map<Enclosure, Integer> enclosureAnimalCountMap = new HashMap<>();

        for (Animal animal : animals) {
            boolean transferred = false;

            // Check if there are any empty enclosures without other animals
            for (Enclosure enclosure : enclosures) {
                if (!enclosureAnimalCountMap.containsKey(enclosure) && enclosure.getAssignedAnimals().isEmpty()) {
                    transferAnimalToEnclosure(animal, enclosure);
                    transferred = true;
                    enclosureAnimalCountMap.put(enclosure, 1);
                    break;
                }
            }

            if (!transferred) {
                // Check if there are any enclosures with only one assignment
                for (Enclosure enclosure : enclosures) {
                    if (enclosureAnimalCountMap.containsKey(enclosure) && enclosureAnimalCountMap.get(enclosure) == 1) {
                        transferAnimalToEnclosure(animal, enclosure);
                        Integer count = enclosureAnimalCountMap.getOrDefault(enclosure, 0);
                        enclosureAnimalCountMap.put(enclosure, count + 1);
                        transferred = true;
                        break;
                    }
                }
                if (!transferred) {
                    return false;
                }
            }
        }
        return true;
    }

    private void transferAnimalToEnclosure(Animal animal, Enclosure enclosure) {
        animal.setEnclosure(enclosure);
        animalRepository.save(animal);
    }
}
