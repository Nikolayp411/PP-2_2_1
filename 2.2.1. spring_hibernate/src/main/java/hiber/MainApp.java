package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Ford", 911);
      Car car2 = new Car("Lada", 2101);
      Car car3 = new Car("Peugeot", 308);

      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);

      userService.add(new User("Emmaluel", "Macron", "user1@mail.ru", car1));
      userService.add(new User("Francois", "Hollande", "user2@mail.ru", car2));
      userService.add(new User("Nicolas", "Sarkozy", "user3@mail.ru", car3));
      userService.add(new User("Jacques", "Chirac", "user4@mail.ru", null));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel());
         }
         System.out.println();
      }

      context.close();
   }
}
