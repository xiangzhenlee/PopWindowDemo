package com.yushan.popwindowdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends Activity implements View.OnClickListener {
    private EditText et_number;
    private PopupWindow popupWindow;
    private ArrayList<String> list;
    private NumberListAdapter adapter;
    private MenuPop menuPop;
    private View view_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        et_number = (EditText) findViewById(R.id.tv_number);
        view_line = findViewById(R.id.view);
        findViewById(R.id.iv_arrow).setOnClickListener(this);
        findViewById(R.id.iv_menu).setOnClickListener(this);

    }

    private void initData(){
        list = new ArrayList<>();
        // 模拟数据
        for (int i = 0; i < 30; i++) {
            list.add((100000 + i) + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_arrow:
                initPopWindow();
                break;

            case R.id.iv_menu:
                menuPop = new MenuPop(HomeActivity.this, itemOnClick);
                menuPop.showPopupWindow(view_line);
                break;
            default:
                break;
        }
    }

    private void initPopWindow(){
        if (popupWindow == null) {
            // 创建一个PopupWindow对象
            // 设置PopupWindow要显示的内容
            ListView contentView = createListView();
            // 设置PopupWindow的宽
            int width = et_number.getWidth();
            // 设置PopupWindow的高
            int height = 500;
            // 设置PopupWindow是否可以获取焦点
            boolean focusable = true;
            popupWindow = new PopupWindow(contentView, width, height, focusable);

            // 下面的两行代码是用于实现在点PopupWindow之外的地方的时候可以隐藏PopupWindow
            // 设置PopupWindow的外部可以点击
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
        }

        // 显示PopupWindow
        // 指定PopupWindow显示在哪个View的下方
        View anchor = et_number;
        // 指定PopupWindow的x方向的偏移量 xoff > 0向右偏移
        int xoff = 15;
        // 指定PopupWindow的y方向的偏移量 以et_number的底部为yoff = 0,yoff > 0向下偏移
        int yoff = -5;
        popupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    private ListView createListView() {
        ListView listView = (ListView) View.inflate(this, R.layout.layout_number_list, null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String number = (String) parent.getItemAtPosition(position);
                et_number.setText(number);
                popupWindow.dismiss();
            }
        });
        adapter = new NumberListAdapter(HomeActivity.this,list);
        listView.setAdapter(adapter);
        return listView;
    }

    private View.OnClickListener itemOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.tv_regulation:
                    menuPop.dismiss();
                    Toast.makeText(HomeActivity.this,"A",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_task: // 进入竞赛任务
                    menuPop.dismiss();
                    Toast.makeText(HomeActivity.this,"B",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_card:
                    menuPop.dismiss();
                    Toast.makeText(HomeActivity.this,"C",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_share:
                    menuPop.dismiss();
                    Toast.makeText(HomeActivity.this,"D",Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };
}
