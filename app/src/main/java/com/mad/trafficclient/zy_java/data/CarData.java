package com.mad.trafficclient.zy_java.data;

import android.content.Context;
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

/**
 * Created by 昭阳 on 2019/5/5.
 */
public class CarData implements CarData_abs {
    private Context context;
    private static List<AllCarBean.ROWSDETAILBean> allcar_list = new ArrayList<>();
    private static List<AllPeccancyBean.ROWSDETAILBean> allpeccancy_list = new ArrayList<>();
    private static List<AllPersonBean.ROWSDETAILBean> allperson_list = new ArrayList<>();
    private static List<AllTypeBean.ROWSDETAILBean> alltype_list = new ArrayList<>();
    private static List<AllPeccancyBean.ROWSDETAILBean> single_peccancy = new ArrayList<>();
    private static Map map = new HashMap();
    private static int flag = 0;
    private String api_1 = "";
    private String api_2 = "";
    private String api_3 = "";
    private String api_4 = "";
    private final RequestQueue requestQueue;
    private final JSONObject object;

    public CarData(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
        requestQueue = Volley.newRequestQueue(context);
        object = new JSONObject();
        try {
            object.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        api_1 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_info";
        api_2 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_car_peccancy";
        api_3 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_user_info";
        api_4 = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_peccancy_type";
        getB1();
        getB2();
        getB3();
        getB4();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String data) {
        switch (data) {
            case "zy":
                if (flag == 4) {
                    dealData();
                    getTu1();
                    getTu2();
                    getTu3();
                    getTu4();
                    getTu5();
                    getTu6();
                    getTu7();
                }
                break;

        }
    }

    public static List<AllCarBean.ROWSDETAILBean> getAllcar_list() {
        return allcar_list;
    }

    public static List<AllPeccancyBean.ROWSDETAILBean> getallpeccancy_list() {
        return allpeccancy_list;
    }

    public static List<AllPersonBean.ROWSDETAILBean> getallperson_list() {
        return allperson_list;
    }

    public static List<AllTypeBean.ROWSDETAILBean> getAlltype_list() {
        return alltype_list;
    }

    public static Map getMap() {
        return map;
    }

    public static int getFlag() {
        return flag;
    }

    @Override
    public void getB1() {
        JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_1, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllCarBean allCarBean = gson.fromJson(jsonObject.toString(), AllCarBean.class);
                allcar_list = allCarBean.getROWS_DETAIL();

                flag++;
                EventBus.getDefault().post("zy");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "正在读取数据", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);

    }

    @Override
    public void getB2() {
        JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_2, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPeccancyBean allCarBean = gson.fromJson(jsonObject.toString(), AllPeccancyBean.class);
                allpeccancy_list = allCarBean.getROWS_DETAIL();
                flag++;
                EventBus.getDefault().post("zy");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "正在读取数据", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void getB3() {
        JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_3, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllPersonBean allCarBean = gson.fromJson(jsonObject.toString(), AllPersonBean.class);
                allperson_list = allCarBean.getROWS_DETAIL();
                flag++;
                EventBus.getDefault().post("zy");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "正在读取数据", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void getB4() {
        JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST, api_4, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                AllTypeBean allCarBean = gson.fromJson(jsonObject.toString(), AllTypeBean.class);
                alltype_list = allCarBean.getROWS_DETAIL();

                flag++;
                EventBus.getDefault().post("zy");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "正在读取数据", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void dealData() {
        for (int i = 0; i < allcar_list.size(); i++) {
            for (int j = 0; j < allpeccancy_list.size(); j++) {
                if (allcar_list.get(i).getCarnumber().equals(allpeccancy_list.get(j).getCarnumber())) {
                    int count = allcar_list.get(i).getCount();
                    count++;
                    allcar_list.get(i).setCount(count);
                }
            }
        }


        for (int i = 0; i < allpeccancy_list.size(); i++) {
            for (int j = 0; j < allpeccancy_list.size(); j++) {
                if (allpeccancy_list.get(i).getCarnumber().equals(allpeccancy_list.get(j).getCarnumber())) {
                    int count = allpeccancy_list.get(i).getCount();
                    count++;
                    allpeccancy_list.get(i).setCount(count);
                }
            }
        }
        Map<String, Integer> mas = new HashMap<>();
        for (int i = 0; i < allpeccancy_list.size(); i++) {
            mas.put(allpeccancy_list.get(i).getCarnumber(), allpeccancy_list.get(i).getCount());
        }
        for (String key : mas.keySet()) {
            single_peccancy.add(new AllPeccancyBean.ROWSDETAILBean(key, mas.get(key)));
        }


        for (int i = 0; i < alltype_list.size(); i++) {
            for (int j = 0; j < allpeccancy_list.size(); j++) {
                if (alltype_list.get(i).getPcode().equals(allpeccancy_list.get(j).getPcode())) {
                    int count = alltype_list.get(i).getCount();
                    count++;
                    alltype_list.get(i).setCount(count);
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
        y.add((float) single_peccancy.size());
        y.add((float) (allcar_list.size() - single_peccancy.size()));
        map.put("x1", x);
        map.put("y1", y);


    }

    @Override
    public void getTu2() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        x.add("无重复违章");
        x.add("有重复违章");
        float[] floats = new float[2];
        for (int i = 0; i < single_peccancy.size(); i++) {
            if (single_peccancy.get(i).getCount() > 1) {
                floats[1]++;
            } else {
                floats[0]++;
            }
        }
        y.add(floats[0]);
        y.add(floats[1]);
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
        for (int i = 0; i < single_peccancy.size(); i++) {
            if (single_peccancy.get(i).getCount() < 3) {
                floats[0]++;
            } else if (single_peccancy.get(i).getCount() < 6) {
                floats[1]++;
            } else {
                floats[2]++;
            }
        }
        y.add(floats[0]*100/single_peccancy.size());
        y.add(floats[1]*100/single_peccancy.size());
        y.add(floats[2]*100/single_peccancy.size());
        map.put("x3", x);
        map.put("y3", y);


    }

    @Override
    public void getTu4() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats = new float[2];
        float[] floats1 = new float[2];
        float[] floats2 = new float[2];
        float[] floats3 = new float[2];
        float[] floats4 = new float[2];
        x.add("90后");
        x.add("80后");
        x.add("70后");
        x.add("60后");
        x.add("50后");
        y.add(floats);
        y.add(floats1);
        y.add(floats2);
        y.add(floats3);
        y.add(floats4);
        for (int i = 0; i < allcar_list.size(); i++) {
            int year = Integer.parseInt(allcar_list.get(i).getPcardid().substring(6, 10));
            if (allcar_list.get(i).getCount() == 0) {
                if (year > 1990) {
                    y.get(0)[0]++;
                } else if (year > 1980) {
                    y.get(1)[0]++;
                } else if (year > 1970) {
                    y.get(2)[0]++;
                } else if (year > 1960) {
                    y.get(3)[0]++;
                } else if (year > 1950) {
                    y.get(4)[0]++;
                }


            } else {
                if (year > 1990) {
                    y.get(0)[1]++;
                } else if (year > 1980) {
                    y.get(1)[1]++;
                } else if (year > 1970) {
                    y.get(2)[1]++;
                } else if (year > 1960) {
                    y.get(3)[1]++;
                } else if (year > 1950) {
                    y.get(4)[1]++;
                }
            }

        }
        float[] all = new float[5];

        for (int i = 0; i < y.size(); i++) {
            all[i] = y.get(i)[0] + y.get(i)[1];
        }

        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < floats.length; j++) {

                y.get(i)[j] = y.get(i)[j] * 100 / all[i];
            }
        }
        map.put("x4", x);
        map.put("y4", y);

    }

    @Override
    public void getTu5() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<float[]> y = new ArrayList<>();
        float[] floats = new float[2];
        float[] floats1 = new float[2];

        x.add("女性");
        x.add("男性");

        y.add(floats);
        y.add(floats1);

        for (int i = 0; i < allcar_list.size(); i++) {
//            int year = Integer.parseInt(allcar_list.get(i).getPcardid().substring(6, 10));
            int jundge = allcar_list.get(i).getPcardid().charAt(17);
            if (allcar_list.get(i).getCount() == 0) {

                if (jundge % 2 == 0) {
                    y.get(0)[0]++;
                } else {
                    y.get(1)[0]++;
                }


            } else {
                if (jundge % 2 == 0) {
                    y.get(0)[1]++;
                } else {
                    y.get(1)[1]++;
                }
            }

        }
        float[] all = new float[2];

        for (int i = 0; i < y.size(); i++) {
            all[i] = y.get(i)[0] + y.get(i)[1];
        }

        for (int i = 0; i < y.size(); i++) {
            for (int j = 0; j < floats.length; j++) {

                y.get(i)[j] = y.get(i)[j] * 100 / all[i];
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
        for (int i = 0; i < allpeccancy_list.size(); i++) {
            int hour = Integer.parseInt(allpeccancy_list.get(i).getDatetime().substring(11, 13));
            Log.i("1datatat", "getTu6: "+allpeccancy_list.get(i).getDatetime());
            if (hour >= 22) {
                floats[11]++;
            } else if (hour >= 20) {
                floats[10]++;
            } else if (hour >= 18) {
                floats[9]++;
            } else if (hour >= 16) {
                floats[8]++;
            } else if (hour >= 14) {
                floats[7]++;
            } else if (hour >= 12) {
                floats[6]++;
            } else if (hour >= 10) {
                floats[5]++;
            } else if (hour >= 8) {
                floats[4]++;
            } else if (hour >= 6) {
                floats[3]++;
            } else if (hour >= 4) {
                floats[2]++;
            } else if (hour >= 2) {
                floats[1]++;
            } else if (hour >= 0) {
                floats[0]++;
            }
        }

        for (int i = 0; i < floats.length; i++) {
            y.add(floats[i] * 100 / allpeccancy_list.size());
            x.add(i * 2 + ":00:01-" + (i * 2 + 1) + ":00:00");
        }


        map.put("x6", x);
        map.put("y6", y);


    }

    @Override
    public void getTu7() {
        Collections.sort(alltype_list, new Comparator<AllTypeBean.ROWSDETAILBean>() {
            @Override
            public int compare(AllTypeBean.ROWSDETAILBean o1, AllTypeBean.ROWSDETAILBean o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        ArrayList<String> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        float all = 0;
        for (int i = 0; i < 10; i++) {
            all += alltype_list.get(i).getCount();
        }

        for (int i = 10; i > -1; i--) {
            x.add(alltype_list.get(i).getPremarks());
            y.add(alltype_list.get(i).getCount() * 100 / all);
        }

        map.put("x7", x);
        map.put("y7", y);
    }
}
