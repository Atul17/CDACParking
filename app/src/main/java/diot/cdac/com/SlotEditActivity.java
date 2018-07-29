package diot.cdac.com;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by Atul Upadhye
 **/
public class SlotEditActivity extends AppCompatActivity {

    private TextView txt_slot_title;
    private TextInputEditText inputEditText;
    private Button updateBtn,deleteBtn;

    public String data,macid;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_edit);

        data = getIntent().getStringExtra("SlotValue");
        macid=getIntent().getStringExtra("MacID");

       // txt_slot_title=findViewById(R.id.txt_slot_title);
       // txt_slot_title.setText(macid);
        inputEditText=findViewById(R.id.edt_slt_name);
        inputEditText.setText(data);
        updateBtn=findViewById(R.id.btn_update);
        deleteBtn=findViewById(R.id.btn_delete);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("cdac_slots");

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputEditText.getText().toString();
                databaseReference.child(macid).child("sl_name").setValue(data);
                //finish();
                //startActivity(new Intent(getApplicationContext(),ParkingActivity.class));
                Intent i = new Intent(getApplicationContext(),ParkingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(macid).removeValue();
                Toast.makeText(getApplicationContext(),"Slot Deleted",Toast.LENGTH_SHORT).show();
                //finish();

                //startActivity(new Intent(getApplicationContext(),ParkingActivity.class));
                Intent i = new Intent(getApplicationContext(),ParkingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });




    }
}
