package com.example.springlab2.controllers;

import com.example.grpc.BarrierOpenServiceGrpc;
import com.example.grpc.BarrierServiceGrpc;
import com.example.grpc.BarrierServiceOuterClass;
import com.example.springlab2.car.CarTable;
import com.example.springlab2.car.CarTableService;
import com.example.springlab2.emptyplaces.EmptyPlaces;
import com.example.springlab2.emptyplaces.EmptyPlacesService;
import com.example.springlab2.user.User;
import com.example.springlab2.user.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
    @Autowired private com.example.springlab2.user.UserService UserService;
    @Autowired private CarTableService CarService;
    @Autowired private EmptyPlacesService EPService;

    @GetMapping("/users") //отображение таблицы пользователей для админа
    public String showUserList(Model model){
        List<User> listUsers = UserService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/users/new") // создание пользователя для админа ПОЧИНИТЬ!!!
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("car", new CarTable());
        model.addAttribute("listCarTables", new User());
        model.addAttribute("pageTitle", "Add new user");
        return "user_form";
    }
    @PostMapping("/users/save") // сохранение пользователя после изменения
    public String saveUser(User user, CarTable carTable){
        CarService.save1(carTable);
        UserService.save(user);
        return "redirect:/users";
    }
    @PostMapping("/users/save2") // сохранение для пользователя??? ВОЗМОЖНО ИМЕННО ТУТ НАДО ДОБАВИТЬ SAVE(CAR) ЧТОБЫ НЕ УДАЛЯЛАСЬ СТРОЧКА ИЗ БД
    public String saveUser2(User user, CarTable car, EmptyPlaces emptyPlaces, Model model){
        UserService.save(user);
        CarService.save1(car);
        EPService.save(emptyPlaces);
        model.addAttribute("user", user);
        model.addAttribute("car", car);
        model.addAttribute("eps", emptyPlaces);
        return "welcome";
    }
    @GetMapping("/users/edit/{id}") // изменение инфы о человеке для админа
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes re) {
        try {
           User user = UserService.get(id);
           CarTable car = CarService.get_by_user_ID(id);
            System.out.println(user);
            System.out.println(car);
           model.addAttribute("user", user);
            model.addAttribute("car", car);
            model.addAttribute("pageTitle", "Edit user (ID:" + id + ")");
            return "user_form";
        }
        catch(UserNotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/edit2/{id}") //изменение инфы для человека (сейчас кнопка edit включена)
    public String showEditForm2(@PathVariable("id") Integer id, Model model, RedirectAttributes re) {
        try {
            User user = UserService.get(id);
            CarTable car = CarService.get_by_user_ID(id);
            EmptyPlaces eps = EPService.get(1);
            model.addAttribute("user", user);
            model.addAttribute("car", car);
            model.addAttribute("eps", eps);
            model.addAttribute("pageTitle", "Edit user (ID:" + id + ")");
            return "user_form2";
        }
        catch(UserNotFoundException e){
            re.addFlashAttribute("message", e.getMessage());
            return "redirect:/check";
        }
    }
    @PostMapping("/users/edit2") // удаляет пользователя и сохраняет его обновленную версию ДОБАВЬ СЮДА УДАЛЕНИЕ И СОХРАНЕНИЕ CARTABLE
    public String editPage2(User user, CarTable car, Model model) throws UserNotFoundException{
        UserService.delete2(UserService.get_by_ID((user.getIdUser())));
        UserService.save(user);
        CarService.delete2(CarService.get_by_user_ID(user.getIdUser()));
        CarService.save1(car);
        model.addAttribute("user", user);
        model.addAttribute("car", car);
        return "redirect:/index.html";
    }
    @PostMapping("user/edit")  // удаляет пользователя и сохраняет его обновленную версию
    public String editPage(User user,CarTable car, Model model) throws UserNotFoundException {
        UserService.delete2(UserService.get_by_ID(user.getIdUser()));
        UserService.save(user);
        CarService.delete2(CarService.get_by_user_ID(user.getIdUser()));
        CarService.save1(car);
        model.addAttribute("user",user);
        model.addAttribute("car", car);
        return "home_page";
    }
   @GetMapping("/users/delete/{id}") //удаление для админа
    public String deleteUser(@PathVariable("id")  Integer id, Model model, RedirectAttributes re) throws UserNotFoundException {
        try {
            UserService.delete(id);
            model.addAttribute("listUsers", UserService.listAll());
            return "users";
        }
        catch (UserNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());

        }
       return "redirect:/users";
    }

    @PostMapping("/users/locking") //блокирование шлагбаума
    public String checkGate(@RequestBody String s) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(s);
        int id = jsonNode.get("id").asInt();
        System.out.println(id);
        Boolean newValue = jsonNode.get("newValue").asBoolean();
        System.out.println(newValue);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8989")
                .usePlaintext()
                .build();

        BarrierServiceGrpc.BarrierServiceBlockingStub stub =
                BarrierServiceGrpc.newBlockingStub(channel);
        BarrierServiceOuterClass.BarrierRequest request = BarrierServiceOuterClass.BarrierRequest
                .newBuilder().setBarrierRequest(newValue).build();
        BarrierServiceOuterClass.BarrierResponse response = stub.greeting2(request);

        channel.shutdownNow();
        return "users";
    }
    @PostMapping("/users/open")
    public String openGate(@RequestBody String s) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(s);
        int id = jsonNode.get("id").asInt();
        System.out.println(id);
        Boolean newValue = jsonNode.get("newValue").asBoolean();
        System.out.println(newValue);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8989")
                .usePlaintext()
                .build();

        BarrierOpenServiceGrpc.BarrierOpenServiceBlockingStub stub =
                BarrierOpenServiceGrpc.newBlockingStub(channel);
        BarrierServiceOuterClass.BarrierOpenRequest request = BarrierServiceOuterClass.BarrierOpenRequest
                .newBuilder().setBarrierOpenRequest(newValue).build();
        BarrierServiceOuterClass.BarrierOpenResponse response = stub.greeting3(request);

        channel.shutdownNow();
        return "users";
    }
}
