package facade;

import entity.User;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "userEao")
public class UserEaoImpl extends AbstractFacade implements UserEao {
    @Override
    public User findUserByNameAndPass(String username, String pass) {
        List<User> userList = getResultList(User.class, "where o.username = ?1 and o.userpass=?2", null, username, pass);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}
