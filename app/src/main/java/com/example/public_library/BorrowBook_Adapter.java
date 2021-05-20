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

public class BorrowBook_Adapter  extends RecyclerView.Adapter<BorrowBook_Adapter.ProductViewHolder>{
    private final Context mCtx;
    private final List<Books> booksList;

    public BorrowBook_Adapter(Context mCtx, List<Books> booksList) {
        this.mCtx = mCtx;
        this.booksList = booksList;
    }

    @Override
    public BorrowBook_Adapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_book_list, null);
        return new BorrowBook_Adapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BorrowBook_Adapter.ProductViewHolder holder, int position) {
        Books books = booksList.get(position);

        //loading the image
        Glide.with(mCtx)
        ;


        holder.text_bookId.setText(books.getBookId());
        holder.text_bookName.setText(String.valueOf(books.getBookName()));
        holder.text_bookAuthor.setText(String.valueOf(books.getBookAuthor()));
        holder.text_bookType.setText(String.valueOf(books.getBookType()));

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
        return booksList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView text_bookId, text_bookName, text_bookAuthor,text_bookType;


        public ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.user_card_view_books);
            text_bookId = itemView.findViewById(R.id.text_bookId);
            text_bookName = itemView.findViewById(R.id.text_bookName);
            text_bookAuthor = itemView.findViewById(R.id.text_bookAuthor);
            text_bookType = itemView.findViewById(R.id.text_bookType);


        }
    }
}
