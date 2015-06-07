package kaustav.test.android.avsheshversion2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerMyItems extends Fragment {

    ListView myitem_list;
    Integer[] pro_stat_img = {
            R.drawable.live_butn,
            R.drawable.offline_butn,
            R.drawable.live_butn,
            R.drawable.offline_butn,
            R.drawable.live_butn,
            R.drawable.live_butn
    };
    String[] pro_stat = {
            "Posting is active",
            "Posting is deactive",
            "Posting is active",
            "Posting is deactive",
            "Posting is active",
            "Posting is active"
    };
    String[] pro_butn = {
            "Pause",
            "Start",
            "Pause",
            "Start",
            "Pause",
            "Pause"
    };
    Integer[] pro_img = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6
    };
    String[] pro_desc = {
            "Iron Scrap",
            "Plastic",
            "AC machine",
            "Battery",
            "Copper Scrap",
            "Aluminium Scrap"
    };
    String[] pro_post_date = {
            "03/06/2015",
            "03/05/2015",
            "03/04/2015",
            "20/03/2015",
            "09/03/2015",
            "03/03/2015"
    };
    String[] pro_quantity = {
            "Quantity : 100kg",
            "Quantity : 200kg",
            "Quantity : 5",
            "Quantity : 100",
            "Quantity : 200kg",
            "Quantity : 50kg"
    };
    String[] pro_price = {
            "Price/kg : 21",
            "Price/kg : 16",
            "Price : 10000",
            "Price : 1000",
            "Price/kg : 410",
            "Price/kg : 110"
    };



    public SellerMyItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_my_items, container, false);

        SellerMyItemList adapter = new SellerMyItemList(getActivity(), pro_stat_img, pro_stat, pro_butn, pro_img, pro_desc, pro_post_date, pro_quantity, pro_price);
        myitem_list = (ListView)view.findViewById(R.id.sel_my_item_list);
        myitem_list.setAdapter(adapter);
        myitem_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
