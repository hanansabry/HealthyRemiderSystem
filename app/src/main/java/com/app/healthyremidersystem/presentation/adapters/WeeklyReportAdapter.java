package com.app.healthyremidersystem.presentation.adapters;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.model.Medicine;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeeklyReportAdapter extends RecyclerView.Adapter<WeeklyReportAdapter.MedicineViewHolder> {
    private List<Medicine> medicineList;
    private boolean isReport;

    public WeeklyReportAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public WeeklyReportAdapter.MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_reminder_report_item, null);
        return new WeeklyReportAdapter.MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyReportAdapter.MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        holder.medicineNameTextView.setText(medicine.getMedicineName());
        holder.medicineImageVIew.setImageURI(Uri.parse(medicine.getMedicineImageUri()));
        holder.percentTextView.setText(String.format(Locale.US, "Taken: %d/%d", medicine.getTakenNumber(), medicine.getScheduledTimes().size()));
        holder.remainingTextView.setText(String.format(Locale.US, "Remaining: %d/%d", medicine.getRemainingNumber(), medicine.getScheduledTimes().size()));
        holder.medicineStatusTextView.setText(medicine.getMedicineStatus().name());
        if (medicine.getMedicineStatus().equals(Medicine.MedicineStatus.PERFECT)) {
            holder.medicineStatusTextView.setBackgroundColor(Color.GREEN);
        } else if (medicine.getMedicineStatus().equals(Medicine.MedicineStatus.ACCEPTED)) {
            holder.medicineStatusTextView.setBackgroundColor(Color.BLUE);
        } else if (medicine.getMedicineStatus().equals(Medicine.MedicineStatus.CARELESS)) {
            holder.medicineStatusTextView.setBackgroundColor(Color.RED);
        }
        if (medicine.getRemainingNumber() == 2) {
            holder.contentView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.yellow));
        }
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
        @BindView(R.id.percentTextView)
        TextView percentTextView;
        @BindView(R.id.remainingTextView)
        TextView remainingTextView;
        @BindView(R.id.medicineStatusTextView)
        TextView medicineStatusTextView;
        @BindView(R.id.contentView)
        View contentView;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
