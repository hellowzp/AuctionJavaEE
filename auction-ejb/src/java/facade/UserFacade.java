package facade;

import entity.User;
import java.util.*;

import javax.ejb.*;

@Stateless(name = "userFacade")
public class UserFacade extends AbstractFacade implements UserFacadeLocal {
    @Override
    public User findUserByNameAndPwd(String username, String pass) {
        List<User> userList = getResultList(User.class, "where o.username = ?1 and o.userpass=?2", null, username, pass);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}
