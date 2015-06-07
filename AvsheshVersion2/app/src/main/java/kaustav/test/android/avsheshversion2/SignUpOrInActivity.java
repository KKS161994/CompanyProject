package kaustav.test.android.avsheshversion2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class SignUpOrInActivity extends ActionBarActivity {

    Button b1, b2;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_in);

        fa = this;

        b1 = (Button)findViewById(R.id.sign_up_opt);
        b2 = (Button)findViewById(R.id.sign_in_opt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpOrInActivity.this, SignUpActivity.class);
                startActivity(intent); // GOTO Sign In

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpOrInActivity.this, SignInActivity.class);
                startActivity(intent); //GOTO Sign Up

            }
        });
    }
}