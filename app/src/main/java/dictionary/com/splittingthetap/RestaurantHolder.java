package dictionary.com.splittingthetap;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nguyen Ha Thanh on 2/19/2017.
 */

public class RestaurantHolder {
    private TextView name      = null;
    private TextView address   = null;
    private ImageView icon     = null;

    RestaurantHolder (View row) {
        name = (TextView)row.findViewById(R.id.title);
        address = (TextView)row.findViewById(R.id.address);
        icon = (ImageView)row.findViewById(R.id.icon);
    }

    void populateFrom (Restaurant r) {
        name.setText(r.getName());
        address.setText(r.getAddress());

        if (r.getType().equals("sit_down")){
            icon.setImageResource(R.drawable.ball_red);
        } else if (r.getType().equals("take_out")) {
            icon.setImageResource(R.drawable.ball_green);
        } else {
            icon.setImageResource(R.drawable.ball_yellow);
        }
    }
}
