package com.example.qgerent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminPanelAdapter extends FirebaseRecyclerAdapter<requestQ,adminPanelAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    DatabaseReference qReqDB = FirebaseDatabase.getInstance().getReference("qReq");
    public adminPanelAdapter(@NonNull FirebaseRecyclerOptions<requestQ> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminPanelAdapter.myViewHolder holder, int position, @NonNull requestQ model) {
        holder.qCustomerNameAPID.setText(model.getcName());
        holder.qCustomerEmailAPID.setText(model.getcEmail());
        holder.qCustomerServiceAPID.setText(model.getcService() );
        holder.removeReqBtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.qCustomerEmailAPID.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("This Request will be removed from Queue ");
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        qReqDB.child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.qCustomerNameAPID.getContext(), "Request Dismissed", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Keep", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public adminPanelAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView qCustomerNameAPID,qCustomerEmailAPID,qCustomerServiceAPID;
        ImageButton removeReqBtnID;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            qCustomerNameAPID= itemView.findViewById(R.id.qCustomerNameAP);
            qCustomerEmailAPID=itemView.findViewById(R.id.qCustomerEmailAP);
            qCustomerServiceAPID=itemView.findViewById(R.id.qCustomerServiceAP);
            removeReqBtnID = itemView.findViewById(R.id.removeCustomerBtn);
        }
    }
}
