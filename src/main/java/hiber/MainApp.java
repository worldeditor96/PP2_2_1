package hiber;

import hiber.Util.Util;
import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


/*
+0 Обновить все зависимости в pom
+/-1.1 Создайте соединение к своей базе данных
+1.2 Создайте схему БД.
+При первом подключении к БД, обратите внимание что database в скачанном шаблоне называется spring_hiber
+Вам нужно настроить файл db.proper.. (так как там подключение к другой базе данных со своим логином и паролем),
 +обратите внимание к названию БД которой подключается система
+ Изменить в property hibernate.hbm2ddl.auto=auto на hibernate.hbm2ddl.auto=create-drop
+1.3 3. Далее находим метод Main и запускаем у вас должно все скомлироваться (создается таблица users
 и в нее записываются данные, если вылетает ошибка, изучаем ошибку и как ее решать)
+Запустите приложение. Проверьте, что оно полностью работает.
2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
 Теперь смотрим на деррикторию model(там у нас класс которые в дальнейшем реализуется в таблице),
 значит наш класс Car тоже должен быть там, создаем обычный POJO класс
-/+?3 Добавьте этот класс в настройки hibernate.
-/+3.1 ПОвторяем как подключает класс к таблице
3.2 какие аннотации проставлять через Hibernate
3.3 Смотрим как настраивается OneToOne подключение (в инете много информации)  @Создайте несколько пользователей с машинами@
В классе User cоздаем приватное поле типа Car. А над ним прописываем @OneToOne - которая позволит нам связать сущность User с
 сущностью Car. Также нужно добавить колонку в таблице для юзеров c помощью @JoinColumn (можно прямо под @OneToOne прописать).
И не забудьте добавить наш созданный класс Car в метод setAnnotatedClasses() , который находится в AppConfig.java ,

4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.


7. Мы помним зачем нуженн паттерн DAO - фактически это прослойка где прописывается вся логика подключение к БД, значит нам надо туда)
8. Нам не надо создавать новый CarDAO как вы можете подумать, я тоже так думал и даже реализовал))) Читаем статью
https://www.baeldung.com/jpa-queries
http://java-online.ru/hibernate-hql.xhtml
НОВЫХ классов создавать ненадо! Это самое сложное из всего задание по факту
9 . setAnnotatedClasse через запятую указать Car.class
10. Конечно реалзовать раздел сервис
11. И итог main


 */

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 7)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Mercedes", 600)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Ferrari", 40)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",new Car("VW", 4578)));

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
