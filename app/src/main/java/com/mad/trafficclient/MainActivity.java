/**
 *
 */
package com.mad.trafficclient;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mad.trafficclient.fragment.FragmentHome;
import com.mad.trafficclient.st_java.fragment.GRZXFra;
import com.mad.trafficclient.st_java.fragment.LKCXFra;
import com.mad.trafficclient.st_java.fragment.SHZSFra;
import com.mad.trafficclient.st_java.fragment.SHZSminFra;
import com.mad.trafficclient.st_java.fragment.SSHJFra;
import com.mad.trafficclient.ws_java.ob1.Account_Main;
import com.mad.trafficclient.ws_java.ob11.Light_Main;
import com.mad.trafficclient.ws_java.ob2.ZhangDanManager;
import com.mad.trafficclient.ws_java.ob23.BalanceYuZhiSetting;
import com.mad.trafficclient.ws_java.ob25.Status_Main;
import com.mad.trafficclient.ws_java.ob32.SubwayMain;
import com.mad.trafficclient.ws_java.ob5.Sense_Main;
import com.mad.trafficclient.ws_java.ob7.YuZhiSetting;
import com.mad.trafficclient.ws_java.ob9.Acc_Main;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.fragment.BusManage10;
import com.mad.trafficclient.zy_java.fragment.LightManage_2;
import com.mad.trafficclient.zy_java.fragment.Message_Main;
import com.mad.trafficclient.zy_java.fragment.PeccancyQuery_1;
import com.mad.trafficclient.zy_java.fragment.VideoFragment;
import com.mad.trafficclient.zy_java.fragment.Zy_charmain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends FragmentActivity {
    private SlidingPaneLayout slidepanel;

    private Fragment fragment;

    private ListView listView;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;

    private FragmentManager fragmentManager;
    private LinearLayout top_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        new Thread() {
            @Override
            public void run() {
                new CarData(MainActivity.this);
                super.run();
            }
        }.start();

        setContentView(R.layout.activity_main);
        initView();
        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);

        listView = (ListView) findViewById(R.id.listView1);
        ivHome = (ImageView) findViewById(R.id.imageView_home);
        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setHome();
            }
        });

        iVSliding = (ImageView) findViewById(R.id.imageView_Sliding);
        tV_title = (TextView) findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_title));


        iVSliding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });


        final String[] actionTexts = new String[]{
                getString(R.string.item_1),
                getString(R.string.item_2),
                getString(R.string.item_3),
                getString(R.string.item_4),
                getString(R.string.item_5),
                getString(R.string.item_6),
                getString(R.string.item_7),
                getString(R.string.item_8),
                getString(R.string.item_9),
                getString(R.string.item_10),
                getString(R.string.item_11),
                getString(R.string.item_12),
                getString(R.string.item_13),
                getString(R.string.item_14),
                getString(R.string.item_15),
                getString(R.string.item_16),
                getString(R.string.item_17),
                getString(R.string.item_18),
                getString(R.string.item_19),
                getString(R.string.item_20),
                getString(R.string.item_21),
                getString(R.string.item_22),
                getString(R.string.item_23),
                getString(R.string.item_24),
                getString(R.string.item_25),
                getString(R.string.item_26),
                getString(R.string.item_27),
                getString(R.string.item_28),
                getString(R.string.item_29),
                getString(R.string.item_30),
                getString(R.string.item_31),
                getString(R.string.item_32),
                getString(R.string.res_left_exit)

        };
        int[] actionImages = new int[]{
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_download,
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_download,

        };

        actionItems = new ArrayList<HashMap<String, Object>>();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});

        for (int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
        listView.setAdapter(actionAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Account_Main()).commit();
                        //过
                        tV_title.setText(actionTexts[arg2]);

                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new LightManage_2()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;

                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ZhangDanManager()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;

                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new VideoFragment()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Sense_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;

                    case 5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Sense_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 6:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new YuZhiSetting()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 7:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new VideoFragment()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 8:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Acc_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 9:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusManage10()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 10:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Light_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 11:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new PeccancyQuery_1()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 12:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new LKCXFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 13:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SHZSFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 14:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Zy_charmain()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 15:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new GRZXFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 16:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SHZSFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 17:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Message_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 18:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Zy_charmain()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 19:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new GRZXFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 20:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new LightManage_2()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 21:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Account_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 22:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BalanceYuZhiSetting()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 23:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SHZSminFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 24:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Status_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 25:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Zy_charmain()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 26:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SHZSminFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 27:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusManage10()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 28:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SSHJFra()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 29:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new VideoFragment()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 30:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Message_Main()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;
                    case 31:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SubwayMain()).commit();
                        tV_title.setText(actionTexts[arg2]);

                        break;

                    case 32:
                        exitAppDialog();

                        break;

                    default:
                        break;
                }
                if (arg2 == 31) {
                    top_title.setVisibility(View.GONE);
                } else {
                    top_title.setVisibility(View.VISIBLE);

                }

            }
        });

        fragmentManager = getFragmentManager();

        setHome();
//        new CarData(this);


    }

    public void setHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
        tV_title.setText(R.string.app_title);

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        int[] listImg = new int[]{R.drawable.icon_trafic, R.drawable.icon_busstop, R.drawable.icon_lamp, R.drawable.icon_parking, R.drawable.icon_light, R.drawable.icon_etc, R.drawable.icon_speed};
        String[] listName = new String[]{"城市交通", "公交站点", "城市环境", "找车位", "红绿灯管理", "ETC管理", "高速车速监控"};
        for (int i = 0; i < listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", listImg[i]);
            item.put("itemName", listName[i]);
            items.add(item);
        }
        return items;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitAppDialog();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }


    private void initView() {
        top_title = (LinearLayout) findViewById(R.id.top_title);
    }
}
