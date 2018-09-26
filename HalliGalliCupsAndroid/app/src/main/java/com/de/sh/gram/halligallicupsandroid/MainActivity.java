package com.de.sh.gram.halligallicupsandroid;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener{

    //private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView redCup, greenCup, blueCup, blackCup;
    private ImageView firstBlank, secondBlank, thirdBlank, fourthBlank;
    private static final String RED_CUP_TAG = "REDCUP IMAGE";
    private static final String GREEN_CUP_TAG = "GREENCUP IMAGE";
    private static final String BLUE_CUP_TAG = "BLUECUP IMAGE";
    private static final String BLACK_CUP_TAG = "BLACKCUP IMAGE";

    //Drawable enteredBlank = getResources().getDrawable(R.drawable.blank_entered_main);
    //Drawable blank = getResources().getDrawable(R.drawable.blank_main);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        implementEvents();
    }

    private void findViews(){
        redCup = (ImageView) findViewById(R.id.img_main_redcup);
        redCup.setTag(RED_CUP_TAG);
        greenCup = (ImageView) findViewById(R.id.img_main_greencup);
        greenCup.setTag(GREEN_CUP_TAG);
        blueCup = (ImageView) findViewById(R.id.img_main_bluecup);
        blueCup.setTag(BLUE_CUP_TAG);
        blackCup = (ImageView) findViewById(R.id.img_main_blackcup);
        blackCup.setTag(BLACK_CUP_TAG);

        /*firstBlank = (ImageView) findViewById(R.id.img_main_blank1);
        secondBlank = (ImageView) findViewById(R.id.img_main_blank2);
        thirdBlank = (ImageView) findViewById(R.id.img_main_blank3);
        fourthBlank = (ImageView) findViewById(R.id.img_main_blank4);*/

    }

    private void implementEvents(){
        redCup.setOnLongClickListener(this);
        greenCup.setOnLongClickListener(this);
        blueCup.setOnLongClickListener(this);
        blackCup.setOnLongClickListener(this);

        findViewById(R.id.linear_main_blank1).setOnDragListener(this);
        findViewById(R.id.linear_main_blank2).setOnDragListener(this);
        findViewById(R.id.linear_main_blank3).setOnDragListener(this);
        findViewById(R.id.linear_main_blank4).setOnDragListener(this);
    }

    @Override
    public boolean onLongClick(View view){

        //MIMETYPE, CharSequence, Tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData data = new ClipData(view.getTag().toString(),mimeTypes,item);

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        view.startDrag(data,shadowBuilder,view,0);

        view.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent event){
        int action = event.getAction();

        switch (action){
            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;

            case DragEvent.ACTION_DRAG_ENTERED :
                //view.setBackground(enteredBlank);
                break;


            case DragEvent.ACTION_DRAG_LOCATION:
                view.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                view.invalidate();

                return true;

            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                // Turns off any color tints

                // Invalidates the view to force a redraw
                view.invalidate();

                View v = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                owner.removeView(v);//remove the dragged view
                LinearLayout container = (LinearLayout) view;//caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v);//Add the dragged view
                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                view.invalidate();

                return true;

            default:
                break;
        }
        return false;
    }

}
