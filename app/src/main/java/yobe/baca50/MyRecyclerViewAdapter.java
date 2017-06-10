package yobe.baca50;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

import yobe.baca50.model.User;


/**
 * Created by yobe on 2017/06/04.
 */

class MyRecyclerViewAdapter extends RealmRecyclerViewAdapter<User, MyRecyclerViewAdapter.MyViewHolder> {

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<Integer>();

    MyRecyclerViewAdapter(OrderedRealmCollection<User> data) {
        super(data, true);
        setHasStableIds(true);
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }

    Set<Integer> getCountersToDelete() {
        return countersToDelete;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_user_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User obj = getItem(position);
        holder.data = obj;
        //noinspection ConstantConditions
        holder.id.setText(obj.getId());
        holder.uid.setText(obj.getuId().toString());
        holder.uName.setText(obj.getuName());
        holder.uSubName.setText(obj.getuSubName());
        holder.deletedCheckBox.setChecked(countersToDelete.contains(obj.getId()));
        if (inDeletionMode) {
            holder.deletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
//                        countersToDelete.add(obj.getuId());
                    } else {
//                        countersToDelete.remove(obj.getId());
                    }
                }
            });
        } else {
            holder.deletedCheckBox.setOnCheckedChangeListener(null);
        }
        holder.deletedCheckBox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);
    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return getItem(index).getuId();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView uid;
        TextView uName;
        TextView uSubName;
        CheckBox deletedCheckBox;
        public User data;

        MyViewHolder(View view) {
            super(view);
            uSubName = (TextView) view.findViewById(R.id.uname);
            deletedCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }
}
