package yobe.baca50.model;

import java.util.Collection;

import io.realm.Realm;

/**
 * Created by yobe on 2017/06/03.
 */

public class DataHelper {

    public static void listItemAsync(Realm realm) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }


    // 新規登録
    public static void addItemAsync(Realm realm,final User user) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User.insertAction(realm,user);
            }
        });
    }

    // 修正
    public static void updateItemAsync(Realm realm,final long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User.updateAction(realm,id);
            }
        });
    }

    // ユーザーの削除
    public static void deleteItemAsync(Realm realm, final long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User.deleteAction(realm, id);
            }
        });
    }

}
