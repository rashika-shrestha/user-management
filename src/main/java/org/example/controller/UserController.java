package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class UserController {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        String decision = "";
        do {
            String choice = JOptionPane.showInputDialog("Enter your choice of DB operation: save|update|delete|get|list");
            switch (choice) {
                case "save":
                    User user = getUser("save");

                    int saved = userService.saveUser(user);
                    if (saved >= 1) {
                        JOptionPane.showMessageDialog(null, "User is saved successfully!!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in db.");
                    }
                    break;
                case "update":
                    user = getUser("update");
                    int updated = userService.updateUser(user);
                    if (updated >= 1) {
                        JOptionPane.showMessageDialog(null, "User is updated successfully!!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in db.");
                    }
                    break;
                case "delete":
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));
                    int deleted = userService.deleteUser(id);
                    if (deleted >= 1) {
                        JOptionPane.showMessageDialog(null, "User is deleted successfully!!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in db.");
                    }
                    break;
                case "list":
                    List<User> userList = userService.getAllUser();
                    for (User u : userList) {
                        System.out.println("User id is: " + u.getId());
                        System.out.println("Username is: " + u.getUsername());
                        System.out.println("Password is: " + u.getPassword());
                        System.out.println("Salary is: " + u.getSalary());
                        System.out.println("Mobile no. is: " + u.getMobileNo());
                        System.out.println("is user active?" + u.isActive());
                        System.out.println("DOB is: " + u.getDob());
                        System.out.println("==============================");
                    }
                    break;
                case "get":
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));
                    User u = userService.getUserById(id);
                    System.out.println("User id is: " + u.getId());
                    System.out.println("Username is: " + u.getUsername());
                    System.out.println("Password is: " + u.getPassword());
                    System.out.println("Salary is: " + u.getSalary());
                    System.out.println("Mobile no. is: " + u.getMobileNo());
                    System.out.println("is user active?" + u.isActive());
                    System.out.println("DOB is: " + u.getDob());
                    System.out.println("==============================");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wrong choice!!!");
                    break;
            }
            decision = JOptionPane.showInputDialog("do you want to continue?Enter Yes|No");
        } while (decision.equalsIgnoreCase("Yes"));
        JOptionPane.showMessageDialog(null, "Bye Bye!!!See You Next Time....");
    }

    public static User getUser(String type) {
        User user = new User();
        if (type.equals("update")) {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));
            user.setId(id);
        }
        String username = JOptionPane.showInputDialog("Enter username");
        String password = JOptionPane.showInputDialog("Enter password");
        double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter salary"));
        long mobileNo = Long.parseLong(JOptionPane.showInputDialog("Enter mobile number"));
        LocalDate dob = LocalDate.now();
        boolean isActive = Boolean.getBoolean(JOptionPane.showInputDialog("is user active?"));
        user.setUsername(username);
        user.setPassword(password);
        user.setSalary(salary);
        user.setDob(dob);
        user.setActive(isActive);
        user.setMobileNo(mobileNo);
        return user;
    }

}
