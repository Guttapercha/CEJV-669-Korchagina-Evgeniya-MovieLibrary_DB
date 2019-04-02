package com.example.movielibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    List<Movies> movieslist;


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, description, status;
        RatingBar rating;
        ImageButton btnDelete;

        MyViewHolder (final View view) {
            super(view);

            name = view.findViewById(R.id.movieName);
            id = view.findViewById(R.id.movieID);
            description = view.findViewById(R.id.movieDescription);
            rating = view.findViewById(R.id.movieRating);
            status = view.findViewById(R.id.movieStatus);
            btnDelete = view.findViewById(R.id.btn_detete_record);
        }
    }

    public MovieAdapter(List<Movies> movieslist) {
        this.movieslist = movieslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return this. movieslist.size();
    }


    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MyViewHolder viewHolder, final int i) {

        final Movies movie = movieslist.get(i);

        viewHolder.name.setText(movie.getMovieName());
        viewHolder.description.setText(movie.getDescription());
        viewHolder.id.setText(String.valueOf(movie.getMovieID()));
        viewHolder.rating.setNumStars(5);
        viewHolder.rating.setRating(movie.getMovieRating());
        viewHolder.status.setText(!movie.isActive() ? "deleted": "");
        viewHolder.btnDelete.setVisibility(!movie.isActive() ? View.GONE: View.VISIBLE);



        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(i);

                DBClass dbc = new DBClass(v.getContext());

                dbc.updateMovie(movie.getMovieID(), movie.getMovieName(), movie.getDescription(), movie.getMovieRating(), false);
            }
        });


        viewHolder.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {

                if (movie.isActive()) {

                    DBClass dbc = new DBClass(ratingBar.getContext());

                    dbc.updateMovie(movie.getMovieID(), movie.getMovieName(), movie.getDescription(), (int)rating, movie.isActive());

                    Toast.makeText(ratingBar.getContext(), "RATING CHANGED!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    private void removeItem(int position) {
        movieslist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, movieslist.size());
    }

}

