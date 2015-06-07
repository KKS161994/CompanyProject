package kaustav.test.android.avsheshversion2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ProfileSetupSeller extends ActionBarActivity {

    String MobNo, Passwd;
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_seller);

        ed1 = (EditText)findViewById(R.id.username);
        ed2 = (EditText)findViewById(R.id.prim_mob_no);
        ed3 = (EditText)findViewById(R.id.comp_name);
        ed4 = (EditText)findViewById(R.id.land_no);
        ed5 = (EditText)findViewById(R.id.addr_line_1);
        ed6 = (EditText)findViewById(R.id.addr_line_2);
        ed7 = (EditText)findViewById(R.id.addr_city);
        update=(Button)findViewById(R.id.sel_prof_set);

        Intent inSet= getIntent();
        Bundle t = inSet.getExtras();

        if(t!=null)
        {
            MobNo =(String) t.get("mob");
            Passwd =(String) t.get("pass");
        }

        ed2.setText(MobNo);
    }
}
