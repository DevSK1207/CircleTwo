package com.odyssey.circle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.MyViewHolder> {

    private Context context;
    private List<Colors> colorsList;

    public ColorsAdapter(Context context, List<Colors> colorsList){
        this.context = context;
        this.colorsList = colorsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.colors_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        Colors colors = colorsList.get(i);
        myViewHolder.myColorButton.setColors(colors.getColor1(), colors.getColor2(), colors.getColor3(), colors.getColor4(),
                colors.getColor5(),colors.getColor6(),colors.getColor7(),colors.getColor8());
    }

    @Override
    public int getItemCount() {
        return colorsList != null ? colorsList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RadioButtons myColorButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            myColorButton = itemView.findViewById(R.id.myRadioButtons);

            myColorButton.setOnSliceClickListener(new RadioButtons.OnSliceClickListener() {
                @Override
                public void onSlickClick(int slicePosition, double angle) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("color1", colorsList.get(position).getColor1());
                    intent.putExtra("color2", colorsList.get(position).getColor2());
                    intent.putExtra("color3", colorsList.get(position).getColor3());
                    intent.putExtra("color4", colorsList.get(position).getColor4());
                    intent.putExtra("color5", colorsList.get(position).getColor5());
                    intent.putExtra("color6", colorsList.get(position).getColor6());
                    intent.putExtra("color7", colorsList.get(position).getColor7());
                    intent.putExtra("color8", colorsList.get(position).getColor8());
                    context.getApplicationContext().startActivity(intent);
                }
            });

            /*myColorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("color1", colorsList.get(position).getColor1());
                    intent.putExtra("color2", colorsList.get(position).getColor2());
                    intent.putExtra("color3", colorsList.get(position).getColor3());
                    intent.putExtra("color4", colorsList.get(position).getColor4());
                    intent.putExtra("color5", colorsList.get(position).getColor5());
                    intent.putExtra("color6", colorsList.get(position).getColor6());
                    intent.putExtra("color7", colorsList.get(position).getColor7());
                    intent.putExtra("color8", colorsList.get(position).getColor8());
                    context.getApplicationContext().startActivity(intent);
                }
            });*/

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("color1", colorsList.get(position).getColor1());
                intent.putExtra("color2", colorsList.get(position).getColor2());
                intent.putExtra("color3", colorsList.get(position).getColor3());
                intent.putExtra("color4", colorsList.get(position).getColor4());
                intent.putExtra("color5", colorsList.get(position).getColor5());
                intent.putExtra("color6", colorsList.get(position).getColor6());
                intent.putExtra("color7", colorsList.get(position).getColor7());
                intent.putExtra("color8", colorsList.get(position).getColor8());
                context.startActivity(intent);
            }
        }
    }
}
