package com.example.mypa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.BookingViewHolder> {

    class BookingViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookingItemView;

        private BookingViewHolder(View itemView) {
            super(itemView);
            bookingItemView = itemView.findViewById(R.id.textView5v1);
        }
    }

    private final LayoutInflater mInflater;
    private List<Booking> mBookings; // Cached copy of bookings

    BookingListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item5v1, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        if (mBookings != null) {
            Booking current = mBookings.get(position);
            holder.bookingItemView.setText(current.getBooking());
        } else {
            // Covers the case of data not being ready yet.
            holder.bookingItemView.setText("No Word");
        }
    }

    void setBookings(List<Booking> bookings){
        mBookings = bookings;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBookings != null)
            return mBookings.size();
        else return 0;
    }
}
