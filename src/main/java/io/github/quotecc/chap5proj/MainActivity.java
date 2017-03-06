package io.github.quotecc.chap5proj;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    String[] tech = {"NCredible NTunes", "32gb Flash", "AMD Processor", "Sweet Tunes Test"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,R.layout.activity_main, R.id.tech, tech));
    }

    public void onListItemClick(ListView lv, View v, int pos, long id){
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        Bundle b = new Bundle();
        switch(pos){
            case 0:
                //NCredible NTunes, headphon, https://www.newegg.com/Product/Product.aspx?Item=N82E16826696252&ignorebbr=1
                b.putString("name", "NCredible NTunes");
                b.putString("pic", "headphon");
                b.putString("url", "https://www.newegg.com/Product/Product.aspx?Item=N82E16826696252&ignorebbr=1");

                break;
            case 1:
                //32 Gb Kingston flshDrive, FlshDrive, https://www.newegg.com/Product/Product.aspx?Item=N82E16820239625&ignorebbr=1
                b.putString("name", "Kingston 32gb Flash");
                b.putString("pic", "flshdrive");
                b.putString("url", "https://www.newegg.com/Product/Product.aspx?Item=N82E16820239625&ignorebbr=1");
                break;
            case 2:
                //AMD A4-6300 processor, process, https://www.newegg.com/Product/Product.aspx?Item=N82E16819113349&ignorebbr=1
                b.putString("name", "AMD A4-6300 processor");
                b.putString("pic", "process");
                b.putString("url", "https://www.newegg.com/Product/Product.aspx?Item=N82E16819113349&ignorebbr=1");
                break;
            case 3:
                //Send to tunes
                b.putString("name", "Sweet Tunes");
                b.putString("pic", "song");

                break;

            default:

                break;
        }
        i.putExtras(b);
        startActivity(i);
    }
}
