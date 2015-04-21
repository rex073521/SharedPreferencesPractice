package idv.practice0310.assetspractice;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Button btnLogin,btnCancel;
    private EditText editTxtID,editTxtPass;
    private CheckBox chkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        loadPref();

    }

    protected void findviews(){
        btnLogin= (Button)findViewById(R.id.btnLogin);
        btnCancel= (Button)findViewById(R.id.btnCancel);
        editTxtID = (EditText)findViewById(R.id.editTxtID);
        editTxtPass = (EditText)findViewById(R.id.editTxtPass);
        chkBox = (CheckBox)findViewById(R.id.chkBox);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTxtID.setText("");
                editTxtPass.setText("");
            }
        });

    }

    private void savePref() {
        SharedPreferences preferences = getSharedPreferences("SaveData", MODE_PRIVATE);
        String id,psd;
        try {
            id = editTxtID.getText().toString();
            psd = editTxtPass.getText().toString();
        } catch (NullPointerException npe) {
            Toast.makeText(this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
            Log.e("MainActivity", npe.toString());
            return;
        }

        boolean isSave = chkBox.isChecked();

        if (isSave) {
            preferences.edit()
                    .putString("ID", id)
                    .putString("Password", psd)
                    .putBoolean("isSave", isSave)
                    .apply();
            Toast.makeText(this, "儲存資料", Toast.LENGTH_SHORT).show();
        }
            else
            Toast.makeText(this, "不存", Toast.LENGTH_SHORT).show();

    }

    private void loadPref() {
        SharedPreferences preferences = getSharedPreferences("SaveData", MODE_PRIVATE);
        String id = preferences.getString("ID", "");
        String psd = preferences.getString("Password", "");
        editTxtID.setText(id);
        editTxtPass.setText(psd);

        boolean isSave = preferences.getBoolean("isSave", true);
        if (isSave)
            chkBox.setChecked(true);
        else
            chkBox.setChecked(false);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
