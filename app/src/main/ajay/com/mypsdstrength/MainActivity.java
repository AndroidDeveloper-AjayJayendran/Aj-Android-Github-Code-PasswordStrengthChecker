package ajay.com.mypsdstrength;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    TextView et2;
    Button b;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    ProgressBar pb;
    CheckBox c;
    int len = 0;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et1 = (EditText)findViewById(R.id.e1);
        et2 = (TextView)findViewById(R.id.e2);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
        c = (CheckBox)findViewById(R.id.c1);
        b = (Button)findViewById(R.id.b);
        t = (TextView) findViewById(R.id.t3);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayUseLogoEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et1.getText().toString();
                if(et1.getText().toString().length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("It seems,you have entered an Empty Value..\nPlease Enter non-Empty value.!")
                            .setCancelable(false)
                            .setPositiveButton("I Understand", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Password Error");
                    alert.show();
                }
                else{
                    final int k = ope(s);
                    pb.setProgress(k);
                    t.setText("Score\t" + " -> " + k);
                    if(k >=1 && k<26){
                        et2.setText("Very Weak");
                    }
                    if(k>=26 && k<=50){
                        et2.setText("Good");
                    }
                    if(k>50 && k<76){
                        et2.setText("Strong");
                    }
                    if (k >= 76 && k <=100){
                        et2.setText("Very Strong");
                    }
                }
            }
        });
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    et1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    et1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    int ope(String s){
        len = 0;
        numOfChar(s);
        upperCaseLet(s);
        lowerCaseLet(s);
        numOnly(s);
        letterOnly(s);
        symOnly(s);
        numbers(s);
        Findrepeter(s);
        consecUpper(s);
        consecLower(s);
        consecNumber(s);
        seqChar(s);
        if(len > 100){
            len = (len*50)/100;
        }
        return len;
    }
    void numOfChar(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if((s.charAt(i)>='A' && s.charAt(i)<='Z')||(s.charAt(i) >='a' &&s.charAt(i)<='z')){
                t++;
            }
        }
        len += t*4;
    }
    void upperCaseLet(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if(s.charAt(i) >='A' && s.charAt(i)<='Z'){
                t++;
            }
        }
        len += ((s.length()-t)*2);
    }
    void lowerCaseLet(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if(s.charAt(i) >= 'a' && s.charAt(i)<='z'){
                t++;
            }
        }
        len += ((s.length()-t)*2);
    }
    void numOnly(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if(s.charAt(i) >='0' && s.charAt(i)<='9'){
                t++;
            }
        }
        len += t*4;
    }
    void symOnly(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if((!(s.charAt(i) >='0' && s.charAt(i)<='9'))&&(!(s.charAt(i) >='A' && s.charAt(i)<='Z'))
                    &&(!(s.charAt(i) >='a' && s.charAt(i)<='z'))){
                t++;
            }
        }
        len += t*6;
    }
    void letterOnly(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if((s.charAt(i)>='A' && s.charAt(i)<='Z')||(s.charAt(i) >='a' &&s.charAt(i)<='z')){
                t++;
            }
        }
        len -= t;
    }
    void numbers(String s){
        int i=0,t=0;
        for(i=0;i<s.length();i++){
            if(s.charAt(i) >='0' && s.charAt(i)<='9'){
                t++;
            }
        }
        len -= t;
    }
    void Findrepeter(String s){
        int i,j,t=1;
        boolean flag = false;
        for(i=0;i<s.length();i++){
            for(j=i+1;j<s.length();j++){
                if(s.charAt(i) == s.charAt(j)){
                    t++;
                    flag = true;
                }
            }
        }
        len -= t;
    }
    void consecUpper(String s){
        int i,j,t=1;
        boolean flag = false;
        for(i=0;i<s.length()-1;i++){
            if(s.charAt(i) >= 'A' && s.charAt(i) <='Z'){
                if(s.charAt(i) == s.charAt(i+1)){
                    t++;
                    flag = true;
                }
            }
        }
        if(!flag)
            t=0;
        len -= (t*2);
    }
    void consecLower(String s){
        int i,j,t=1;
        boolean flag = false;
        for(i=0;i<s.length()-1;i++){
            if(s.charAt(i) >= 'a' && s.charAt(i) <='z'){
                if(s.charAt(i) == s.charAt(i+1)){
                    t++;
                    flag = true;
                }
            }
        }
        if(!flag)
            t=0;
        len -= (t*2);
    }
    void consecNumber(String s){
        int i,j,t=1;
        boolean flag = false;
        for(i=0;i<s.length()-1;i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <='9'){
                if(s.charAt(i) == s.charAt(i+1)){
                    t++;
                    flag = true;
                }
            }
        }
        if(!flag)
            t=0;
        len -= (t*2);
    }
    void seqChar(String s){
        int i,j,t=0;
        boolean flag = false;
        for(i=0;i<s.length()-1;i++){
            int c = s.charAt(i) +1 ,d = s.charAt(i+1);
            if(c == d){
                t++;
                flag = true;
            }
        }
        len -= (t*3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Katavu(V.1.0)\nThe word 'katavu' which has derived from Tamil and it has meaning 'Password'\nBuild by Ajay Jayendran\nCo-Founder & CTO at pagelizt\n\nContact:tillmybest@gmail.com\nCompany:www.pagelizt.com")
                    .setCancelable(false)
                    .setPositiveButton("Wow", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                 });
            AlertDialog alert = builder.create();
            alert.setTitle("About Katavu");
            alert.setIcon(R.drawable.developer);
            alert.show();
        }
        if (id == R.id.action_refresh) {
            Toast.makeText(getApplicationContext(),"page has been refreshed",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);

        }
        if (id == R.id.action_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to close this application ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes,I do it", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();}
                    })
                    .setNegativeButton("No,I don't", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Exit");
            alert.setIcon(R.drawable.exit);
            alert.show();
        }
        if (id == R.id.action_req) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("We Strongly recommended this requirements\n1.)Minimum 8 characters in length\n2.)Contains 3/4 of the following items:\n\t- Uppercase Letters\n\t"+
                    "- Lowercase Letters\n\t" +
                    "- Numbers\n\t" +
                    "- Symbols")
                    .setCancelable(false)
                    .setPositiveButton("I Understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                           dialog.cancel();
                           }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Minimum Requirements");
            alert.setIcon(R.drawable.attractions);
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
