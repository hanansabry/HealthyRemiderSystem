package com.app.healthyremidersystem.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.MedicineViewHolder> {

    private List<Medicine> medicineList;
    private MedicinesCallback medicinesCallback;
    private boolean isReport;

    public MedicinesAdapter(List<Medicine> medicineList, MedicinesCallback medicinesCallback) {
        this.medicineList = medicineList;
        this.medicinesCallback = medicinesCallback;
        this.isReport = isReport;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_reminder_item, null);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        holder.medicineNameTextView.setText(medicine.getMedicineName());
        holder.dozeTextView.setText(String.format(Locale.US, "%d time/day", medicine.getNumberPerDay()));
        holder.deleteItemButton.setOnClickListener(v -> {
            medicineList.remove(position);
            medicinesCallback.removeMedicine(String.valueOf(medicine.getMedicineId()));
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.medicineImageVIew)
        ImageView medicineImageVIew;
        @BindView(R.id.medicineNameTextView)
        TextView medicineNameTextView;
        @BindView(R.id.dozeTextView)
        TextView dozeTextView;
        @BindView(R.id.deleteItemButton)
        ImageButton deleteItemButton;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MedicinesCallback {
        void removeMedicine(String medicineId);
    }
}
