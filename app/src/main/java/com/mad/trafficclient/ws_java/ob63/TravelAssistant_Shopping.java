package com.mad.trafficclient.ws_java.ob63;


import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/10 21:14
 */

/**
 * Int("id", position);
 * String("photo",list.get(position).getTouris_image());
 * String("name",list.get(position).getName());
 * String("details_text","景点介绍\n" + list.get(position).getDetails_text());
 * Int("balance",list.get(position).getMoney());
 */
public class TravelAssistant_Shopping extends Fragment implements View.OnClickListener {

    private static TravelAssistant_Shopping fragment;
    private ImageView imageView_Sliding;
    private Button ob63_sh_guanli;
    private TextView ob63_sh_today;
    private TextView ob63_sh_tomorrow;
    private ImageView ob63_sh_more_date;
    private ListView ob63_sh_listview;
    private TextView ob63_sh_zongjine;
    private Button ob63_sh_lijizhifu;
    private Button ob63_sh_qingkong;
    private Context context;
    private int flage;
    private List<Shopping_Bean> list;
    private ShoppingAdapter adapter;
    private int sum;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob63_shopping, container, false);
        initView(view);
        flage = 0;
        context = getContext();
        getActivity().findViewById(R.id.top_title).setVisibility(View.GONE);
        list = new ArrayList<>();
        System.out.println(list);
        setListView();
        ob63_sh_today.setText("今天" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "\n预定");
        ob63_sh_tomorrow.setText("明天" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1) + "\n预定");
        calculateSUM();
        return view;
    }

    private void calculateSUM(){
        sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + (list.get(i).getNumber() * list.get(i).getBalance());
        }
        ob63_sh_zongjine.setText("总金额：" + sum + "元");
    }

    private void setListView() {
        Shopping_Bean shoppingBean = new Shopping_Bean(
                fragment.getArguments().getInt("id"),
                fragment.getArguments().getString("photo"),
                fragment.getArguments().getString("name"),
                fragment.getArguments().getString("details_text"),
                1,
                fragment.getArguments().getInt("balance"));
        Log.i("Go_Fight_Now 提醒您", "shoppingBean.toString()" + ":" + shoppingBean.toString());
        
        String data = Shopping_Util.getList(context);
        if (data == null){
            Toast.makeText(context, "空的", Toast.LENGTH_SHORT).show();
        }
        Log.i("Go_Fight_Now 提醒您", "data" + ":" + data);

        list = new Gson().fromJson(data, new TypeToken<List<Shopping_Bean>>() {
        }.getType());
//        Log.i("Go_Fight_Now 提醒您", "显示之前list.size()" + ":" + list.size());

        if (list == null||list.size() == 0) {
            list.add(shoppingBean);
        } else {
            boolean state = true;
            for (int i = 0; i < list.size(); i++) {
                if (shoppingBean.getId() == list.get(i).getId()) {
                    list.get(i).setNumber(list.get(i).getNumber() + 1);
                    state = false;
                    break;
                }
            }
            if (state) {
                list.add(shoppingBean);
            }
        }
        adapter = new ShoppingAdapter();
        ob63_sh_listview.setAdapter(adapter);
    }

    public static TravelAssistant_Shopping newInstance(Bundle args) {
        fragment = new TravelAssistant_Shopping();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        ob63_sh_guanli = (Button) view.findViewById(R.id.ob63_sh_guanli);
        ob63_sh_today = (TextView) view.findViewById(R.id.ob63_sh_today);
        ob63_sh_tomorrow = (TextView) view.findViewById(R.id.ob63_sh_tomorrow);
        ob63_sh_more_date = (ImageView) view.findViewById(R.id.ob63_sh_more_date);
        ob63_sh_listview = (ListView) view.findViewById(R.id.ob63_sh_listview);
        ob63_sh_zongjine = (TextView) view.findViewById(R.id.ob63_sh_zongjine);
        ob63_sh_lijizhifu = (Button) view.findViewById(R.id.ob63_sh_lijizhifu);
        ob63_sh_qingkong = (Button) view.findViewById(R.id.ob63_sh_qingkong);

        ob63_sh_guanli.setOnClickListener(this);
        ob63_sh_lijizhifu.setOnClickListener(this);
        ob63_sh_qingkong.setOnClickListener(this);
        ob63_sh_more_date.setOnClickListener(this);
        ob63_sh_today.setOnClickListener(this);
        ob63_sh_tomorrow.setOnClickListener(this);
        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.top_title).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob63_sh_today:
                ob63_sh_today.setTextColor(Color.BLACK);
                ob63_sh_tomorrow.setTextColor(Color.parseColor("#999494"));
                break;
            case R.id.ob63_sh_tomorrow:
                ob63_sh_tomorrow.setTextColor(Color.BLACK);
                ob63_sh_today.setTextColor(Color.parseColor("#999494"));
                break;
            case R.id.ob63_sh_guanli:
                if (flage == 0) {
                    flage = 1; //调整为显示状态
                } else if (flage == 1) {
                    flage = 0; //调整为隐藏
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.ob63_sh_lijizhifu:
                Toast.makeText(context, "立即支付，二维码呈上", Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                StringBuilder stringBuilder = new StringBuilder("");
                for (int i = 0; i < list.size(); i++) { stringBuilder.append(list.get(i).getName() + "、"); }
                String name = stringBuilder.substring(0,stringBuilder.length()-1);
                args.putString("name", name);
                args.putInt("balance",sum);
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,Shopping_Zxing.newInstance(args)).commit();
                break;
            case R.id.ob63_sh_qingkong:
                list.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.ob63_sh_more_date:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = View.inflate(context, R.layout.caldarview, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Calendar.getInstance().get(Calendar.YEAR) == datePicker.getYear() &&
                                Calendar.getInstance().get(Calendar.MONTH) == datePicker.getMonth() &&
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == datePicker.getDayOfMonth()) {
                            ob63_sh_today.setTextColor(Color.BLACK);
                            ob63_sh_tomorrow.setTextColor(Color.parseColor("#999494"));
                        }else if (Calendar.getInstance().get(Calendar.YEAR) == datePicker.getYear() &&
                                Calendar.getInstance().get(Calendar.MONTH) == datePicker.getMonth() &&
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1 == datePicker.getDayOfMonth()) {
                            ob63_sh_tomorrow.setTextColor(Color.BLACK);
                            ob63_sh_today.setTextColor(Color.parseColor("#999494"));
                        }else{
                            ob63_sh_today.setTextColor(Color.parseColor("#999494"));
                            ob63_sh_tomorrow.setTextColor(Color.parseColor("#999494"));
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }


    class ShoppingAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.ob63_sh_list_item, null);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            if (flage == 0) {
                viewHolder.ob63_tv_shanchu.setVisibility(View.INVISIBLE);
            } else if (flage == 1) {
                viewHolder.ob63_tv_shanchu.setVisibility(View.VISIBLE);
            }
            viewHolder.ob63_tv_shanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                    calculateSUM();
                }
            });
            viewHolder.ob63_lv_image.setImageResource(Acc_Util.getImage(list.get(position).getImage()));
            viewHolder.ob63_lv_name.setText(list.get(position).getName());
            viewHolder.ob63_lv_introduction.setText(list.get(position).getIntroduction());
            viewHolder.ob63_lv_num.setText(list.get(position).getNumber() + "");
            viewHolder.ob63_lv_balance.setText(list.get(position).getBalance() + "元");

            viewHolder.ob63_lv_jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(viewHolder.ob63_lv_num.getText().toString().trim()) <= 1) {
                        viewHolder.ob63_lv_jian.setTextColor(Color.parseColor("#999494"));
                    }
                    if (Integer.parseInt(viewHolder.ob63_lv_num.getText().toString().trim()) <= 0) {

                    } else {
                        list.get(position).setNumber(list.get(position).getNumber() - 1);
                        viewHolder.ob63_lv_num.setText(list.get(position).getNumber() + "");
                    }
                    calculateSUM();
                }
            });

            viewHolder.ob63_lv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.ob63_lv_jian.setTextColor(Color.BLACK);
                    list.get(position).setNumber(list.get(position).getNumber() + 1);
                    viewHolder.ob63_lv_num.setText(list.get(position).getNumber() + "");
                    calculateSUM();
                }
            });
            return convertView;
        }


        public class ViewHolder {
            public View rootView;
            public TextView ob63_tv_shanchu;
            public ImageView ob63_lv_image;
            public TextView ob63_lv_name;
            public TextView ob63_lv_introduction;
            public Button ob63_lv_jian;
            public TextView ob63_lv_num;
            public Button ob63_lv_add;
            public TextView ob63_lv_balance;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob63_tv_shanchu = (TextView) rootView.findViewById(R.id.ob63_tv_shanchu);
                this.ob63_lv_image = (ImageView) rootView.findViewById(R.id.ob63_lv_image);
                this.ob63_lv_name = (TextView) rootView.findViewById(R.id.ob63_lv_name);
                this.ob63_lv_introduction = (TextView) rootView.findViewById(R.id.ob63_lv_introduction);
                this.ob63_lv_jian = (Button) rootView.findViewById(R.id.ob63_lv_jian);
                this.ob63_lv_num = (TextView) rootView.findViewById(R.id.ob63_lv_num);
                this.ob63_lv_add = (Button) rootView.findViewById(R.id.ob63_lv_add);
                this.ob63_lv_balance = (TextView) rootView.findViewById(R.id.ob63_lv_balance);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Go_Fight_Now 提醒您", "退出之前list.size()" + ":" + list.size());
        Shopping_Util.saveList(context, list);
        Log.i("Go_Fight_Now 提醒您", "------------------------------------------------------------------------------------------");
    }
}
