package com.yeho.kimdaeho.lostfindapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.Locale;


/*
* Android 위치정보 가져오기...
*
* 1) AndroidManifest.xml 퍼미션 추가 : android.permission.ACCESS_FILE_LOCATION
*
* 1.onCreate 영역 : GPS를 제어 할 수 있는 LocationManager를 생성함
*       1) 현재 사용 가능한 위치 정보 장치 중에서 가장 정확도 높은 장치를 판별함
*       2) 마지막으로 조회 되었던 위치 정보가 존재하는지 체크하고 있다면 그 위치로 초기값을 설정함
* 2.onResume에서 GPS를 구동
*       1) LocationManager 객체에 이벤트 연결
*          위치정보 장치 이름 갱신 기간 주기 갱신 거리 주기를 설정함
* 3.onPause에서 GPS를 중지
*       1) LocationManager 객체에 이벤트 해제
 */

public class ReportActivity extends AppCompatActivity implements View.OnClickListener , LocationListener {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private static int num = 0;

    //캡처한 이미지 Uri
    private Uri mImageCaptureUri;

    //신고 정보 내용을 적는 테스트
    EditText reportEditText = null;
    //위치 정보 내용을 등록하는 리스트
    EditText reportPosEditText = null;

    //신고 내용 전송하는 버튼
    Button reportSendBtn = null;
    //이미지버튼을 배열화 시킴 총 5가지의 이미지를 넣을 수 있음
    ImageButton[] reportImageBtns = new ImageButton[5];

    //위치정보 객체
    LocationManager lm = null;
    //위치정보 장치 이름
    String provider = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Bundle bundle = getIntent().getExtras();
        int num = bundle.getInt("categoryPage");

        reportEditText = (EditText)findViewById(R.id.report_editText);
        reportPosEditText = (EditText)findViewById(R.id.report_pos_eidttext);
        reportSendBtn = (Button)findViewById(R.id.report_send_button);

        reportSendBtn.setOnClickListener(this);
        //for문을 이용하여 우선 첫번째 이미지버튼만 setOnClickListener를 함
        for (int i = 0; i < reportImageBtns.length; i++){
            reportImageBtns[i] = (ImageButton)findViewById(R.id.report_image_button1+i);
            if(i > 0){
                reportImageBtns[i].setAlpha(0.0f);
            }else{
                reportImageBtns[i].setOnClickListener(this);
            }
        }

        //Location Manager
        //위치 정보 객체를 생성한다
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //위처정보 하드웨어 목록을 받는다
       Criteria c = new Criteria();
        //최적의 하드웨어 이름을 리턴 받는다.
        provider = lm.getBestProvider(c, true);
        //모든 장치 리스트에서 사용가능한 항목을 얻는다
        if (provider == null || !lm.isProviderEnabled(provider)){
            List<String> list = lm.getAllProviders();
            for(int i = 0; i< list.size(); i++){
                //장치 이름 하나 얻기
                String temp = list.get(i);
                //사용 가능 여부 검사
                if(lm.isProviderEnabled(temp)){
                    provider = temp;
                    break;
                }
            }
        }
        Location location = lm.getLastKnownLocation(provider);
        if(location == null){
            Toast.makeText(this,"사용가능한 위치 정보 제공자가 없습니다",Toast.LENGTH_LONG).show();
        }else {
            onLocationChanged(location);
        }

    }
    //카메라에서 이미지 가져오기
    private void doTakePhotoAction(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_"+String.valueOf(System.currentTimeMillis())+".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }
    //앨범에서 이미지 가져오기
    private void doTakeAlbumAction(){
        //앨범 호출

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK){
            return;
        }
        switch (requestCode){
            case CROP_FROM_CAMERA:
                //크롭이 된 후 이미지를 넘겨받아 임시파일까지 작동하는 알고리즘
                final Bundle extras = data.getExtras();

                if(extras != null){
                    Bitmap photo = extras.getParcelable("data");
                    reportImageBtns[num].setImageBitmap(photo);
                    //이미지를 입힌 후 다음 이미지 버튼에 버튼을 활성화 시키고 알파값을 1.0으로 하여 투도를 없앰
                    if(num < 5){
                        reportImageBtns[num+1].setOnClickListener(this);
                        reportImageBtns[num+1].setAlpha(1.0f);
                    }

                }
                //임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists()){
                    f.delete();
                }
                break;
            case PICK_FROM_ALBUM:
            {
                mImageCaptureUri = data.getData();
            }
            case PICK_FROM_CAMERA:
            {
                //카메라를 자를 크기를 정함
               Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");
                intent.putExtra("outputX", 160);
                intent.putExtra("outputY", 160);
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);
                break;
            }
        }
    }
    private void callAlert(){
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        new AlertDialog.Builder(this)
                .setTitle("업로드 할 이미지 선택")
                .setPositiveButton("사진 촬영", cameraListener)
                .setNeutralButton("앨범 선택", albumListener)
                .setNegativeButton("취소",cancelListener)
                .show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //신고를 등록했을 때 나오는 버튼
            case R.id.report_send_button:
                break;
            //버튼을 눌렀을때 각 배열의 num 값이 바뀜
            case R.id.report_image_button1:
                num = 0;
                callAlert();
                break;
            case R.id.report_image_button2:
                num = 1;
                callAlert();
                break;
            case R.id.report_image_button3:
                num = 2;
                callAlert();
                break;
            case R.id.report_image_button4:
                num = 3;
                callAlert();
                break;
            case R.id.report_image_button5:
                num = 4;
                callAlert();
                break;
        }
    }

    //Location
    //화먼이 불릴때, 일시 정지 해제 처리
    @Override
    protected void onResume() {
        super.onResume();
        lm.requestLocationUpdates(provider, 500, 1, this);
    }
    //다른화면으로 넘어갈때 일시정지 처리
    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        reportPosEditText.setText(getAddress(lat,lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    //위도와 경도를 기반으로 주소값을 리턴해주는 메서드
    private String getAddress(double lat, double lng){
        String address = null;
        //위치 정보를 활용하기 위한 구글 API 객
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;

        try {
            list = geocoder.getFromLocation(lat,lng,1);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null){
            Log.e("getAddress","주소 데이터 얻기 실패");
            return null;
        }
        if(list.size() > 0){
            Address addr = list.get(0);
            address = addr.getSubLocality() + " "
                    + addr.getThoroughfare() + " "
                    + addr.getFeatureName();
        }
        return address;
    }
}
