package com.example.springlab2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired public UserRepository repo;

    public List<User> listAll(){ // вывод всего
        return (List<User>) repo.findAll();
    }


    public void save(User user) { //сохранение
        repo.save(user);
    }
/*    public List<User> FindPlateByID(int id_user){
        List<User> result = repo.getPlateByID(id_user);
        return result;
    };*/
    public User get(Integer id) throws UserNotFoundException { //получить пользователя
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID:" + id);
    }

    public User get_by_ID(int id) //получение пользователя по id
    {
        User user=new User();
        List<User> users= listAll();
        for (int i=0;i<users.size();i++)
        {
            if (users.get(i).getIdUser()==id)
            {
                user= users.get(i);
            }
        }
        return user;
    }
    public void delete2(User user){ //удаление пользователя
        repo.delete(user);
    }
    public void delete(Integer id) throws UserNotFoundException { //удаление пользователя по id
/*        Long count = repo.countById(id);
        if(count == null || count ==0){
            throw new UserNotFoundException("Could not find any users with ID:" + id);
        }*/
        repo.deleteById(id);
    }
}
