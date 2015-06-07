package kaustav.test.android.avsheshversion2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kaustav on 6/3/2015.
 */
public class SellerMyItemList extends ArrayAdapter<String> {

    private final Activity context;
    private final Integer[] pro_stat_img;
    private final String[] pro_stat;
    private final String[] pro_btn;
    private final Integer[] pro_img;
    private final String[] pro_desc;
    private final String[] pro_post_date;
    private final String[] pro_quan;
    private final String[] pro_price;

    public SellerMyItemList(Activity context, Integer[] pro_stat_img, String[] pro_stat, String[] pro_butn, Integer[] pro_img, String[] pro_desc, String[] pro_post_date, String[] pro_quantity, String[] pro_price) {
        super(context, R.layout.my_item_list_single, pro_desc);
        this.context = context;
        this.pro_stat_img = pro_stat_img;
        this.pro_stat = pro_stat;
        this.pro_btn = pro_butn;
        this.pro_img = pro_img;
        this.pro_desc = pro_desc;
        this.pro_post_date = pro_post_date;
        this.pro_quan = pro_quantity;
        this.pro_price = pro_price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.my_item_list_single, null, true);

        ImageView proStatLight = (ImageView)rowView.findViewById(R.id.stat_light_btn);
        TextView proStatText = (TextView)rowView.findViewById(R.id.prod_stat);
        Button statChangeButn = (Button)rowView.findViewById(R.id.stat_change_butn);
        ImageView proImage = (ImageView)rowView.findViewById(R.id.prod_img);
        TextView proDesc = (TextView)rowView.findViewById(R.id.pro_desc);
        TextView proPostDt = (TextView)rowView.findViewById(R.id.pro_post_dt);
        TextView proQuantity = (TextView)rowView.findViewById(R.id.myitem_quantity);
        TextView proPrice = (TextView)rowView.findViewById(R.id.myitem_price);



        proStatLight.setImageResource(pro_stat_img[position]);
        proStatText.setText(pro_stat[position]);
        statChangeButn.setText(pro_btn[position]);
        proImage.setImageResource(pro_img[position]);
        proDesc.setText(pro_desc[position]);
        proPostDt.setText(pro_post_date[position]);
        proQuantity.setText(pro_quan[position]);
        proPrice.setText(pro_price[position]);

        return rowView;
    }
}