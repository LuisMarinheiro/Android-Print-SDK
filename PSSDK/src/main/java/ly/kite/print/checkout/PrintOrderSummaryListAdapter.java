package ly.kite.print.checkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import ly.kite.print.PrintJob;
import ly.kite.print.PrintOrder;
import ly.kite.print.R;

/**
 * Created by deonbotha on 20/02/2014.
 */
class PrintOrderSummaryListAdapter extends BaseAdapter {

    private final PrintOrder order;

    public PrintOrderSummaryListAdapter(PrintOrder order) {
        this.order = order;
    }

    @Override
    public int getCount() {
        return order.getJobs().size();
    }

    @Override
    public Object getItem(int i) {
        return order.getJobs().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.order_summary_list_item, parent, false);
        TextView itemDescription = (TextView) row.findViewById(R.id.text_view_order_item_description);
        TextView itemCost = (TextView) row.findViewById(R.id.text_view_order_item_cost);

        PrintJob job = order.getJobs().get(i);
        itemDescription.setText(String.format("Pack of %d %s", job.getQuantity(), job.getProductType().getProductName()));

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        itemCost.setText(formatter.format(job.getCost().doubleValue()));

        return (row);
    }
}
