package yobe.baca50.model;

import java.util.Collection;

import io.realm.Realm;

/**
 * Created by yobe on 2017/06/03.
 */

public class DataHelper {

    // 新規登録
    public static void addItemAsync(Realm realm) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User.insertAction(realm);
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
