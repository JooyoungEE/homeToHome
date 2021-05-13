package com.example.aconmemo;

        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Looper;
        import android.os.SystemClock;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ScrollView;
        import android.widget.TextView;
        import android.widget.Toast;

public class StopWatchActivity extends AppCompatActivity{
    private ScrollView scrollView;
    private TextView mEllapse, mSplit;
    private Button mBtnStart, mBtnSplit, mBtnExit;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;
    long mBaseTime;
    long mPauseTime;
    int mSplitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        mEllapse = (TextView)findViewById(R.id.ellapse);
        mSplit = (TextView)findViewById(R.id.split);
        mBtnStart = (Button)findViewById(R.id.btnStart);
        mBtnSplit = (Button)findViewById(R.id.btnSplit);
        mBtnExit = (Button)findViewById(R.id.btnExit);

        mTimer.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);

        mBtnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StopWatchActivity.this);
                builder.setMessage("앱을 종료하시겠습니까?");
                builder.setTitle("앱 종료 확인")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(StopWatchActivity.this,"앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("앱 종료 확인");
                alert.show();
            }
        });
    }
    Handler mTimer = new Handler(Looper.getMainLooper()){
        public void handleMessage(android.os.Message msg) {
            mEllapse.setText(getEllapse());
            mTimer.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onDestroy() {
        mTimer.removeMessages(0);
        super.onDestroy();
    }

    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.btnStart:
                switch(mStatus){
                    case IDLE:
                        mBaseTime = SystemClock.elapsedRealtime();
                        mTimer.sendEmptyMessage(0);
                        mBtnStart.setText("중지");
                        mBtnSplit.setEnabled(true);
                        mStatus = RUNNING;
                        break;

                    case RUNNING:
                        mTimer.removeMessages(0);
                        mPauseTime = SystemClock.elapsedRealtime();
                        mBtnStart.setText("시작");
                        mBtnSplit.setText("초기화");
                        mStatus = PAUSE;
                        break;

                    case PAUSE:
                        long now = SystemClock.elapsedRealtime();
                        mBaseTime += (now - mPauseTime);
                        mTimer.sendEmptyMessage(0);
                        mBtnStart.setText("중지");
                        mBtnSplit.setText("기록");
                        mStatus = RUNNING;
                        break;
                }
                break;

            case R.id.btnSplit:
                switch(mStatus){
                    case RUNNING:
                        String sSplit = mSplit.getText().toString();
                        sSplit += String.format("%d. %s\n", mSplitCount+1, getEllapse());
                        mSplit.setText(sSplit);
                        mSplitCount++;
                        break;

                    case PAUSE:
                        mTimer.removeMessages(0);
                        mBtnStart.setText("시작");
                        mBtnSplit.setText("기록");
                        mEllapse.setText("00:00:00");
                        mSplitCount = 0;
                        mStatus = IDLE;
                        mSplit.setText("");
                        mBtnSplit.setEnabled(false);
                        break;
                }
                break;
        }
    }

    String getEllapse(){
        long now = SystemClock.elapsedRealtime();
        long ell = now - mBaseTime;
        String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, (ell/1000)%60, (ell %1000)/10);
        return sEll;
    }
}
