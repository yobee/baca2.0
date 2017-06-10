package yobe.baca50.model;

import android.widget.Toast;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yobe on 2017/06/04.
 */

public class User extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_UID = "uid";
    public static final String FIELD_NAME = "uName";
    public static final String FIELD_SUB_NAME = "uSubName";
    public static final String FIELD_PFIU = "uSex";

    private static AtomicLong LONG_USER = new AtomicLong(0);

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private Long uId;

    private String uName;
    private String uSubName;
    private Integer uSex;

    public Integer getuSex() {
        return uSex;
    }

    public void setuSex(Integer uSex) {
        this.uSex = uSex;
    }


    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuSubName() {
        return uSubName;
    }

    public void setuSubName(String uSubName) {
        this.uSubName = uSubName;
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
        String wkName = null;
        String wkSubName = null;

        //User user = realm.createObject(User.class, increment());

        User user = new User();

    }

//    登録処理
    static void insertAction(Realm realm){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                User user = bgRealm.createObject(User.class);
                user.setuId(increment());
                user.setuName("Young Person");
                user.setuSubName("４パーソン");
                user.setuSex(1);
            }
        });
    }

    static void updateAction(Realm realm, long id) {
        User user = realm.where(User.class).equalTo(FIELD_ID, id).findFirst();
        // Otherwise it has been deleted already.
    }

    static void deleteAction(Realm realm, long id) {
        User user = realm.where(User.class).equalTo(FIELD_ID, id).findFirst();
        // Otherwise it has been deleted already.
        if (user != null) {
            user.deleteFromRealm();
        }
    }



    private void basicCRUD(Realm realm) {

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                User user = realm.createObject(User.class);
                user.setuId(increment());
                user.setuName("Young Person");
                user.setuSubName("４パーソン");
                user.setuSex(1);

            }

        });

        // Find the first person (no query conditions) and read a field
        final User user = realm.where(User.class).equalTo("id",1).findFirst();
        user.setuName("Young ");
        user.setuSubName("5パーソン");
        user.setuSex(0);

        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealmOrUpdate(user);
            }
        });


        //削除処理
        final RealmResults<User> results = realm.where(User.class).equalTo("id",1).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User users = results.get(0);
                users.deleteFromRealm();

            }
        });
    }

    static void delete(Realm realm, long id) {
        User user = realm.where(User.class).equalTo(FIELD_ID, id).findFirst();
        // Otherwise it has been deleted already.
        if (user != null) {
            user.deleteFromRealm();
        }
    }



    private static long increment() {
        return LONG_USER.getAndIncrement();
    }
}
