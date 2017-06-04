package yobe.baca50.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by yobe on 2017/06/03.
 */

public class Parent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<User> userList;

    public RealmList<User> getUserList() {
        return userList;
    }
}
