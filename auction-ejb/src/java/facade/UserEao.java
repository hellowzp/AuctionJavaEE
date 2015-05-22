package facade;

import entity.User;
import javax.ejb.*;

@Local
public interface UserEao extends Facade {
    User findUserByNameAndPass(String username, String pass);
}
