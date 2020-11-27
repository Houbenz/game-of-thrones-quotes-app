package com.houbenz.gameofthronesqutotes;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {



    ArrayList<Quote> quotes;
    private boolean isGrid = false;


    public RecyclerViewAdapter( ArrayList<Quote> quotes,boolean isGrid){
        this.quotes =quotes;this.isGrid=isGrid;
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        View view;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(isGrid)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_layout,parent,false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_layout_grid,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        View view = holder.view;

        TextView character =view.findViewById(R.id.character);
        TextView quote=view.findViewById(R.id.quote);
        ImageView imageView = view.findViewById(R.id.imageView);


        String stringCharacter=quotes.get(position).getCharacter();
        character.setText(stringCharacter);
        quote.setText("\""+quotes.get(position).getQuote()+"\"");

        String replaceChar= stringCharacter.replace(" ","_");
        replaceChar= replaceChar.toLowerCase();

        imageView.setImageDrawable(view.getContext().getResources().getDrawable(getImage(replaceChar)));
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }



    public int getImage(String image_name){

        if (image_name.equalsIgnoreCase("alayne"))
            return R.drawable.alayne;

        if (image_name.equalsIgnoreCase("bran"))
            return R.drawable.bran;

        if (image_name.equalsIgnoreCase("bronn"))
            return R.drawable.bronn;

        if (image_name.equalsIgnoreCase("brynden_tully"))
            return R.drawable.brynden_tully;

        if (image_name.equalsIgnoreCase("cersei_and_tyrion"))
            return R.drawable.cersei_and_tyrion;

        if (image_name.equalsIgnoreCase("cersei_lannister"))
            return R.drawable.cersei_lannister;

        if (image_name.equalsIgnoreCase("daenerys"))
            return R.drawable.daenerys;

        if (image_name.equalsIgnoreCase("davos"))
            return R.drawable.davos;

        if (image_name.equalsIgnoreCase("jaime_lannister"))
            return R.drawable.jaime_lannister;

        if (image_name.equalsIgnoreCase("jon_snow"))
            return R.drawable.jon_snow;

        if (image_name.equalsIgnoreCase("lord_rodrik"))
            return R.drawable.lord_rodrik;

        if (image_name.equalsIgnoreCase("olenna_tyrell"))
            return R.drawable.olenna_tyrell;

        if (image_name.equalsIgnoreCase("quaithe"))
            return R.drawable.quaithe;

        if (image_name.equalsIgnoreCase("renly_baratheon"))
            return R.drawable.renly_baratheon;

        if (image_name.equalsIgnoreCase("samwell"))
            return R.drawable.samwell;

        if (image_name.equalsIgnoreCase("sansa"))
            return R.drawable.sansa;

        if (image_name.equalsIgnoreCase("the_discarded_knight"))
            return R.drawable.the_discarded_knight;

        if (image_name.equalsIgnoreCase("the_hound"))
            return R.drawable.the_hound;


        if (image_name.equalsIgnoreCase("tyrion"))
            return R.drawable.tyrion;

        if (image_name.equalsIgnoreCase("varys"))
            return R.drawable.varys;

        if (image_name.equalsIgnoreCase("victarion_greyjoy"))
            return R.drawable.victarion_greyjoy;

        return R.drawable.the_discarded_knight;
    }
}
