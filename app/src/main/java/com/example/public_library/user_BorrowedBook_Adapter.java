package com.example.public_library;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class user_BorrowedBook_Adapter extends RecyclerView.Adapter<user_BorrowedBook_Adapter.ProductViewHolder>{
    private final Context mCtx;
    private final List<borrowedBooks_Model> borrowedBooks_List;

    public user_BorrowedBook_Adapter(Context mCtx, List<borrowedBooks_Model> borrowedBooks_List) {
        this.mCtx = mCtx;
        this.borrowedBooks_List = borrowedBooks_List;
    }

    @Override
    public user_BorrowedBook_Adapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_borrowed_resource, null);
        return new user_BorrowedBook_Adapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(user_BorrowedBook_Adapter.ProductViewHolder holder, int position) {
        borrowedBooks_Model books = borrowedBooks_List.get(position);

        //loading the image
        Glide.with(mCtx)
        ;


        holder.borrow_bookId.setText(books.getBookId());
        holder.borrow_bookName.setText(String.valueOf(books.getBookName()));
        holder.borrow_returnDate.setText(String.valueOf(books.getReturnDate()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Borrow_Book_Details.class);
               /*intent.putExtra("bookId", books.getBookId());
               intent.putExtra("bookName",books.getBookName());*/
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return borrowedBooks_List.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView borrow_bookId, borrow_bookName, borrow_returnDate;


        public ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_borrowedBook);
            borrow_bookId = itemView.findViewById(R.id.borrow_bookId);
            borrow_bookName = itemView.findViewById(R.id.borrow_bookName);
            borrow_returnDate = itemView.findViewById(R.id.borrow_dueDate);



        }
    }
}
