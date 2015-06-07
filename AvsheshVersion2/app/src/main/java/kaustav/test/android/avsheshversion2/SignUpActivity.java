package kaustav.test.android.avsheshversion2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class SignUpActivity extends ActionBarActivity {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        b1 = (Button)findViewById(R.id.sign_up_seller);
        b2 = (Button)findViewById(R.id.sign_up_buyer);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentS = new Intent(SignUpActivity.this, SignUpSellerBuyer.class);
                intentS.putExtra("type", 0);
                startActivity(intentS);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(SignUpActivity.this, SignUpSellerBuyer.class);
                intentB.putExtra("type", 1);
                startActivity(intentB);
            }
        });
    }
}