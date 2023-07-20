package ihu.dev.travelbook.ui.admin.dashboard;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ihu.dev.travelbook.R;
import ihu.dev.travelbook.roomdb.TripsEntity;

public class DashboardRecyclerViewAdapter extends RecyclerView.Adapter<DashboardRecyclerViewAdapter.DashboardHolder> {
    private List<TripsEntity> tripsEntityList = new ArrayList<>();
    private OnItemClickListener listener;

    @SuppressLint("NotifyDataSetChanged")
    public void setTrips(List<TripsEntity> tripsEntityList) {
        this.tripsEntityList = tripsEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardRecyclerViewAdapter.DashboardHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trips_cardview, viewGroup, false);

        return new DashboardRecyclerViewAdapter.DashboardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewAdapter.DashboardHolder holder, int position) {
        TripsEntity trip = tripsEntityList.get(position);
        //holder.id.setText(String.valueOf(trip.getTrip_id()));
        holder.city.setText(trip.getTown_name());
        holder.country.setText(trip.getCountry_name());
        holder.duration.setText(String.valueOf(trip.getDuration()));
        holder.type.setText(trip.getType());
        holder.price.setText(String.valueOf(trip.getPrice()));
    }


    @Override
    public int getItemCount() {
        return tripsEntityList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TripsEntity getTrip(int position) {
        return tripsEntityList.get(position);

    }


    public interface OnItemClickListener {
        void onItemClick(TripsEntity tour);
    }

    public class DashboardHolder extends RecyclerView.ViewHolder {
        //private final TextView id;
        private final TextView city;
        private final TextView country;
        private final TextView duration;
        private final TextView type;
        private final TextView price;


        public DashboardHolder(View view) {
            super(view);
            //id = view.findViewById(R.id.trip_id_cardview);
            city = view.findViewById(R.id.trip_city_cardview);
            country = view.findViewById(R.id.trip_country_cardview);
            duration = view.findViewById(R.id.trip_duration_cardview);
            type = view.findViewById(R.id.trip_type_cardview);
            price = view.findViewById(R.id.trip_price_cardview);



            //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(R.id.tour_image_cardview);

            view.setOnClickListener(view1 -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(tripsEntityList.get(position));
                }
            });
        }
    }

}
