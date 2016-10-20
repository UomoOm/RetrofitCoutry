package om.studies.om.a1810;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Country> countryList;
    private OnRecyclerItemClickListener listener;

    public interface OnRecyclerItemClickListener {
        void onItemClickListener(Country item, int position);
    }


    public RecyclerViewAdapter(List<Country> countryList, Context context) {
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.name.setText((CharSequence) countryList.get(i));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;

        ViewHolder(View item) {
            super(item);
            name = (TextView) item.findViewById(R.id.list_item);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                listener.onItemClickListener(countryList.get(position), position);
            } else {
                throw new RuntimeException(
                        "You must init OnRecyclerItemClickListener by calling setListener() method.");
            }
        }
    }
}
