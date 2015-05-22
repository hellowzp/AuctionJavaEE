package jsf;

import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

import business.AuctionManagerLocal;

public class LoginAction {

    private String username;
    private String password;
    private String vercode;
    private String errInfo;

    @EJB(beanName = "auctionManager")
    private AuctionManagerLocal am;

    public String execute() throws Exception {
        Map<String, Object> session = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap();
        //clear the random validation code string in the Http Session
        String ver2 = (String) session.get("rand");
        session.put("rand", null);
        if (vercode.equalsIgnoreCase(ver2)) {
            int userId = am.validateLogin(username, password);
            if (userId > 0) {
                session.put("userId", userId);
                return "success";
            } else {
                setErrInfo("The user name and the password does not match");
                return "failure";
            }
        } else {
            setErrInfo("wrong validation code, input again");
            return "failure";
        }
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public String getVercode() {
        return this.vercode;
    }


    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getErrInfo() {
        return this.errInfo;
    }
}
