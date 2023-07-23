package com.example.springlab2.controllers;


import com.example.springlab2.car.CarTable;
import com.example.springlab2.car.CarTableService;
import com.example.springlab2.emptyplaces.EmptyPlaces;
import com.example.springlab2.emptyplaces.EmptyPlacesService;
import com.example.springlab2.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    int FreeSpaces = 32; // затычка для отображения количества мест
    @Autowired
    private UserService usrsrvs; // нужно для использования методов из UserService
    @Autowired
    private CarTableService crtblsrvs; // нужно для использования методов из CarTableService
    @Autowired
    private EmptyPlacesService epservice; //

    @GetMapping("") //стартовая
    public String showHomePage(Model model){
        model.addAttribute("new_User_user1", new User());
        return "index";
    }
    @GetMapping("/index.html")  // нужно для возвращения к хоум странице
    public String showIndex(Model model){
        model.addAttribute("new_User_user1", new User());
        return "index";
    }
    @GetMapping("/register.html") // регистрация
    public String test2(Model model){
        model.addAttribute("new_User_user1",new User());
        model.addAttribute("new_CarTable1", new CarTable());
        return "register";
    }

    @GetMapping("/welcome.html") // вход
    public String test3(){
        return "welcome";
    }
    @PostMapping("/check") // проверка логина и пароля + роли при входе
    public String check1(Model model,User user) throws UserNotFoundException {
        List<User> users = usrsrvs.listAll();
        List<CarTable> cartables = crtblsrvs.listAll();
        List<EmptyPlaces> eps = epservice.listAll();
        for(int i = 0; i<users.size();i++){
            if (users.get(i).getEmail().equals(user.getEmail()) && (users.get(i).getPassword().equals(user.getPassword()))) {
                if(users.get(i).getRole().equals("admin")){
                    model.addAttribute("listUsers", users);
                    model.addAttribute("listCarTables", cartables);
                  //  model.addAttribute("")
                    System.out.println("admin panel out");
                    return "users";
                }
                else {
                    model.addAttribute("listUsers", users.get(i));
                    for(int j=0;j<cartables.size();j++) {
                        if (users.get(i).getIdUser() == cartables.get(j).getIdUser()) {
                            model.addAttribute("listCarTables", cartables.get(j));
                            model.addAttribute("listEmptyPlaces", eps.get(0));
                            model.addAttribute("listEmptyPlaces2", eps.get(1));
                            break;

                        }
                    }
                    System.out.println("user panel out");
                    return "welcome";
                }
            }
        }
        return "index";
    }
    @PostMapping("/add") //добавление нового пользователя при регистрации
    public String add1(User user, CarTable carTable){
        int size = 0;
        int size2 = 0;
        List<User> users = usrsrvs.listAll();
        List<CarTable> carTables = crtblsrvs.listAll();
        for(int j = 0; j<=users.size(); j++){
            size = size +1;
        }
        int var = size+1;
        user.setIdUser(var);
        for(int z = 0; z<= carTables.size(); z++){
            size2 = size2 +1;
        }
        int var2 = size2+1;
        carTable.setIdcarTable(var2);
        for(int i = 0; i<users.size();i++){
            if (users.get(i).getName().equals(user.getName()) || (users.get(i).getPassword().equals(user.getPassword()))||(users.get(i).getEmail().equals(user.getEmail()))) {
                return "register";
            }
        }
        int var_user_id;
        usrsrvs.save(user);
        var_user_id = user.getIdUser();
        carTable.setIdUser(var_user_id);
        crtblsrvs.save1(carTable);
        return "index";
    }
}
