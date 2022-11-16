package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            userService.createUsersTable();
            userService.saveUser("Maxim", "Lobkov", (byte) 43);
            userService.saveUser("Xin", "Minson", (byte) 68);
            userService.saveUser("Dyrius", "Memnov", (byte) 25);
            userService.saveUser("Denis", "Egorov", (byte) 88);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } catch (Exception e) {
            System.out.println("bad :(");
        }
    }
}
