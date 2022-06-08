package com.example.json1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHoldler> {

    TextView title, rate, patients, hospitalname, hospitaladdress, region;
    Context mContext;
    ArrayList<DataList> data;

    public DataAdapter(Context mContext, ArrayList<DataList> data) {
        this.mContext = mContext;
        this.data = data;
    }
    public class ViewHoldler extends RecyclerView.ViewHolder {



        public ViewHoldler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            rate = itemView.findViewById(R.id.item_rate);
            patients = itemView.findViewById(R.id.item_patients);
            region = itemView.findViewById(R.id.item_region);
            hospitalname = itemView.findViewById(R.id.item_hospitalname);
            hospitaladdress = itemView.findViewById(R.id.item_hospitaladdress);

        }
        public void setItem(emergence item){
            title.setText(item.region);
            patients.setText(item.patients +"명");
            hospitalname.setText(item.hospital_name);
            hospitaladdress.setText(item.hospital_address);
        }
    }
    ArrayList<emergence> em = new ArrayList<emergence>();
    @NonNull
    @Override
    public DataAdapter.ViewHoldler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list,parent,false);
        return new ViewHoldler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHoldler holder, int position) {

        emergence emdata = em.get(position);
        holder.setItem(emdata);
    }

    @Override
    public int getItemCount() {
        return (em != null? em.size() : 0);
    }
    public void setItems(ArrayList<emergence> items){
        this.em = items;
    }



}
