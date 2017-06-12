package yobe.baca50;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import yobe.baca50.model.DataHelper;
import yobe.baca50.model.Parent;
import yobe.baca50.model.User;
import yobe.baca50.ui.DividerItemDecoration;

import static yobe.baca50.R.id.radioButtonMan;


public class UserRegistActivity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    String mName;
    String mSubName;

    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        TouchHelperCallback() {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            DataHelper.deleteItemAsync(realm, viewHolder.getItemId());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regist);

        realm = Realm.getDefaultInstance();

        final EditText mName = (EditText) findViewById(R.id.Name);
        final EditText mSubName = (EditText) findViewById(R.id.subName);

        RadioGroup group = (RadioGroup) findViewById(R.id.radio_sex_group);
        group.check(radioButtonMan);


        findViewById(R.id.regist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // クリック時の処理

                User user = new User();
                user.setuName(mName.toString());
                user.setuSubName(mSubName.toString());

                // 性別ラジオボタンのチェック
                RadioButton radio = (RadioButton) findViewById(radioButtonMan);
                if (radio.isChecked() == true) {
                // 性別チェック
                    user.setuSex(1);
                } else {
                    user.setuSex(0);
                }

                DataHelper.addItemAsync(realm, user);


                Intent intent = new Intent(UserRegistActivity.this, UserListActivity.class);
                startActivity(intent);

            }
        });

//        setUpRecyclerView();

    }

//    private void setUpRecyclerView() {
//        adapter = new MyRecyclerViewAdapter(realm.where(Parent.class).findFirst().getUserList());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//
//        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
//        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
//        touchHelper.attachToRecyclerView(recyclerView);
//    }
}
