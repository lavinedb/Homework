package com.example.project;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class B_Activity extends Activity {
    private int increase_num = 0; //증가할 숫자 초기값
    private TextView numMeter;
    public Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        numMeter = (TextView)findViewById(R.id.numMeter);
        backBtn = (Button)findViewById(R.id.backBtn);

    }
    Handler handler = new Handler(){ //작업을 순서대로 처리하기 위해 핸들러 객체 생성(데드락방지)
        @Override
        public void handleMessage(Message msg){
            numMeter.setText(String.valueOf(increase_num)); //수행한 결과값을 String형태로 반환
        }
    };

    protected void onStart(){
        super.onStart();

        Thread thread = new Thread(new Runnable() {
            boolean flag = true; // 스레드 멈추는 시점을 정하기 위한 플래그 생성
            @Override
            public void run() {

                while(flag){
                    try{
                        handler.sendMessage(handler.obtainMessage());
                        //handler객체의 obtainMessage()로 메시지 객체하나를 참조
                        //sendMessage()로 메시지큐에 수행한 작업의 결과를 메시지 큐에 넣음.
                        Thread.sleep(1000);
                        increase_num++;
                    }catch(Throwable t){

                    }
                    backBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            flag=false;
                            //스레드 중지
                            Intent intent = getIntent();
                            //B_Activity를 호출한 Activity한테서 인텐트 값을 받아옴
                            intent.putExtra("result",String.valueOf(increase_num));
                            //result에 스레드가 중지된 시점의 결과값을 string형태로 반환.
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    });
                }

            }
        });
        thread.start();
    }



}
