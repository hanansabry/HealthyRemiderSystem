package com.app.healthyremidersystem.presentation.adapters;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.model.ScheduledTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduledTimesAdapter extends RecyclerView.Adapter<ScheduledTimesAdapter.ScheduledTimeViewHolder> {

    private int numberPerDay;
    private ScheduledTimeCallback scheduledTimeCallback;
    private List<String> scheduledTimes = new ArrayList<>(numberPerDay);

    public ScheduledTimesAdapter(int numberPerDay, ScheduledTimeCallback callback) {
        this.numberPerDay = numberPerDay;
        this.scheduledTimeCallback = callback;
    }

    @NonNull
    @Override
    public ScheduledTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_scheduled_item, null);
        return new ScheduledTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduledTimeViewHolder holder, int position) {
        holder.numberOfTimeTextView.setText(String.valueOf(position+1));
        holder.setTimeButton.setOnClickListener(v -> {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(holder.context, (timePicker, selectedHour, selectedMinute) -> {
                String selectedHourS = (selectedHour < 10) ? "0" + selectedHour : String.valueOf(selectedHour);
                String selectedMinuteS = (selectedMinute < 10) ? "0" + selectedMinute : String.valueOf(selectedMinute);
                String time = String.format(Locale.US, "%s:%s", selectedHourS, selectedMinuteS);
                if (scheduledTimes.size() == numberPerDay) {
                    scheduledTimes.set(position, time);
                } else {
                    scheduledTimes.add(time);
                }
                holder.timeTextView.setText(time);
                if (scheduledTimes.size() == numberPerDay) {
                    scheduledTimeCallback.onAllTimesSet(scheduledTimes);
                }
            }, hour, minute, false);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        });
    }

    @Override
    public int getItemCount() {
        return numberPerDay;
    }

    class ScheduledTimeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.numberOfTimeTextView)
        TextView numberOfTimeTextView;
        @BindView(R.id.timeTextView)
        TextView timeTextView;
        @BindView(R.id.setTimeButton)
        ImageButton setTimeButton;

        private Context context;

        public ScheduledTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }
    }

    public interface ScheduledTimeCallback {
        void onAllTimesSet(List<String> scheduledTimes);
    }
}
