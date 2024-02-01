package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("model1", 1);
      Car car2 = new Car("model2", 2);
      Car car3 = new Car("model3", 3);
      Car car4 = new Car("model4", 4);

      User vlad = new User("vlad", "Lastname1", "user1@mail.ru", car1);
      User serega = new User("serega", "Lastname2", "user2@mail.ru", car2);
      User pasha = new User("pasha", "Lastname3", "user3@mail.ru", car3);
      User danya = new User("danya", "Lastname4", "user4@mail.ru", car4);


      car1.setUser(vlad);
      car2.setUser(serega);
      car3.setUser(pasha);
      car4.setUser(danya);

      userService.add(vlad);
      userService.add(serega);
      userService.add(pasha);
      userService.add(danya);

      CarService carService = context.getBean(CarService.class);
      User found = carService.userByCar("model2", 2);
      System.out.println("Найденный пользователь: " + found.getFirstName() + " " + found.getLastName());


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      context.close();
   }
}
