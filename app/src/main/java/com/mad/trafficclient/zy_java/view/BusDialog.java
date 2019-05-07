package com.mad.trafficclient.zy_java.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 昭阳 on 2019/5/7.
 */
public class BusDialog extends AlertDialog {
    private List<Integer> list;
    private Context context;
    private DiaAdapter adapter;
    private ViewHolder viewHolder;

    public BusDialog(Context context) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.busdialog, null);
        setView(view);
        viewHolder = new ViewHolder(view);
        list = new ArrayList<>();
        adapter = new DiaAdapter();
        viewHolder.busdialog_list.setAdapter(adapter);
        viewHolder.bt_dimss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    protected BusDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public void notAdatter(List<Integer> list) {
        this.list = list;
        adapter.notifyDataSetChanged();
        int all = 0;
        for (int i = 0; i < list.size(); i++) {
            all += list.get(i);
        }
        viewHolder.dialog_allperson.setText(all + "");

    }

    public static
    class ViewHolder {
        public View rootView;
        public ListView busdialog_list;
        public TextView dialog_allperson;
        public Button bt_dimss;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.busdialog_list = (ListView) rootView.findViewById(R.id.busdialog_list);
            this.dialog_allperson = (TextView) rootView.findViewById(R.id.dialog_allperson);
            this.bt_dimss = (Button) rootView.findViewById(R.id.bt_dimss);
        }

    }


    class DiaAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_dialog_bus, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.person.setText(list.get(position) + "");
            viewHolder.tx_bianhao.setText((position + 1) + "");
            viewHolder.tx_xuhao.setText((position + 1) + "");
            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView tx_xuhao;
            public TextView tx_bianhao;
            public TextView person;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tx_xuhao = (TextView) rootView.findViewById(R.id.tx_xuhao);
                this.tx_bianhao = (TextView) rootView.findViewById(R.id.tx_bianhao);
                this.person = (TextView) rootView.findViewById(R.id.person);
            }

        }
    }
}
