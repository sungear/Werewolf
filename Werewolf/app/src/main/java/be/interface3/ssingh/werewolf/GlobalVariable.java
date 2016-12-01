package be.interface3.ssingh.werewolf;

import org.json.JSONObject;

import java.util.ArrayList;

import be.interface3.ssingh.werewolf.model.Party;
import be.interface3.ssingh.werewolf.model.User;

/**
 * Created by s.singh on 30/09/2016.
 */
public abstract class GlobalVariable {
//    public abstract static class function() {
//        public static final String USER = "User";
//    }

    public static final String URL_QUERY_SELECT = "http://10.0.2.2/android/select.php";
    public static final String URL_QUERY_INSERT = "http://10.0.2.2/android/insert.php";
    public static final String URL_QUERY_UPDATE = "http://10.0.2.2/android/update.php";
    public static final String URL_QUERY_DELETE = "http://10.0.2.2/android/delete.php";

    public static final User USER = new User();

    public  static final String USER_ID = "id";
    public  static final String USER_USERNAME = "username";
    public  static final String USER_ROLE = "role";
    public  static  final String USER_IS_CONNECTED = "isConnected";
    public  static final String USER_IS_ACTIVE = "isActive";
    public  static final String USER_IS_ALIVE = "isAlive";
    public static final String USER_IS_VICTIM = "isVictim";
    public static final String USER_IS_GM = "isGM";

    public static final Party PARTY = new Party();
}