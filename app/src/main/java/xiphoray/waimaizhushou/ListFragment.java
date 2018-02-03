package xiphoray.waimaizhushou;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ListFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {
    private android.support.v4.app.FragmentManager fManager;
    private ArrayList<Data> datas;

    public ListFragment(android.support.v4.app.FragmentManager fManager, ArrayList<Data> datas) {
        this.fManager = fManager;
        this.datas = datas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_newlist, container, false);
        ListView list_news = view.findViewById(R.id.list_news);
        MyAdapter myAdapter = new MyAdapter(datas, getActivity());
        list_news.setAdapter(myAdapter);
        list_news.setOnItemClickListener(this);
        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        android.support.v4.app.FragmentTransaction fTransaction = fManager.beginTransaction();
        NewContentFragment ncFragment = new NewContentFragment();
        Bundle bd = new Bundle();
        bd.putString("content", datas.get(position).getNew_content());
        ncFragment.setArguments(bd);
        //加上Fragment替换动画
        fTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
        fTransaction.replace(R.id.menu_content, ncFragment);
        //调用addToBackStack将Fragment添加到栈中
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }
}
