package s21.reflection;


import s21.classes.*;
import s21.program.Program;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    private final Class<?> handleClass;
    private Object newObject;

    public Handler(Class<?> handleClass) {
        this.handleClass = handleClass;
    }

    public void createObject() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Field[] fields = handleClass.getDeclaredFields();
        Constructor<?>[] constructors = handleClass.getConstructors();
        List<Object> fieldsList = new ArrayList<>();
        for (Field field : fields) {
            System.out.println(field.getName() + ":");
            String input = Program.getRequest();
            Class<?> fieldType = field.getType();
            if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
                fieldsList.add(Integer.parseInt(input));
            } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
                fieldsList.add(Double.parseDouble(input));
            } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
                fieldsList.add(Float.parseFloat(input));
            } else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
                fieldsList.add(Boolean.parseBoolean(input));
            } else if (fieldType.equals(String.class)) {
                fieldsList.add(input);
            } else {
                throw new IllegalArgumentException("Incorrect type");
            }
        }
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() != handleClass.getDeclaredFields().length) {
                continue;
            }
            newObject = constructor.newInstance(fieldsList.toArray());
            System.out.println("Object created: " + newObject);
            System.out.println("---------------------");
        }
    }

    public void executeMethod() throws InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method for call:");
        Method[] methods = handleClass.getDeclaredMethods();
        String reqMethod = Program.getRequest();
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals(reqMethod)) { method = m; }
        }
        if (method == null) {
            throw new IllegalArgumentException("Method not found");
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        List<Object> paramsList = new ArrayList<>();
        for (Class<?> param : parameterTypes) {
            System.out.println("Enter " + param.getSimpleName() + " value:");
            if (param.equals(int.class) || param.equals(Integer.class)) {
                paramsList.add(Integer.parseInt(Program.getRequest()));
            } else if (param.equals(double.class) || param.equals(Double.class)) {
                paramsList.add(Double.parseDouble(Program.getRequest()));
            } else if (param.equals(long.class) || param.equals(Long.class)) {
                paramsList.add(Float.parseFloat(Program.getRequest()));
            } else if (param.equals(boolean.class) || param.equals(Boolean.class)) {
                paramsList.add(Boolean.parseBoolean(Program.getRequest()));
            } else if (param.equals(String.class)) {
                paramsList.add(Program.getRequest());
            } else {
                throw new IllegalArgumentException("Incorrect type");
            }
        }
        Object res = method.invoke(newObject, paramsList.toArray());
        System.out.println("Method returned: " + res);
    }

    public void setField() throws IllegalAccessException {
        Field[] fields = newObject.getClass().getDeclaredFields();
        System.out.println("Enter name of the field for changing:");
        String input = Program.getRequest();
        for (Field field : fields) {
            if (field.getName().equals(input)) {
                field.setAccessible(true);
                System.out.println("Enter " + field.getType().getSimpleName() + " value:");
                Class<?> fieldType = field.getType();
                if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
                    field.setInt(newObject, Integer.parseInt(Program.getRequest()));
                } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
                    field.setDouble(newObject, Double.parseDouble(Program.getRequest()));
                } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
                    field.setLong(newObject, Long.parseLong(Program.getRequest()));
                } else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
                    field.setBoolean(newObject, Boolean.parseBoolean(Program.getRequest()));
                } else if (fieldType.equals(String.class)) {
                    field.set(newObject, Program.getRequest());
                } else {
                    throw new IllegalArgumentException("Incorrect field type");
                }
            }
        }
        System.out.println("Object updated: " + newObject);
        System.out.println("---------------------");
    }

    public void printFields() {
        System.out.println("---------------------");
        Field[] fields = handleClass.getDeclaredFields();
        System.out.println("fields:");
        for(Field f : fields) {
            System.out.print("\t" + f.getType().getSimpleName());
            System.out.println(" " + f.getName());
        }
    }

    public void printMethods() {
        Method[] methods = handleClass.getDeclaredMethods();
        StringBuilder str = new StringBuilder();
        str.append("methods:\n");
        for (Method m : methods) {
            if (m.getName().equals("toString")) { continue; }
            str.append("\t" + m.getReturnType().getName() +
                    " " + m.getName() + "(");
            Class<?>[] params = m.getParameterTypes();
            int maxLength = params.length - 1;
            if (maxLength >= 0) {
                for (int i = 0; i < maxLength; ++i) {
                    str.append(params[i].getSimpleName() + ", ");
                }
                str.append(params[0].getSimpleName());
            }
            System.out.println(str.append(")"));
            str.setLength(0);
        }
        System.out.println("---------------------");
    }
}
