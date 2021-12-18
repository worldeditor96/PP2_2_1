package hiber;

import hiber.Util.Util;
import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService=context.getBean(CarService.class);


      Car car1=new Car("BMW", 7);
      Car car2=new Car("Mercedes", 600);
      Car car3= new Car("Ferrari", 40);
      Car car4= new Car("VW", 4578);

      carService.addCar(car1);
      carService.addCar(car2);
      carService.addCar(car3);
      carService.addCar(car4);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserCarByModelSeries("BMW", 7));

   }

}
