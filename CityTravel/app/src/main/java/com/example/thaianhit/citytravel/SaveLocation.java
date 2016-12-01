package com.example.thaianhit.citytravel;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SaveLocation  extends Fragment {

    List<PlaceLikeDTO> listPlace;
    private View myFragmentView;
    PlaceLikeDAO placeLikeDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.activity_save_location, container, false);
        Toolbar toolbar = (Toolbar) myFragmentView.findViewById(R.id.toolbarSave);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Saved");
        toolbar.inflateMenu(R.menu.menu_toolbar_save_location);


        mRecyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        placeLikeDAO = new PlaceLikeDAO(getActivity());
        placeLikeDAO.open();

        listPlace = placeLikeDAO.GetListHistoryLike();
        placeLikeDAO.close();
        // specify an adapter (see also next example)
        mAdapter = new SaveLocationAdapter(listPlace, getActivity());
        mRecyclerView.setAdapter(mAdapter);
//        recyclerView = (RecyclerView) myFragmentView.findViewById(R.id.recycler);
//
//        // If the size of views will not change as the data changes.
//        recyclerView.setHasFixedSize(true);
//
//        // Setting the LayoutManager.
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        DataRecyclerSearch a=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch b=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch c=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        DataRecyclerSearch d=new DataRecyclerSearch("9.0","861/90C Trần Xuân Soạn, P.Tân Hưng, Q.7",(float)1.5,"Khách sạn","","Khách sạn xanh");
//        listData.add(a);
//        listData.add(b);
//        listData.add(c);
//        listData.add(d);
//        // Setting the adapter.
//        adapter = new CustomRecyclerAdapterSearch(listData,getActivity());
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);

//        lvHistory = (ListView) myFragmentView.findViewById(R.id.lvHistory);
//
//        listHistory = new ArrayList<HistoryLikeDTO>();
//
//        //mở kết nối với database
//        historyLikeDAO = new HistoryLikeDAO(getActivity());
//        historyLikeDAO.open();
//
//        listHistory = historyLikeDAO.GetListHistoryLike();
//
////        adapter = new CustomHistoryAdapter(this,R.layout.custom_history_adapter,listHistory);
//        adapter = new CustomHistoryAdapter(getActivity(),R.layout.custom_history_adapter,listHistory);
//        lvHistory.setAdapter(adapter);
//
//        //lấy intent gọi Activity này
//        callerIntent = getActivity().getIntent();
//
////        ReceiveData();

        return myFragmentView;
    }

//    public void ReceiveData() {
//
//        if(RESQUEST_CODE == 0) {
//            //có intent rồi thì lấy Bundle dựa vào MyPackage
//            packageFromCaller = callerIntent.getBundleExtra("MyPackage");
//
//            //Có Bundle rồi thì lấy các thông số
//            int id = packageFromCaller.getInt("getID");
//            String tenDiaDiem = packageFromCaller.getString("getNameDiaDiem");
//            String tenDichVu = packageFromCaller.getString("getNameDichVu");
//            String thoiGianLike = packageFromCaller.getString("getThoigianLike");
//            String tenDiaChi = packageFromCaller.getString("getNameDiaChi");
//            String hinhAnhLike = packageFromCaller.getString("getHinhAnhDichVu");
//            String diemDanhGia = packageFromCaller.getString("getDiemDanhGia");
//
//            HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();
//            historyLikeDTO.set_id(id);
//            historyLikeDTO.setTenDiaDiem(tenDiaDiem);
//            historyLikeDTO.setTenDichVu(tenDichVu);
//            historyLikeDTO.setThoiGianLike(thoiGianLike);
//            historyLikeDTO.setTenDiaChi(tenDiaChi);
//            historyLikeDTO.setHinhAnhDichVu(hinhAnhLike);
//            historyLikeDTO.setDiemDanhGia(diemDanhGia);
//
//            //tiến hành xử lý
//            listHistory.add(historyLikeDTO);
//
//            boolean kiemtra = historyLikeDAO.AddHistoryLike(historyLikeDTO);
//            if (kiemtra) {
//                //Toast.makeText(HistoryActivity.this, "Đã thêm vào Lịch sử yêu thích",Toast.LENGTH_LONG).show();
//                adapter.notifyDataSetChanged();
//            }
//            //Đăng kí context menu cho then listView
//            registerForContextMenu(lvHistory);
//        }
//
//        if(RESQUEST_CODE == 1) {
//
////            HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();
////            historyLikeDTO = listHistory.get(0);
////
////            historyLikeDAO.DeleteItemHistoryLike(historyLikeDTO);
////            adapter.remove(historyLikeDTO);
////            adapter.notifyDataSetChanged();
//
//
//        }
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getActivity().getMenuInflater();
//        inflater.inflate(R.menu.menu_context_history,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itemDelete:
//                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                HistoryLikeDTO historyDTO = listHistory.get(menuInfo.position);
//                historyLikeDAO.DeleteItemHistoryLike(historyDTO);
//                adapter.remove(historyDTO);
//                adapter.notifyDataSetChanged();
//                ;break;
//        }
//        return true;
//    }

}
