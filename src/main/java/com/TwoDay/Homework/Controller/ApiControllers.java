package com.TwoDay.Homework.Controller;

import com.TwoDay.Homework.Models.Animal;
import com.TwoDay.Homework.Models.Enclosure;
import com.TwoDay.Homework.Services.AnimalService;
import com.TwoDay.Homework.Services.AnimalTransferService;
import com.TwoDay.Homework.Services.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private EnclosureService enclosureService;

    @Autowired
    private AnimalTransferService animalTransferService;

    @GetMapping(value = "/")
    public String getPage(){
        return "Zoo management system, please use REST";
    }

    @GetMapping("/animals")
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/enclosures")
    public List<Enclosure> getAllEnclosures() {
        return enclosureService.getAllEnclosures();
    }

    @PostMapping("/singleAnimalAdd")
    public ResponseEntity<String> addAnimal(@RequestBody Animal animal) {
        animalService.addSingleAnimal(animal);
        return ResponseEntity.ok("Single Animal added successfully");
    }

    @PostMapping("/allAnimalsAdd")
    public ResponseEntity<String> addAnimal(@RequestBody List<Animal> animals) {
        animalService.addAllAnimals(animals);
        return ResponseEntity.ok("All Animals added successfully");
    }

    @PostMapping("/singleEnclosureAdd")
    public ResponseEntity<String> addEnclosure(@RequestBody Enclosure enclosure) {
        enclosureService.addSingleEnclosure(enclosure);
        return ResponseEntity.ok("Single Enclosure added successfully");
    }

    @PostMapping("/enclosuresAdd")
    public ResponseEntity<String> addEnclosure(@RequestBody List<Enclosure> enclosures) {
        enclosureService.addEnclosures(enclosures);
        return ResponseEntity.ok("All Enclosures added successfully");
    }

    // Assing database animals to database enclosures
    @PostMapping("/transferAnimals")
    public ResponseEntity<String> transferAnimalsToNewZoo() {
        List<Animal> animals = animalService.getAllAnimals();
        List<Enclosure> enclosures = enclosureService.getAllEnclosures();

        if(!animalTransferService.transferAnimalsToZoo(animals, enclosures)){
            return ResponseEntity.ok("Animals not transfered, due to lack of enclosure space");
        }

        return ResponseEntity.ok("Animal transfer completed successfully");
    }

    // Delete animal by ID
    @DeleteMapping(value = "animalDelete/{id}")
    public String deleteAnimal(@PathVariable long id){
        boolean isDeleted = animalService.deleteAnimal(id);
        if(isDeleted){
            return "Animal with ID " + id + " deleted successfully.";
        } else {
            return "Delete FAILED";
        }
    }

    // Delete enclosure by ID
    @DeleteMapping(value = "enclosureDelete/{id}")
    public String deleteEnclosure(@PathVariable long id){
        boolean isDeleted = enclosureService.deleteEnclosure(id);
        if(isDeleted){
            return "Enclosure with ID " + id + " deleted successfully.";
        } else {
            return "Enclosure delete FAILED";
        }
    }
}
