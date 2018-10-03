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

    private Drawable enteredBlank;
    private Drawable blank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        implementEvents();
    }


    // 뷰를 찾고 드래그할 뷰들에 태그 설정
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

        enteredBlank = getResources().getDrawable(R.drawable.blank_entered_main);
        blank = getResources().getDrawable(R.drawable.blank_main);

    }

    // long click 실행 및 drag listener
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

        // (실제) 데이터를 클립보드에 생성
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        // 클립보드에 저장될 데이터의 메타 데이터의 마임타입을 일반 문자열로 지정
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData data = new ClipData(view.getTag().toString(),mimeTypes,item);

        // 드래그 섀도우 인스턴스화
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        view.startDrag(data,shadowBuilder,view,0);

        // 공간은 남긴 채 뷰를 감춤
        view.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent event){
        int action = event.getAction();

        switch (action){

            //드래그 앤 드랍의 시작
            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;

            // 드래그 한 뷰가 다른 경계 지점으로 넘어갔을 때
            case DragEvent.ACTION_DRAG_ENTERED :
                view.setBackground(enteredBlank);
                break;

            // ACTION_DRAG_ENTERED 실행 후
            case DragEvent.ACTION_DRAG_LOCATION:
                view.invalidate();
                return true;

            // 드래그한 뷰가 해당 영역에서 빠져나갔을 떄
            case DragEvent.ACTION_DRAG_EXITED:

                view.setBackground(blank);

                return true;

             // 뷰를 드래그해서 다른 지역으로 옮긴 후 누른 상태를 놓았을 때
            case DragEvent.ACTION_DROP:

                ClipData.Item item = event.getClipData().getItemAt(0);

                view.invalidate();

                View v = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();

                // 드래그 된 뷰 지우기
                owner.removeView(v);

                // 뷰를 LinearLayout으로 캐스팅
                LinearLayout container = (LinearLayout) view;

                //드래그 된 뷰 추가
                container.addView(v);

                // 드래그 된 뷰가 보이도록 설정
                v.setVisibility(View.VISIBLE);

                return true;

             // ACTION_DROP이 끝난 후
            case DragEvent.ACTION_DRAG_ENDED:

                view.invalidate();

                return true;

            default:
                view.invalidate();
                break;
        }
        return false;
    }

}
