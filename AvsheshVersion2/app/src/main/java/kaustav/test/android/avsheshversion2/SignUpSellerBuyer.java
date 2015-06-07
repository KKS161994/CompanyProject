package kaustav.test.android.avsheshversion2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpSellerBuyer extends ActionBarActivity {

    TextView t1;
    EditText ed1, ed2, ed3;
    Button b1;
    CheckBox cb1;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_seller_buyer);

        t1 = (TextView)findViewById(R.id.signup_as);
        ed1 = (EditText)findViewById(R.id.mob_no_editText);
        ed2 = (EditText)findViewById(R.id.pass_editText);
        ed3 = (EditText)findViewById(R.id.cnf_pass_editText);
        b1 = (Button)findViewById(R.id.start_sign_up);
        cb1 = (CheckBox)findViewById(R.id.checkBox_terms);

        cb1.setChecked(true);

        Intent iType= getIntent();
        Bundle t = iType.getExtras();

        if(t!=null)
        {
            j =(int) t.get("type");
        }

        if ( j == 0)
        {
            t1.setText("Sign Up As Seller");
        }
        else if ( j == 1)
        {
            t1.setText("Sign Up As Buyer");
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked())
                {
                    String mob = ed1.getText().toString();
                    if (mob.length() == 10 && isNumeric(mob))
                    {
                        if (ed2.getText().toString().length() >= 0)
                        {
                            if (ed2.getText().toString().equals(ed3.getText().toString()))
                            {
                                String MobNo = ed1.getText().toString();
                                String Pass = ed2.getText().toString();
                                if ( j == 1)
                                {
                                    Intent intentB = new Intent(SignUpSellerBuyer.this, ProfileSetupBuyer.class);
                                    intentB.putExtra("mob", MobNo);
                                    intentB.putExtra("pass", Pass);
                                    startActivity(intentB);
                                }
                                else if ( j == 0)
                                {
                                    Intent intentS = new Intent(SignUpSellerBuyer.this, ProfileSetupSeller.class);
                                    intentS.putExtra("mob", MobNo);
                                    intentS.putExtra("pass", Pass);
                                    startActivity(intentS);
                                }
                            }
                            else
                            {
                                Toast.makeText(SignUpSellerBuyer.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                                ed2.setText("");
                                ed3.setText("");
                            }
                        }
                        else
                        {
                            Toast.makeText(SignUpSellerBuyer.this, "Password too short Minimum 0 characters", Toast.LENGTH_SHORT).show();
                            ed2.setText("");
                            ed3.setText("");
                        }
                    }
                    else
                    {
                        if(mob.length()==0){
                            Toast.makeText(SignUpSellerBuyer.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUpSellerBuyer.this, "Enter correct mobile number", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(SignUpSellerBuyer.this, "Please accept the Terms & Conditions to Sign Up", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
