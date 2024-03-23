package com.example.paymark;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private List<Record> records;
    public RecordAdapter(List<Record> records) {
        this.records = records;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Record record = records.get(position);
        holder.typeTextView.setText(record.getType());
        holder.priceTextView.setText(String.valueOf(record.getPrice()));
        holder.infoTextView.setText(record.getInfo());
        holder.timestampTextView.setText(formatTimestamp(record.getTimestamp()));

        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                records.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
            }
        });
    }
    @Override
    public int getItemCount() {
        return records.size();
    }

    public void addRecord(Record record) {
        records.add(record);
        notifyItemInserted(records.size() - 1);
    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView;
        TextView priceTextView;
        TextView infoTextView;
        TextView timestampTextView;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.type_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            infoTextView = itemView.findViewById(R.id.info_text_view);
            timestampTextView = itemView.findViewById(R.id.timestamp_text_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}