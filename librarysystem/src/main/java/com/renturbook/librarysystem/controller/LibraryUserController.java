package com.renturbook.librarysystem.controller;

import com.renturbook.librarysystem.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.renturbook.librarysystem.service.LibraryUserServiceImpl;
import com.renturbook.librarysystem.model.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Resource(name = "libraryUserService")
@RequestMapping(path = "api/v1/user")
public class LibraryUserController {

    private final LibraryUserServiceImpl userService;

    @Autowired
    public LibraryUserController(LibraryUserServiceImpl uService ){
        this.userService = uService;
    }

    @GetMapping
    public List<LibraryUser> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/username/")
    public LibraryUser getByUsername(@RequestParam String username) {
        return userService.getByUsername(username );
    }

    @GetMapping("/id/")
    @ResponseBody
    public LibraryUser getById( @RequestParam Long userId ) {
        return userService.getById(userId );
    }

    @GetMapping("/favid/")
    public List<Long> getFavIdById( @RequestParam Long userId ) {
        return userService.getFavIdById(userId);
    }

    @GetMapping("/favbook/")
    public List<Book> getFavBookById( @RequestParam Long userId) { return userService.getFavBookById(userId); }

    @GetMapping("/login/")
    public LibraryUser login(@RequestParam String username, @RequestParam String password ) {

        return userService.login(username, password).get();
    }

    @GetMapping("/checkusername/")
    public boolean checkUserName(@RequestParam String username) {
        return userService.checkUsername(username);
    }

    @PostMapping
    public void registerNewUser(@RequestBody LibraryUser newUser) {
        userService.addNewUser(newUser);
    }

    @PostMapping("/test")
    public void addTestUser() {
        LibraryUser temp = new LibraryUser("username", "password", "Fname", "Lname", "0813942837", new ArrayList<>());
        userService.addNewUser( temp );
        LibraryUser temp2 = new LibraryUser("username2", "password2", "First2", "Last2", "0913834921",new ArrayList<>());
        LibraryUser temp3 = new LibraryUser("admin", "admin","Admin","Account", "",new ArrayList<>());
        System.out.println("######################\n" + temp);
        System.out.println("######################\n" + temp2);
        temp3.setUserType("admin");
        userService.addNewUser( temp2);
        userService.addNewUser( temp3);
    }

    @PostMapping("/addadmin")
    public void addAdmin() {
        LibraryUser temp3 = new LibraryUser("admin", "admin","Admin","Account","",new ArrayList<>());
        temp3.setUserType("admin");
        userService.addNewUser( temp3);
    }

    @GetMapping("/addfav/")
    public void addFavBookById( @RequestParam Long userId, @RequestParam Long bookId) {
        LibraryUser user = getById(userId);
        if ( user.getFavoriteBooks().contains(bookId) ) {
            return;
        }
        userService.addFavBookById( userId, bookId );
    }

    @GetMapping("/removefav/")
    public void removeFavBookById( @RequestParam Long userId, @RequestParam Long bookId) {
        LibraryUser user = getById(userId);
        System.out.println("######################\n" +user.getFavoriteBooks());
        if ( user.getFavoriteBooks().contains(bookId) ) {
            userService.removeFavBookById( userId, bookId );
        }
    }
}