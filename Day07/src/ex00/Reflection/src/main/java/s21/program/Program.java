package s21.program;

import s21.classes.*;
import s21.reflection.Handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Handler handler = chooseClass();
        handler.printFields();
        handler.printMethods();
        handler.createObject();
        handler.setField();
        handler.executeMethod();
    }


    public static Handler chooseClass() throws ClassNotFoundException {
        Class<User> userClass = User.class;
        Class<Car> carClass = Car.class;
        StringBuilder responce = new StringBuilder();
        responce.append("Classes:\n")
                .append(userClass.getSimpleName() + "\n")
                .append(carClass.getSimpleName() + "\n")
                .append("---------------------\n")
                .append("Enter class name:");
        System.out.println(responce);
        String request = getRequest();
        Handler handler = null;
        if (request.equals("User")) {
            handler = new Handler(User.class);
        } else if (request.equals("Car")) {
            handler = new Handler(Car.class);
        } else {
            throw new ClassNotFoundException("Incorrect class name");
        }
        return handler;
    }
    public static String getRequest() {
        return new Scanner(System.in).nextLine();
    }
}
