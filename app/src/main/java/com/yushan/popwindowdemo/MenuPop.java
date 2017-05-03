package com.yushan.popwindowdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MenuPop extends PopupWindow {

	private View view;
	public TextView tv_regulation,tv_task,tv_card,tv_share;
	private int h,w;
	private String tv_bgColor = "#77000000";
	private final OnClickListener click;

	public MenuPop(Activity context, OnClickListener onClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.menu_popup, null);
		h = context.getWindowManager().getDefaultDisplay().getHeight();
		w = context.getWindowManager().getDefaultDisplay().getWidth();
		click = onClick;

		initView();
		initPopWindow();
	}

	private void initView(){
		tv_regulation = (TextView) view.findViewById(R.id.tv_regulation);
		tv_task = (TextView) view.findViewById(R.id.tv_task);
		tv_card = (TextView) view.findViewById(R.id.tv_card);
		tv_share = (TextView) view.findViewById(R.id.tv_share);

		tv_regulation.setBackgroundColor(Color.parseColor(tv_bgColor));
		tv_task.setBackgroundColor(Color.parseColor(tv_bgColor));
		tv_card.setBackgroundColor(Color.parseColor(tv_bgColor));
		tv_share.setBackgroundColor(Color.parseColor(tv_bgColor));

		tv_regulation.setOnClickListener(click);
		tv_task.setOnClickListener(click);
		tv_card.setOnClickListener(click);
		tv_share.setOnClickListener(click);
	}

	private void initPopWindow(){
		this.setOutsideTouchable(false);
		this.setContentView(view);
		// 设置弹出窗体的宽
		this.setWidth(w);
		// 设置弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置弹出窗体可点击
		this.setFocusable(true);
		// 设置弹出窗体动画效果
//		 this.setAnimationStyle(R.style.AnimTop_gradualChange);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
		//设置弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//		view.setBackgroundDrawable(new ColorDrawable());

		view.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = view.findViewById(R.id.rl_competeMenu).getBottom();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y > height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent,0,0);
		} else {
			this.dismiss();
		}
	}
}
