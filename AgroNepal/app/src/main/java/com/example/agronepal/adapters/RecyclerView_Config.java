package com.example.agronepal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agronepal.R;
import com.example.agronepal.activities.View_Experience;
import com.example.agronepal.model.Experiences;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private PostAdapter mPostAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Experiences>exps,List<String>keys){
        mContext=context;
        mPostAdapter=new PostAdapter(exps, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mPostAdapter);
    }

    class PostItemView extends RecyclerView.ViewHolder{
        private TextView mtitle, mdesc;
        private String key;

        public PostItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.sharing_list_item,parent,false));
            mtitle=(TextView) itemView.findViewById(R.id.title1);
            mdesc=(TextView) itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent intent=new Intent(mContext, View_Experience.class);
                     intent.putExtra("key",key);
                    intent.putExtra("title",mtitle.getText().toString());
                    intent.putExtra("experience",mdesc.getText().toString());
                    mContext.startActivity(intent);

                }
            });
        }
        public void bind(Experiences experiences, String key){
           mtitle.setText(experiences.getTitle());
           mdesc.setText(experiences.getExperience());
           this.key=key;
        }
    }
    class PostAdapter extends RecyclerView.Adapter<PostItemView>{
        private List<Experiences> mExp;
        private List<String> mkeys;

        public PostAdapter(List<Experiences> mExp, List<String> mkeys) {
            this.mExp = mExp;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public PostItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PostItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PostItemView holder, int position) {
        holder.bind(mExp.get(position), mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mExp.size();
        }
    }
}
