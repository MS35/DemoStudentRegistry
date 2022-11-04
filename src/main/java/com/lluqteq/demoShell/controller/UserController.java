package com.lluqteq.demoShell.controller;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.lluqteq.demoShell.exceptions.*;
import com.lluqteq.demoShell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.lluqteq.demoShell.domain.User;

@Controller("userController")
@SessionScope
public class UserController {

    private static final String PASSWORD_REQUIREMENTS = "a digit must occur at least once\n"
            + "a lower case letter must occur at least once\n" + "an upper case letter must occur at least once\n"
            + "a special character must occur at least once\n" + "no whitespace allowed\n"
            + "at least 8 characters are needed";

    private static final String INDEX = "index.xhtml";

    public static final String LANDING_PAGE = "landingPage.xhtml";

    public static final String ADMIN_ROLE = "ADMIN";

    private User user = new User();

    private UserService service;

    private String emailAddress;

    private String password;

    private String confirmPassword;

    private String name;

    private String surname;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    public String login() {

        try {
            user = service.findUser(emailAddress, password);


            return LANDING_PAGE;

        } catch (NoSuchUserException e) {

            logError("User Not Found Or Is Invalid");

            return "";

        } catch (IncorrectPasswordException e) {

            String message = "Incorrect Password";

            logError(message);

            return "";
        }
    }

    private void logError(String message) {
        printFacesMessage(message);
    }

    public String register() {
        user.setEmailAddress(emailAddress);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        try {

            if (!password.equals(confirmPassword)) {
                logError("Passwords Have To Match");
                return "";
            }

            service.save(user);
            printFacesMessage("Registration Complete");
            return INDEX;

        } catch (DuplicateEmailAddressException e) {
            logError("Email-Address Already Already In Use");
            return "";
        } catch (WeakPasswordException e) {
            logError(PASSWORD_REQUIREMENTS);
            return "";
        } catch (InvalidEmailAddressException e) {
            logError("Invalid Email-Address");
            return "";
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername(){
        return user.getName().toUpperCase();
    }

    private void printFacesMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message, message));
    }

    public String logout() {
        reset();
        return INDEX;
    }

    public void reset() {
        user = new User();
        setEmailAddress("");
        setPassword("");
    }


    public boolean isAdmin(){
        return user.getRole().equalsIgnoreCase(ADMIN_ROLE);
    }

}
