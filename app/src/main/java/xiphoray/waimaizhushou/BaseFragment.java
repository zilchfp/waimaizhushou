package xiphoray.waimaizhushou;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class BaseFragment extends android.support.v4.app.Fragment implements MainActivity.FragmentBackHandler {
    View view;
    public static BaseFragment newInstance(String menuName) {
        Bundle args = new Bundle();
        BaseFragment fragment = new BaseFragment();
        args.putString("menuName", menuName);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
        view = inflater.inflate(R.layout.fragment_base, null);
        initializeChoiceLayout();
        return view;
    }

    void initializeChoiceLayout(){
        if(Objects.equals(getArguments().getString("menuName"), "订单")){
            ordersMenuInitialization();
        } else if(Objects.equals(getArguments().getString("menuName"), "个人中心")) {
            personalSettingInitialization();
        } else if(Objects.equals(getArguments().getString("menuName"), "消息")) {
            messageInitialization();
        }
        TextView tvInfo = view.findViewById(R.id.message);
        tvInfo.setText(getArguments().getString("menuName"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onBackPressed()
    {
        return BackHandlerHelper.handleBackPress(this);
    }


    private void ordersMenuInitialization() {
        ArrayList<Data> datas = new ArrayList<>();
        android.support.v4.app.FragmentManager fManager = getChildFragmentManager();
        for (int i = 1; i <= 20; i++) {
            Data data = new Data("  订单" + i + "号", i + "宫保鸡丁");
            datas.add(data);
        }
        ListFragment OrdersListFragment = new ListFragment(fManager, datas);
        android.support.v4.app.FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.menu_content, OrdersListFragment);
        ft.commit();
    }

    private void messageInitialization() {
        ArrayList<Data> datas = new ArrayList<>();
        android.support.v4.app.FragmentManager fManager = getChildFragmentManager();
        for (int i = 1; i <= 20; i++) {
            Data data = new Data("  消息" + i + "号", i + "宫保鸡丁");
            datas.add(data);
        }
        ListFragment OrdersListFragment = new ListFragment(fManager, datas);
        android.support.v4.app.FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.menu_content, OrdersListFragment);
        ft.commit();
    }


    private void personalSettingInitialization() {
        ArrayList<SettingPerson> datas = new ArrayList<>();
        android.support.v4.app.FragmentManager fManager = getChildFragmentManager();
        String[] settings = {"历史订单","规划设置","寻呼设置","清理内存","帮助"};
        for (int i = 1; i <= 5; i++) {
            SettingPerson data = new SettingPerson("  "+settings[i-1], i + "嘿嘿嘿");
            datas.add(data);
        }
        NewPersonFragment nlFragment = new NewPersonFragment(fManager, datas);
        android.support.v4.app.FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.menu_content, nlFragment);
        ft.commit();
    }
}
