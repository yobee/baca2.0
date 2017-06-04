package yobe.baca50.model;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yobe on 2017/06/04.
 */

public class User extends RealmObject {

    public static final String FIELD_ID = "uid";
    public static final String FIELD_NAME = "Name";
    public static final String FIELD_PFIU = "profileImageUrl";

    private static AtomicInteger INTEGER_USER = new AtomicInteger(0);

    @PrimaryKey
    private int uId;
    private String uName;
    private String uDetail;

    public int getuId() {
        return uId;
    }

    public String getuIdString(){
        return Integer.toString(uId);
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuDetail() {
        return uDetail;
    }

    public void setuDetail(String uDetail) {
        this.uDetail = uDetail;
    }

    public String getuProfileImageUrl() {
        return uProfileImageUrl;
    }

    public void setuProfileImageUrl(String uProfileImageUrl) {
        this.uProfileImageUrl = uProfileImageUrl;
    }

    private String uProfileImageUrl;



    static void create(Realm realm) {
        create(realm, false);
    }
    static void create(Realm realm, boolean randomlyInsert) {
        Parent parent = realm.where(Parent.class).findFirst();
        RealmList<User> users = parent.getUserList();
        User user = realm.createObject(User.class, increment());
        if (randomlyInsert && users.size() > 0) {
            Random rand = new Random();
            users.listIterator(rand.nextInt(users.size())).add(user);
        } else {
            users.add(user);
        }
    }
    static void delete(Realm realm, long id) {
        User user = realm.where(User.class).equalTo(FIELD_ID, id).findFirst();
        // Otherwise it has been deleted already.
        if (user != null) {
            user.deleteFromRealm();
        }
    }

    private static int increment() {
        return INTEGER_USER.getAndIncrement();
    }
}
