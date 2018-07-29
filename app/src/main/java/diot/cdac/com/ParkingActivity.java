package diot.cdac.com;
/**
 * Created by Atul Upadhye
 **/
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ParkingActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView rcv;
    private RecyclerViewAdapter adapter;
    private ProgressBar bar;


    private List<SlotData> slotData;

    @Override
    public void onBackPressed() {
        finish();
    }

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("cdac_slots");

        slotData = new ArrayList<>();

        rcv = findViewById(R.id.rcvMain);
        bar =findViewById(R.id.progressBar);

        bar.setVisibility(View.VISIBLE);
        //RecyclerViewAdapter adapter =new RecyclerViewAdapter(this,slotData);
        rcv.setLayoutManager(new GridLayoutManager(this, 3));
        // rcv.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bar.setVisibility(View.GONE);
                getSlotData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                doAutoRefresh();
//                getSlotChangedData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void getSlotChangedData(DataSnapshot dataSnapshot) {
        SlotData slotDataDetails = dataSnapshot.getValue(SlotData.class);
        String sl_name = slotDataDetails.getsl_name();
        Long book_status = slotDataDetails.getBook_status();
        Long slot_status = slotDataDetails.getSlot_status();
        String macid = slotDataDetails.getMac_id();

        SlotData data = new SlotData(sl_name, macid, book_status, slot_status);
        //slotData.clear();
        slotData.add(data);
        adapter.notifyDataSetChanged();


    }

    private void doAutoRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                finish();
//                startActivity(new Intent(getApplicationContext(), ParkingActivity.class));
                bar.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(),ParkingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                bar.setVisibility(View.GONE);
                startActivity(i);
            }
        }, 100);
    }

    private void getSlotData(DataSnapshot dataSnapshot) {
        SlotData slotDataDetails = dataSnapshot.getValue(SlotData.class);
        String sl_name = slotDataDetails.getsl_name();
        Long book_status = slotDataDetails.getBook_status();
        Long slot_status = slotDataDetails.getSlot_status();
        String macid = slotDataDetails.getMac_id();

        SlotData data = new SlotData(sl_name, macid, book_status, slot_status);
        slotData.add(data);

        adapter = new RecyclerViewAdapter(this, slotData);

        adapter.notifyDataSetChanged();
        rcv.setAdapter(adapter);
    }
}
