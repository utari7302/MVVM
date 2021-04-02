package com.dejure.mvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dejure.mvvm.Interface.OnItemClick;
import com.dejure.mvvm.Model.ModelTvShow;
import com.dejure.mvvm.R;
import com.dejure.mvvm.ResponseApi.ResponseTvShow;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AdapterTvShow extends RecyclerView.Adapter<AdapterTvShow.ViewHolder> {

    private Context context;
    private List<ResponseTvShow> responseTvShowList;

    public AdapterTvShow(Context context) {
        this.context = context;
        this.responseTvShowList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_show_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponseTvShow responseTvShow = responseTvShowList.get(position);
        String image = responseTvShow.getImageThumbnailPath();
        String name = responseTvShow.getName();
        String network = responseTvShow.getNetwork();
        String startDate = responseTvShow.getStartDate();

        Picasso.get().load(image).into(holder.roundedImageView);
        holder.textView2.setText(name);
        holder.textView3.setText(network);
        holder.textView5.setText(startDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new OnItemClick(true,responseTvShowList.get(position).getCountry(),responseTvShowList.get(position).getStatus()));
            }
        });
    }
    public void setModelTvShowList(List<ResponseTvShow> responseTvShowList) {
        this.responseTvShowList = new ArrayList<>();
        this.responseTvShowList = responseTvShowList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return responseTvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView roundedImageView;
        private TextView textView2, textView3, textView5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView5 = itemView.findViewById(R.id.textView5);

        }
    }
}
