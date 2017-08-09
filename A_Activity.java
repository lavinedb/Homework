package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class A_Activity extends Activity {
    TextView numView;
    public static final int REQUEST_CODE_B = 101; //다른 액티비티를 띄울 때 구분할 수 있도록 임의의 요청코드 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        numView = (TextView) findViewById(R.id.numView);
        Button nextBtn = (Button) findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A_Activity.this, B_Activity.class);
                //B_Activity 를 띄우기 위한 intent 객체 생성
                startActivityForResult(intent, REQUEST_CODE_B);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        //띄웠던 액티비티의 응답을 처리하는 역할을 하는 메소드
        super.onActivityResult(requestCode, resultCode, Data);
        if (requestCode == REQUEST_CODE_B) {// B_Activity 의 응답인지 확인
            if (resultCode == RESULT_OK) { // B_Activity 의 기능을 수행한 결과값이 성공일시
                numView.setText(Data.getStringExtra("result")); //B_Activity 에서 put 해준 result 의 값을 numView 에 적용.
            }
        }


    }

}
