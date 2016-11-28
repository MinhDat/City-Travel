package com.example.thaianhit.citytravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuuBao on 28-Nov-16.
 */

public class HistoryActivity extends AppCompatActivity {

    ListView lvHistory;

    List<HistoryLikeDTO> listHistory;

    CustomHistoryAdapter adapter;

    HistoryLikeDAO historyLikeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = (ListView) findViewById(R.id.lvHistory);

        listHistory = new ArrayList<HistoryLikeDTO>();

        //mở kết nối với database
        historyLikeDAO = new HistoryLikeDAO(this);
        historyLikeDAO.open();

        listHistory = historyLikeDAO.GetListHistoryLike();

        adapter = new CustomHistoryAdapter(this,R.layout.custom_history_adapter,listHistory);
        lvHistory.setAdapter(adapter);

        //lấy intent gọi Activity này
        Intent callerIntent = getIntent();

        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");

        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        int id = packageFromCaller.getInt("getID");
        String tenDiaDiem = packageFromCaller.getString("getNameDiaDiem");
        String tenDichVu = packageFromCaller.getString("getNameDichVu");
        String tenDiaChi = packageFromCaller.getString("getNameDiaChi");

        HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();
        historyLikeDTO.set_id(id);
        historyLikeDTO.setTenDiaDiem(tenDiaDiem);
        historyLikeDTO.setTenDichVu(tenDichVu);
        historyLikeDTO.setTenDiaChi(tenDiaChi);

        //tiến hành xử lý
        listHistory.add(historyLikeDTO);

        boolean kiemtra = historyLikeDAO.AddHistoryLike(historyLikeDTO);
        if(kiemtra){
            Toast.makeText(HistoryActivity.this, "Đã thêm vào Lịch sử yêu thích",Toast.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }

        //Đăng kí context menu cho then listView
        registerForContextMenu(lvHistory);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_history,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDelete:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                HistoryLikeDTO historyDTO = listHistory.get(menuInfo.position);
                historyLikeDAO.DeleteItemHistoryLike(historyDTO);
                adapter.remove(historyDTO);
                adapter.notifyDataSetChanged();
                ;break;
        }
        return true;
    }
}
