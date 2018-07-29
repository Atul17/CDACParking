package diot.cdac.com;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    long b;
    int pos;
    private List<SlotData> slotDataList;

    public RecyclerViewAdapter(Context mContext, List<SlotData> slotDataList) {
        this.mContext = mContext;
        this.slotDataList = slotDataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        view = mLayoutInflater.inflate(R.layout.row_item_car, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_slot_no.setText(slotDataList.get(position).getsl_name());
        holder.txt_mac.setText(slotDataList.get(position).getMac_id());
        b = slotDataList.get(position).getBook_status();
        Resources res = holder.itemView.getContext().getResources();

//        holder.setIsRecyclable(false);


        if (b == 1) {
            holder.cardView.setCardBackgroundColor(res.getColor(R.color.colorParkSlotFull));
        } else {
            holder.cardView.setCardBackgroundColor(res.getColor(R.color.colorParkSlotEmpty));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = (String) holder.tv_slot_no.getText();
                final String macid = (String) holder.txt_mac.getText();
                //Toast.makeText(v.getContext(), "Clicked  " + holder.tv_slot_no.getText(), Toast.LENGTH_SHORT).show();
                Context c = v.getContext();
                Intent i = new Intent(c, SlotEditActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("SlotValue", name);
                i.putExtra("MacID", macid);
                c.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return slotDataList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_slot_no, txt_mac;
        public CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            tv_slot_no = itemView.findViewById(R.id.txt_slot_data);
            txt_mac = itemView.findViewById(R.id.txt_mac);
            cardView = itemView.findViewById(R.id.crvMain);
        }
    }
}
