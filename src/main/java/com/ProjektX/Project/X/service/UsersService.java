package com.ProjektX.Project.X.service;

import com.ProjektX.Project.X.model.UsersModel;
import com.ProjektX.Project.X.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email){
        if(login == null || password == null){
            return null;
        }else{
            UsersModel new_user = new UsersModel();
            new_user.setLogin(login);
            new_user.setPassword(password);
            new_user.setEmail(email);
            return new_user;
        }
    }

    public UsersModel authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }
}
