package com.example.huyongqiang.mypopumenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAPopup();
            }
        });

    }

    private void newAPopup() {
        final PopMenu popMenu = new PopMenu(this);
        backgroundAlpha(0.5f);
        ArrayList<PopMenu.MenuItemBean> menus = new ArrayList<PopMenu.MenuItemBean>();

        PopMenu.MenuItemBean item1 = new PopMenu.MenuItemBean(R.mipmap.ic_launcher, "好孕日记  ");
        PopMenu.MenuItemBean item2 = new PopMenu.MenuItemBean(R.mipmap.ic_launcher, "发话题    ");
//        PopMenu.MenuItemBean item1 = new PopMenu.MenuItemBean(R.drawable.release_diary, "好孕日记  ");
//        PopMenu.MenuItemBean item2 = new PopMenu.MenuItemBean(R.drawable.release_topic, "发话题    ");
        menus.add(item1);
        menus.add(item2);
        popMenu.addItems(menus);

        popMenu.showAsDropDown(btn);
        popMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getApplicationContext(), "diary", Toast.LENGTH_SHORT).show();
                    //MobclickAgent.onEvent(getApplicationContext(), "101");
//                    Intent releaseIntent = new Intent(getApplicationContext(), ReleaseDairyActivity.class);
//                    startActivityForResult(releaseIntent, 127);
                    popMenu.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "topic", Toast.LENGTH_SHORT).show();

//                    Intent releaseIntent = new Intent(getApplicationContext(), ReleaseTopicActivity.class);
//                    startActivityForResult(releaseIntent, 127);
                    popMenu.dismiss();
                }
            }
        });

        popMenu.setDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}
