package com.mad.trafficclient.zy_java.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.bean.AllCarBean;
import com.mad.trafficclient.zy_java.bean.AllPeccancyBean;
import com.mad.trafficclient.zy_java.bean.AllPersonBean;
import com.mad.trafficclient.zy_java.bean.AllTypeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cardata2 implements CarData_abs {
    private static List<AllCarBean.ROWSDETAILBean> all_car_list = new ArrayList<>();
    private static List<AllPeccancyBean.ROWSDETAILBean> all_peccancy_list = new ArrayList<>();
    private static List<AllPersonBean.ROWSDETAILBean> all_person_list = new ArrayList<>();
    private static List<AllTypeBean.ROWSDETAILBean> all_type_list = new ArrayList<>();
    private static List<AllPeccancyBean.ROWSDETAILBean> single_pecc_list = new ArrayList<>();
    private static Map map = new HashMap();
    private Context context;
    private ProgressDialog progressDialog;
    private int progress = 0;
    private String api_1;
    private String api_2;
    private String api_3;
    private String api_4;
    private final JSONObject jsonObject;
    private final RequestQueue requestQueue;

    public Cardata2(Context context) {
        this.context = context;

        api_1 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_info";
        api_2 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_car_peccancy";
        api_3 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_user_info";
        api_4 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_peccancy_type";
        jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Looper.prepare();
        progressDialog = new ProgressDialog(context);
//        progressDialog.setProgressStyle();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setMessage("数据正在加载中");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(context);
        EventBus.getDefault().register(this);
        getB1();
        getB2();
        getB3();
        getB4();
        Looper.loop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String data) {
        progress += 20;
        progressDialog.setProgress(progress);
        if (progressDialog.getProgress() == 80 && data.equals("zy")) {
            dealData();
            getTu1();
            getTu2();
            progressDialog.dismiss();
            getTu3();
            getTu4();
            getTu5();
            getTu6();
            getTu7();
        }
    }

    public static List<AllCarBean.ROWSDETAILBean> getAll_car_list() {
        return all_car_list;
    }

    public static List<AllPeccancyBean.ROWSDETAILBean> getAll_peccancy_list() {
        return all_peccancy_list;
    }

    public static List<AllPersonBean.ROWSDETAILBean> getAll_person_list() {
        return all_person_list;
    }

    public static List<AllTypeBean.ROWSDETAILBean> getAll_type_list() {
        return all_type_list;
    }

    public static List<AllPeccancyBean.ROWSDETAILBean> getSingle_pecc_list() {
        return single_pecc_list;
    }

    public static Map getMap() {
        return map;
    }

    @Override
    public void getB1() {
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_1, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllCarBean allCarBean = gson.fromJson(jsonObject.toString(), AllCarBean.class);
                all_car_list = allCarBean.getROWS_DETAIL();
                EventBus.getDefault().post("zy");
                Log.i("", "onResponse: 数据读取");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "网络ip异常 请查看ip是否正确", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getB2() {
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_2, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPeccancyBean allCarBean = gson.fromJson(jsonObject.toString(), AllPeccancyBean.class);
                all_peccancy_list = allCarBean.getROWS_DETAIL();
                EventBus.getDefault().post("zy");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "网络ip异常 请查看ip是否正确", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getB3() {
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_3, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPersonBean allCarBean = gson.fromJson(jsonObject.toString(), AllPersonBean.class);
                all_person_list = allCarBean.getROWS_DETAIL();
                EventBus.getDefault().post("zy");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "网络ip异常 请查看ip是否正确", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getB4() {
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_4, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllTypeBean allCarBean = gson.fromJson(jsonObject.toString(), AllTypeBean.class);
                all_type_list = allCarBean.getROWS_DETAIL();
                EventBus.getDefault().post("zy");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "网络ip异常 请查看ip是否正确", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void dealData() {
        for (int i = 0; i < all_car_list.size(); i++) {
            for (int j = 0; j < all_peccancy_list.size(); j++) {
                if (all_car_list.get(i).getCarnumber().equals(all_peccancy_list.get(j).getCarnumber())) {
                    int count = all_car_list.get(i).getCount();
                    count++;
                    all_car_list.get(i).setCount(count);
                    break;
                }


            }
        }


        for (int i = 0; i < all_peccancy_list.size(); i++) {
            for (int j = 0; j < all_peccancy_list.size(); j++) {
                if (all_peccancy_list.get(i).getCarnumber().equals(all_peccancy_list.get(j).getCarnumber())) {
                    int count = all_peccancy_list.get(i).getCount();
                    count++;
                    all_peccancy_list.get(i).setCount(count);
                }


            }
        }
        Map<String, Integer> map1 = new HashMap();
        for (int i = 0; i < all_peccancy_list.size(); i++) {
            map1.put(all_peccancy_list.get(i).getCarnumber(), all_peccancy_list.get(i).getCount());
        }
        for (String s : map1.keySet()) {
            single_pecc_list.add(new AllPeccancyBean.ROWSDETAILBean(s, map1.get(s)));
            Log.i("test", "dealData: " + s + "   " + map1.get(s));
        }


        for (int i = 0; i < all_type_list.size(); i++) {
            for (int j = 0; j < all_peccancy_list.size(); j++) {
                if (all_type_list.get(i).getPcode().equals(all_peccancy_list.get(j).getPcode())) {
                    int count = all_type_list.get(i).getCount();
                    count++;
                    all_type_list.get(i).setCount(count);
                }


            }
        }

    }

    @Override
    public void getTu1() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        x.add("有违章");
        x.add("无违章");
        y.add((float) single_pecc_list.size());
        y.add((float) (all_car_list.size() - single_pecc_list.size()));
        map.put("x1", x);
        map.put("y1", y);

    }

    @Override
    public void getTu2() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        x.add("有重复违章");
        x.add("无重复违章");
        float[] floats = new float[2];

        for (int i = 0; i < single_pecc_list.size(); i++) {
            Log.i("数据222", "getTu2: " + single_pecc_list.get(i).getCount());
            if (single_pecc_list.get(i).getCount() == 1) {
                floats[1]++;
            } else {
                floats[0]++;
            }
        }
        y.add(floats[0]);
        y.add(floats[1]);
        Log.i("y数据", "getTu2: " + Arrays.asList(y));
        map.put("x2", x);
        map.put("y2", y);

    }

    @Override
    public void getTu3() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        x.add("1-2条违章");
        x.add("3-5条违章");
        x.add("5条违章以上");
        float[] floats = new float[3];

        for (int i = 0; i < single_pecc_list.size(); i++) {
            if (single_pecc_list.get(i).getCount() > 5) {
                floats[2]++;

            } else if (single_pecc_list.get(i).getCount() > 2) {
                floats[1]++;
            } else if (single_pecc_list.get(i).getCount() > 0) {
                floats[0]++;
            }
        }
        y.add(floats[0] * 100 / single_pecc_list.size());
        y.add(floats[1] * 100 / single_pecc_list.size());
        y.add(floats[2] * 100 / single_pecc_list.size());
        map.put("x3", x);
        map.put("y3", y);
    }

    @Override
    public void getTu4() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            y.add(new float[2]);
        }
        x.add("90后");
        x.add("80后");
        x.add("70后");
        x.add("60后");
        x.add("50后");

        for (int i = 0; i < all_car_list.size(); i++) {
            int substring = Integer.parseInt(all_car_list.get(i).getPcardid().substring(6, 10));

            if (all_car_list.get(i).getCount() > 0) {
                if (substring > 1990) {
                    y.get(0)[1]++;
                } else if (substring > 1980) {
                    y.get(1)[1]++;

                } else if (substring > 1970) {
                    y.get(2)[1]++;

                } else if (substring > 1960) {
                    y.get(3)[1]++;

                } else if (substring > 1960) {
                    y.get(4)[1]++;

                }
            } else {
                if (substring > 1990) {
                    y.get(0)[0]++;
                } else if (substring > 1980) {
                    y.get(1)[0]++;

                } else if (substring > 1970) {
                    y.get(2)[0]++;

                } else if (substring > 1960) {
                    y.get(3)[0]++;

                } else if (substring > 1960) {
                    y.get(4)[0]++;

                }
            }


        }
        float[] floats = new float[5];
        for (int i = 0; i < 5; i++) {
            floats[i] = y.get(i)[0] + y.get(i)[1];
        }

        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < 2; j++) {
                y.get(i)[j] = y.get(i)[j] * 100 / floats[i];
            }
        }

        map.put("x4", x);
        map.put("y4", y);


    }

    @Override
    public void getTu5() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            y.add(new float[2]);
        }
        x.add("女性");
        x.add("男性");


        for (int i = 0; i < all_car_list.size(); i++) {
//            int substring = Integer.parseInt(all_car_list.get(i).getPcardid().substring(6, 10));
            int a = all_car_list.get(i).getPcardid().charAt(17);
            if (all_car_list.get(i).getCount() > 0) {
                if (a % 2 != 0) {
                    y.get(0)[1]++;
                } else {
                    y.get(1)[1]++;

                }
            } else {
                if (a % 2 != 0) {
                    y.get(0)[0]++;
                } else {
                    y.get(1)[0]++;

                }
            }


        }
        float[] floats = new float[2];
        for (int i = 0; i < 2; i++) {
            floats[i] = y.get(i)[0] + y.get(i)[1];
        }

        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < 2; j++) {
                y.get(i)[j] = y.get(i)[j] * 100 / floats[i];
            }
        }

        map.put("x5", x);
        map.put("y5", y);
    }

    @Override
    public void getTu6() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float[] floats = new float[12];
        for (int i = 0; i < all_peccancy_list.size(); i++) {
            int substring = Integer.parseInt(all_peccancy_list.get(i).getDatetime().substring(11, 13));
            if (substring >= 22) {
                floats[11]++;
            } else if (substring >= 20) {
                floats[10]++;
            } else if (substring >= 18) {
                floats[9]++;
            } else if (substring >= 16) {
                floats[8]++;
            } else if (substring >= 14) {
                floats[7]++;
            } else if (substring >= 12) {
                floats[6]++;
            } else if (substring >= 10) {
                floats[5]++;
            } else if (substring >= 8) {
                floats[4]++;
            } else if (substring >= 6) {
                floats[3]++;
            } else if (substring >= 4) {
                floats[2]++;
            } else if (substring >= 2) {
                floats[1]++;
            } else if (substring >= 0) {
                floats[0]++;
            }
        }
        for (int i = 0; i < floats.length; i++) {
            x.add(i * 2 + ":00:01-" + (i * 2 + 1) + ":00:00");
            y.add(floats[i] * 100 / all_peccancy_list.size());
        }
        map.put("x6", x);
        map.put("y6", y);
    }

    @Override
    public void getTu7() {
        Collections.sort(all_type_list, new Comparator<AllTypeBean.ROWSDETAILBean>() {
            @Override
            public int compare(AllTypeBean.ROWSDETAILBean o1, AllTypeBean.ROWSDETAILBean o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        float all = 0;
        for (int i = 0; i < 10; i++) {
            all += all_type_list.get(i).getCount();
        }
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        for (int i = 10; i > -1; i--) {
            x.add(all_type_list.get(i).getPremarks());
            y.add(all_type_list.get(i).getCount() * 100 / all);
        }
        map.put("x7", x);
        map.put("y7", y);
    }
}
