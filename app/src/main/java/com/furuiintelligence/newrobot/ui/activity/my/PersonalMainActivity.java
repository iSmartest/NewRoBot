package com.furuiintelligence.newrobot.ui.activity.my;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.moudle.SaleImgEntity;
import com.furuiintelligence.newrobot.mvp.net.callback.OkHttpManager;
import com.furuiintelligence.newrobot.ui.view.GlideCircleTransform;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.furuiintelligence.newrobot.utils.CropUtils;
import com.furuiintelligence.newrobot.utils.DialogPermission;
import com.furuiintelligence.newrobot.utils.FileUtil;
import com.furuiintelligence.newrobot.utils.PermissionUtil;
import com.furuiintelligence.newrobot.utils.SharedPreferenceMark;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getContext;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PHONE_NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.config.Url.FILE_IMG;
import static com.furuiintelligence.newrobot.mvp.config.Url.HTTP;

/**
 * 修改资料页面
 */
public class PersonalMainActivity extends BaseActivity {


    private static final String TAG = "PersonalMainActivity";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_iconImgview)
    ImageView myIconImgview;
    @BindView(R.id.my_icon_Name)
    TextView myIconName;
    @BindView(R.id.myPhones)
    TextView myPhones;
    @BindView(R.id.personalText)
    TextView personalText;
    @BindView(R.id.sign_Out)
    TextView signOut;
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private File file;
    private Uri uri;
    private Map<String, File> fileMaps = new HashMap<>();
    private HashMap<String, String> phpMap = new HashMap<>();
    private PopupWindow window;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_personal_main;
    }

    @Override
    protected void loadData() {
        myIconName.setText((String) sp.getSharedPreference(NAME, ""));
        myPhones.setText((String) sp.getSharedPreference(PHONE_NUMBER, ""));
    }

    @Override
    protected void initView() {
        title.setText("个人资料");
        titleBack.setVisibility(View.VISIBLE);
        file = new File(FileUtil.getCachePath(this), "user-avatar.jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            uri = FileProvider.getUriForFile(this, "com.furuiintelligence.newrobot", file);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.my_iconImgview, R.id.personalText, R.id.sign_Out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.my_iconImgview:
                //上传头像
                setMre();
                break;
            case R.id.personalText:
                //个人资料
                ActivityUtils.getInstance().showActivity(this, ModificationActivity.class);
                break;
            case R.id.sign_Out:
                //退出
                sp.clear();
                ActivityUtils.getInstance().closeSelf();
                break;
        }
    }

    private void setMre() {
        window = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(this, R.layout.layout_view_headicon, null);
        LinearLayout imgCamera = view.findViewById(R.id.head_icon_camera_ll);
        LinearLayout imgLocalalbum = view.findViewById(R.id.head_icon_gallery_ll);
        LinearLayout imgCancel = view.findViewById(R.id.head_icon_cancel_ll);
        window.setAnimationStyle(R.style.Animation);
        window.setContentView(view);
        window.setFocusable(true);
        setBackgroundAlpha(0.5f);
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        window.setOutsideTouchable(true);
        window.showAtLocation(title, Gravity.BOTTOM, 0, 0);

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
                if (PermissionUtil.hasCameraPermission(APP.context)) {
                    uploadAvatarFromPhotoRequest();
                    window.dismiss();
                }

            }
        });

        imgLocalalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAvatarFromAlbumRequest();
                window.dismiss();

            }
        });
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });


        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }

    //popuwindow设置透明度
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * photo  拍照方法
     */
    private void uploadAvatarFromPhotoRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * album  打开系统相册
     */
    private void uploadAvatarFromAlbumRequest() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        showToast(this, "打开系统相册");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }

    @Override//在这里返回 拍照 或者相册拿到的 图片uri
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == REQUEST_CODE_ALBUM && data != null) {
            Uri newUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                newUri = Uri.parse("file:///" + CropUtils.getPath(APP.context, data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                showToast(this, "没有得到相册图片");
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            startPhotoZoom(uri);
        } else if (requestCode == REQUEST_CODE_CROUP_PHOTO) {
            uploadAvatarFromPhoto();
        }
    }

    private void uploadAvatarFromPhoto() {
        compressAndUploadAvatar(file.getPath());

    }

    private void compressAndUploadAvatar(String fileSrc) {
        final File cover = FileUtil.getSmallBitmap(APP.context, fileSrc);

        //加载本地图片
        fileMaps.put(IMAGE, cover);
        upImage(FILE_IMG);
        Log.e(TAG, "compressAndUploadAvatar: " + cover);
        Glide.with(this).load(cover)
                .transform(new GlideCircleTransform(this)).into(myIconImgview);
        showToast(this, "更换头像成功");

    }


    /**
     * 裁剪拍照裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Toast.makeText(this, "裁剪图片", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
        intent.putExtra("output", Uri.fromFile(file));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case PermissionUtil.REQUEST_SHOWCAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    uploadAvatarFromPhotoRequest();

                } else {
                    if (!SharedPreferenceMark.getHasShowCamera()) {
                        SharedPreferenceMark.setHasShowCamera(true);
                        new DialogPermission(this, "关闭摄像头权限影响扫描功能");
                    } else {
                        Toast.makeText(this, "未获取摄像头权限", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //上传图片的方法
    private void upImage(final String s) {

        phpMap.put(SALE_ID, "1");
        OkHttpManager.getInstance().asynUpLoadPost(s, phpMap, fileMaps,
                new OkHttpManager.ResultCallback() {


                    @Override
                    public void onError(Exception e) {
                        if (e.getMessage().equals("Failed to connect to")) {
                            showToast(getContext(), "未能连接到服务器");
                        }
                        //异常信息
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String string) {

                        SaleImgEntity saleImgEntity = new Gson().fromJson(string, SaleImgEntity.class);
                        switch (saleImgEntity.getResultCode()) {
                            case 0:
                                Glide.with(getContext()).load(HTTP + saleImgEntity.getData().getImage())
                                        .transform(new GlideCircleTransform(getContext())).into(myIconImgview);
                                sp.put(IMAGE, saleImgEntity.getData().getImage());
                                break;
                            case 1:
                                showToast(getContext(), saleImgEntity.getMsg());
                                break;
                        }

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //加载头像
        Glide.with(this).load(HTTP + (String) sp.getSharedPreference(IMAGE, ""))
                .transform(new GlideCircleTransform(this)).into(myIconImgview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myIconName.setText((String) sp.getSharedPreference(NAME, ""));
        myIconName.setText((String) sp.getSharedPreference(PHONE_NUMBER, ""));
    }
}
