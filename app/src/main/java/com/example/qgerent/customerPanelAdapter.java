package com.example.qgerent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class customerPanelAdapter extends FirebaseRecyclerAdapter<requestQ,customerPanelAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public customerPanelAdapter(@NonNull FirebaseRecyclerOptions<requestQ> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull requestQ model) {
        holder.qCustomerNameCPID.setText(model.getcName());
        holder.qCustomerEmailCPID.setText(model.getcEmail());
        holder.qCustomerServiceCPID.setText(model.getcService() );
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.q_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView qCustomerNameCPID,qCustomerEmailCPID,qCustomerServiceCPID;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
                qCustomerNameCPID= itemView.findViewById(R.id.qCustomerNameCP);
                qCustomerEmailCPID=itemView.findViewById(R.id.qCustomerEmailCP);
                qCustomerServiceCPID=itemView.findViewById(R.id.qCustomerServiceCP);
        }
    }
}
