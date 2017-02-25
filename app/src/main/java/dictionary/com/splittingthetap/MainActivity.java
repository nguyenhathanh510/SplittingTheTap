package dictionary.com.splittingthetap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText address;
    private RadioGroup type;
    private Button save;
    private TabHost tabHost;

    List<Restaurant> model = new ArrayList<Restaurant>();
    //ArrayAdapter<Restaurant> adapter = null;
    RestaurantAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save    = (Button)findViewById(R.id.save);
        name    = (EditText)findViewById(R.id.nameTextField);
        address = (EditText)findViewById(R.id.addressTextField);
        type    = (RadioGroup)findViewById(R.id.type);

        init();
        ListView list = (ListView)findViewById(R.id.restaurant);
       // adapter=new ArrayAdapter<Restaurant>(this,android.R.layout.simple_list_item_1,model);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec spec1 = tabHost.newTabSpec("tag1");
        spec1.setContent(R.id.restaurant);
        spec1.setIndicator("List",getResources().getDrawable(R.drawable.list));
        tabHost.addTab(spec1);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("tag2");
        spec2.setContent(R.id.detail);
        spec2.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
        tabHost.addTab(spec2);
        tabHost.setCurrentTab(1);
        list.setOnItemClickListener(onListClick);

    }

    private void init (){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant r = new Restaurant();

                r.setName(name.getText().toString());
                r.setAddress(address.getText().toString());
                switch (type.getCheckedRadioButtonId()){
                    case R.id.take_out:
                        r.setType("take_out");
                        break;
                    case R.id.sit_down:
                        r.setType("sit_down");
                        break;
                    case R.id.delivery:
                        r.setType("delivery");
                        break;
                    default:
                        //do nothing
                }
                adapter.add(r);
                //Toast.makeText(MainActivity.this,r.getName().toString()+"\n"+r.getAddress().toString()+"\n"+r.getType().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick (AdapterView<?> parent, View view, int position, long id){
            Restaurant r = model.get(position);
            name.setText(r.getName());
            address.setText(r.getAddress());

            if (r.getType().equals("sit_down")){
                type.check(R.id.sit_down);
            } else if (r.getType().equals("take_out")){
                type.check(R.id.take_out);
            } else {
                type.check(R.id.delivery);
            }
            tabHost.setCurrentTab(1);
        }

    };

    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
        RestaurantAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, model);
        }
        public View getView (int position, View convertView, ViewGroup parent){
            View row = convertView;
            RestaurantHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else {
                holder = (RestaurantHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return (row);
        }
    }
}
