package balintbabics.queenproblem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity"; // or MainActivity.class.getSimpleName();

    @BindView(R.id.game_view)
    public LinearLayout gameView;
    @BindView(R.id.list_view)
    public ListView listView;

    ArrayList<Integer> listItems = new ArrayList<>();

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        final GameView gameView = new GameView(this);
        this.gameView.addView(gameView);

        fillListItems(listItems);
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //draw Queens to the right place, each list item is an unique solution
            }
        });
    }

    @Override
    public void onDestroy() {
        if(unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    private ArrayList<Integer> fillListItems(ArrayList<Integer> listItems) {
        int item = 1;
        int allCases = 92;
        for(int i = 0; i < allCases; ++i){
            listItems.add(item);
            ++item;
        }
        return listItems;
    }
}
