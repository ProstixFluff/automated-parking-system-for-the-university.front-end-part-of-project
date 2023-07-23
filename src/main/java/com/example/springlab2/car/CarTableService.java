package com.example.springlab2.car;

import com.example.springlab2.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarTableService {
    @Autowired public CarTableRepository repo;

    public List<CarTable> listAll(){
        return (List<CarTable>) repo.findAll();
    }


    public void save1(CarTable cartable) {
        repo.save(cartable);

    }
/*    public List<CarTable> FindPlateByID(int id_user){
        List<CarTable> result = repo.getPlateByID(id_user);
        return result;
    };*/


    public CarTable get(Integer id) throws UserNotFoundException {
        Optional<CarTable> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID:" + id);
    }
    public CarTable get_by_user_ID(int id){
        CarTable car = new CarTable();
        List<CarTable> cars = listAll();
        for(int i =0;i<cars.size();i++){
            System.out.println("TEST:");
         if(cars.get(i).getIdUser()==id){
             car=cars.get(i);

         }
         String test = cars.get(i).getPlateref();
            System.out.println(test);
        }
        return car;
    }

    public CarTable get_by_ID(int id)
    {
        CarTable cartable = new CarTable();
        List<CarTable> cars = listAll();
        for (int i=0;i<cars.size();i++)
        {
            if (cars.get(i).getIdcarTable()==id)
            {
                cartable= cars.get(i);
            }
        }
        return cartable;
    }
    public void delete2(CarTable cartable){
        repo.delete(cartable);
    }
    public void delete(Integer id) throws UserNotFoundException {
/*        Long count = repo.countById(id);
        if(count == null || count ==0){
            throw new UserNotFoundException("Could not find any users with ID:" + id);
        }*/
        repo.deleteById(id);
    }
}
