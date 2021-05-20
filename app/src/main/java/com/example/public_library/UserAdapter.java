package com.example.public_library;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ProductViewHolder> {


    private final Context mCtx;
    private final List<Users> usersList;

    public UserAdapter(Context mCtx, List<Users> usersList) {
        this.mCtx = mCtx;
        this.usersList = usersList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Users User = usersList.get(position);

        //loading the image
        Glide.with(mCtx)
        ;


        holder.textaddress.setText(User.getAddress());
        holder.textname.setText(String.valueOf(User.getName()));
        holder.textemail.setText(String.valueOf(User.getEmail()));
//        holder.textpassword.setText(String.valueOf(User.getPassword()));

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView textaddress, textname, textemail;

        public ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_users);
            textaddress = itemView.findViewById(R.id.textaddress);
            textname = itemView.findViewById(R.id.textname);
            textemail = itemView.findViewById(R.id.textemail);
//            textpassword = itemView.findViewById(R.id.textpassword);

        }
    }
}