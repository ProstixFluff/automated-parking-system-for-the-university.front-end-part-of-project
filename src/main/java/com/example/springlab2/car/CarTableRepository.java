package com.example.springlab2.car;

import com.example.springlab2.car.CarTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarTableRepository extends CrudRepository<CarTable, Integer> {



}
