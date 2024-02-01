package hiber.dao;
import hiber.model.Car;
import hiber.model.User;

public interface CarDao {
    void add(Car car);

    User userByCar(String model, int series);
}
