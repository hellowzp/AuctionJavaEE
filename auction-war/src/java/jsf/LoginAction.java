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
        String ver2 = (String) session.get("rand");
        //清空Http Session中的随机验证码字符串。
        session.put("rand", null);
        if (vercode.equalsIgnoreCase(ver2)) {
            Integer userId = am.validLogin(username, password);
            if (userId != null && userId > 0) {
                session.put("userId", userId);
                return "success";
            } else {
                setErrInfo("用户名/密码不匹配");
                return "failure";
            }
        } else {
            setErrInfo("验证码不匹配,请重新输入");
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
