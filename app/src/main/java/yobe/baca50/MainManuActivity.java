package yobe.baca50;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainManuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);

        String titles[] = {"カルテ","設定"};
        String tags[] = {"カルテ","設定"};
        String descs[] = {"個別のカルテを表示させます","設定画面があります"};

        ArrayList<ListItem> data = new ArrayList<>();
        for (int i = 0; i < titles.length;i++){

            ListItem item = new ListItem();
            item.setId(new Random().nextLong());
            item.setTitle(titles[i]);
            item.setTag(tags[i]);
            item.setDesc(descs[i]);
            data.add(item);

        }

        MyListAdapter adapter = new MyListAdapter(this,data,R.layout.content_menu_list_item);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView =(ListView)parent;
//                String item = (String)listView.getItemAtPosition(position);
                if(position == 0){
                    Intent intent = new Intent(MainManuActivity.this, UserListActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainManuActivity.this, "テスト", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}
