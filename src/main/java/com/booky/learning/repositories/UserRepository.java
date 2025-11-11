package com.booky.learning.repositories;

import com.booky.learning.models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<UserModel> users = new ArrayList<>(
            List.of(
                    new UserModel("1","Book"),
                    new UserModel("2","Alice")
            )
    );

    public List<UserModel> getUsers() {
        return users;
    }
    public void addUser(UserModel user) {
        users.add(user);
    }
    public void removeUser(UserModel user) {
        users.remove(user);
    }
    public void editUser(UserModel user) {
        for(int index = 0; index < users.size(); index++){
            if(users.get(index).getId().equals(user.getId())){
                users.set(index, user);
                return;
            }
        }
    }
}
