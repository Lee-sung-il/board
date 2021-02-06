package common;

import member.vo.MemberVo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class LoginManager implements HttpSessionBindingListener {
    private static Hashtable loginUsers = new Hashtable();

    private  LoginManager() {

    }
    public static LoginManager getInstance() {
        return  LoginManager.LazyHolder.INSTANCE;
    }
    private static class LazyHolder {
        private static final LoginManager INSTANCE = new LoginManager();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        loginUsers.put(event.getSession(),event.getName());

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        loginUsers.remove(event.getSession());

    }

    public void setSession(HttpSession session, int sq) {
        session.setAttribute(Integer.toString(sq),this);
    }

    public String  getMemberSq (HttpSession session){
        return (String) loginUsers.get(session);
    }

    public void removeSession(String sq) {
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        while (e.hasMoreElements()){
            session = (HttpSession) e.nextElement();
            if (loginUsers.get(session).equals(sq)){
                session.invalidate();
            }
        }
    }
}
