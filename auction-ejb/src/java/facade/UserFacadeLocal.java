package facade;

import entity.User;
import javax.ejb.*;

@Local
public interface UserFacadeLocal extends Facade {
    User findUserByNameAndPwd(String username, String pass);
}
