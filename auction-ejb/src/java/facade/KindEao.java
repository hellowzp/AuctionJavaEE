package facade;

import entity.Kind;
import java.util.List;
import javax.ejb.*;

@Local
public interface KindEao extends Facade {

    List<Kind> findAll();
}
