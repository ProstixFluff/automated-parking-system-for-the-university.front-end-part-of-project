package com.example.springlab2.emptyplaces;

import com.example.springlab2.car.CarTable;
import com.example.springlab2.car.CarTableRepository;
import com.example.springlab2.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmptyPlacesService {
    @Autowired public EmptyPlacesRepository repo;

    public List<EmptyPlaces> listAll(){
        return (List<EmptyPlaces>) repo.findAll();
    }

    public void save(EmptyPlaces emptyplaces) {repo.save(emptyplaces);}

    public void delete2(EmptyPlaces emptyplaecs){
        repo.delete(emptyplaecs);
    }

    public EmptyPlaces get(Integer id) throws UserNotFoundException {
        Optional<EmptyPlaces> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any places info");
    }
}