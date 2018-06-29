package diot.cdac.com;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private CardView crvSlot1,crvSlot2;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private TextView txtslt, txtb, txts;
    private String sl_name;
    private long book_status, slot_status;

    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crvSlot1 = findViewById(R.id.crv_slot1);
        crvSlot2=findViewById(R.id.crv_slot2);

        txtslt = findViewById(R.id.txt_slot1);


        bar = findViewById(R.id.prgBar);
        bar.setVisibility(View.VISIBLE);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("cdac_slots");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                bar.setVisibility(View.GONE);
                getSlotStatus(dataSnapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                bar.setVisibility(View.VISIBLE);
                getSlotStatus(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getSlotStatus(DataSnapshot dataSnapshot) {
        bar.setVisibility(View.GONE);
        SlotData slotData = dataSnapshot.getValue(SlotData.class);
        sl_name = slotData.getsl_name();
        book_status = slotData.getBook_status();
        switch (sl_name){
            case "slot1":
                if(book_status==0){
                    crvSlot1.setCardBackgroundColor(getResources().getColor(R.color.colorParkSlotEmpty));
                }
                else {
                    crvSlot1.setCardBackgroundColor(getResources().getColor(R.color.colorParkSlotFull));
                }
                break;
            case "slot2":
                if(book_status==0){
                    crvSlot2.setCardBackgroundColor(getResources().getColor(R.color.colorParkSlotEmpty));
                }
                else {
                    crvSlot2.setCardBackgroundColor(getResources().getColor(R.color.colorParkSlotFull));
                }
        }
    }
}
