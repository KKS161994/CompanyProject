package kaustav.test.android.avsheshversion2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class ProfileSetupBuyer extends ActionBarActivity {

    String MobNo, Passwd;
    EditText name,number,whatsapp_number,scrap_type,cmp_name,lnd_no;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_buyer);

        name = (EditText)findViewById(R.id.username);
        number = (EditText)findViewById(R.id.prim_mob_no);
        whatsapp_number=(EditText)findViewById(R.id.whatsapp_no);
        scrap_type=(EditText)findViewById(R.id.scrap_type);
        cmp_name = (EditText)findViewById(R.id.comp_name);
        lnd_no = (EditText)findViewById(R.id.land_no);
        update=(Button)findViewById(R.id.buy_update);
        Intent inSet= getIntent();
        Bundle t = inSet.getExtras();
        if(t!=null)
        {
            MobNo =(String) t.get("mob");
            Passwd =(String) t.get("pass");
        }
        number.setText(MobNo);
    }
}
