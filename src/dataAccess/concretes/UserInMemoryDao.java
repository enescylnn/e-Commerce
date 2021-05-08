package dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserInMemoryDao implements UserDao {
	
	private List<User> users;

    private static int lastId;

    public UserInMemoryDao(){
        this.users = new ArrayList<>();
        lastId = 0;
    }
    @Override
    public void add(User user) {
        this.users.add(user);
        System.out.println("User added to database.");
        lastId++;
    }

    @Override
    public void update(User user) {
        System.out.println("Update is currently disabled.");
    }

    @Override
    public void delete(User user) {
        int userToDelete = this.users.indexOf(user);
        this.users.remove(userToDelete);
        System.out.println("User deleted from database.");
        lastId--;
    }

    @Override
    public User get(int id) {
        /*
        User userToReturn = null;
        for(User user: this.users){
            if(user.getId() == id){
                userToReturn = user;
                break;
            }
        }*/
        User userToReturn = this.users.stream()
                .filter((user) -> user.getId() == id)
                .findFirst()
                .orElse(null);
        return userToReturn;
    }

    @Override
    public User getByMail(String mail) {

        User userToReturn = null;
        for(User user: this.users){
            if(user.getMail().equals(mail)){
                userToReturn = user;
                break;
            }
        }

        /*User userToReturn = this.users.stream()
                .filter((user) -> user.getMail().equals(mail))
                .findFirst()
                .orElse(null);*/
        return userToReturn;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public boolean isVerified(User user) {
        User userToCheck = get(user.getId());
        return userToCheck.isVerified() ?
                true : false;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        UserInMemoryDao.lastId = lastId;
    }
}
