package hiber.dao;

import hiber.model.User;
import org.springframework.ui.Model;
//import hiber.model.Car;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   User getUserCarByModelSeries (String model, int series);
}
