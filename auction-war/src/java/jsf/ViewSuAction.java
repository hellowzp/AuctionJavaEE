package jsf;

import entity.Item;
import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

import business.AuctionManagerLocal;

public class ViewSuAction {

    private List<Item> items;

    @EJB(beanName = "auctionManager")
    private AuctionManagerLocal am;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        Map<String, Object> session = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap();
        Integer userId = (Integer) session.get("userId");
        return am.getItemByWiner(userId);
    }
}
