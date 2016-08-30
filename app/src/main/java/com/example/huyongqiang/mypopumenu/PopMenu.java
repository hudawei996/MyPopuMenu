package com.example.huyongqiang.mypopumenu;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by huyongqiang on 16/8/31.
 */

public class PopMenu {
    private ArrayList<MenuItemBean> itemList;
    private Context context;
    private PopupWindow popupWindow;
    private ListView listView;
    private AdapterView.OnItemClickListener listener;
    private PopAdapter adapter;
    private PopupWindow.OnDismissListener dismissListener;


    public PopMenu(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;

        itemList = new ArrayList<MenuItemBean>();

        View view = LayoutInflater.from(context).inflate(R.layout.popmenu, null);

        //设置 listview
        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new PopAdapter();
        listView.setAdapter(adapter);

        popupWindow = new PopupWindow(view, context.getResources().getDimensionPixelSize(R.dimen.popmenu_width),
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    //设置菜单项点击监听器
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        //this.listener = listener;
        listView.setOnItemClickListener(listener);
    }

    //批量添加菜单项
    public void addItems(ArrayList<MenuItemBean> items) {
        itemList.addAll(items);
        adapter.notifyDataSetChanged();

    }

    //单个添加菜单项
    public void addItem(MenuItemBean item) {
        itemList.add(item);
        adapter.notifyDataSetChanged();
    }

    //下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(View parent) {
        /*popupWindow.showAsDropDown(parent, context.getResources().getDimensionPixelSize(R.dimen.popmenu_xoff),
                //保证尺寸是根据屏幕像素密度来的
                context.getResources().getDimensionPixelSize(R.dimen.popmenu_yoff));*/
        popupWindow.showAsDropDown(parent,0,0);

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //刷新状态
        popupWindow.update();
    }

    //隐藏菜单
    public void dismiss() {
        popupWindow.dismiss();
    }

    // 适配器
    private final class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.popmenu_item, null);
                holder = new ViewHolder();

                convertView.setTag(holder);

                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                holder.groupItem = (TextView) convertView.findViewById(R.id.textView);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.imageView.setImageResource((itemList.get(position)).iconRes);
            holder.groupItem.setText(itemList.get(position).text);

            return convertView;
        }

        private final class ViewHolder {
            ImageView imageView;
            TextView groupItem;
        }
    }

    public static class MenuItemBean {
        public int iconRes;
        public String text;

        public MenuItemBean(int iconRes, String text) {
            this.iconRes = iconRes;
            this.text = text;
        }
    }


    public void setDismissListener(PopupWindow.OnDismissListener dismissListener){
        this.dismissListener = dismissListener;

        popupWindow.setOnDismissListener(dismissListener);
    }

}
