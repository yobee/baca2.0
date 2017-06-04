package yobe.baca50;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import yobe.baca50.model.Parent;

/**
 * Created by yobe on 2017/06/03.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createObject(Parent.class);
                    }})
                .build();
        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);
    }
}
