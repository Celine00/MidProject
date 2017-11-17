package strongestgirls.midproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    //xml控件
    private ImageView profile;
    private EditText name;
    private RadioGroup radioGroup;
    private RadioButton male;
    private RadioButton female;
    private Spinner birth_spinner;
    private Spinner death_spinner;
    private Spinner place_spinner;
    private Spinner power_spinner;
    private EditText event;
    private Button confirm_button;
    private Button cancel_button;
    private View editLayout;

    private final int CAMERA_REQUEST = 1;
    private final int PHOTO_REQUEST = 2;

    private List<Hero> heros = new ArrayList<>();

    String[] hero_yearItem = new String[]{"公元184年", "公元185年", "公元186年", "公元187年", "公元188年", "公元189年", "公元190年",
            "公元191年", "公元182年", "公元193年", "公元194年", "公元195年", "公元196年", "公元197年", "公元198年", "公元199年", "公元200年",
            "公元201年", "公元202年", "公元203年", "公元204年", "公元205年", "公元206年", "公元207年", "公元208年", "公元209年", "公元210年",
            "公元211年", "公元212年", "公元213年", "公元214年", "公元215年", "公元216年", "公元217年", "公元218年", "公元219年", "公元220年",
            "公元221年", "公元222年", "公元223年", "公元224年", "公元225年", "公元226年", "公元227年", "公元228年", "公元229年", "公元230年",
            "公元231年", "公元232年", "公元233年", "公元234年", "公元235年", "公元236年", "公元237年", "公元238年", "公元239年", "公元240年",
            "公元241年", "公元242年", "公元243年", "公元244年", "公元245年", "公元246年", "公元247年", "公元248年", "公元249年", "公元250年",
            "公元251年", "公元252年", "公元253年", "公元254年", "公元255年", "公元256年", "公元257年", "公元258年", "公元259年", "公元260年",
            "公元261年", "公元262年", "公元263年", "公元264年", "公元265年", "公元266年", "公元267年", "公元268年", "公元269年", "公元270年",
            "公元271年", "公元272年", "公元273年", "公元274年", "公元275年", "公元276年", "公元277年", "公元278年", "公元279年", "公元280年"};
    String[] hero_placeItem = new String[]{"并州", "冀州", "交州", "荆州", "凉州", "青州", "司隶",
            "徐州", "兖州", "扬州", "益州", "幽州", "豫州"};
    String[] hero_powerItem = new String[]{"东汉", "魏", "蜀", "吴", "袁绍", "袁术", "刘表",
            "起义军", "董卓", "刘璋", "西晋", "少数民族", "在野", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        profile = (ImageView) findViewById(R.id.profile);
        name = (EditText)findViewById(R.id.name);
        radioGroup = (RadioGroup)findViewById(R.id.sexSelect);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        birth_spinner = (Spinner)findViewById(R.id.birth_spinner);
        death_spinner = (Spinner)findViewById(R.id.death_spinner);
        place_spinner = (Spinner)findViewById(R.id.place_spinner);
        power_spinner = (Spinner)findViewById(R.id.power_spinner);
        event = (EditText)findViewById(R.id.edit_event);
        confirm_button = (Button) findViewById(R.id.confirm);
        cancel_button = (Button) findViewById(R.id.cancel);
        editLayout = findViewById(R.id.editLayout);

        //点击事件调用
        profileClick();
        birthClick();
        deathClick();
        placeClick();
        powerClick();
        buttonClick();
    }

    //头像点击事件
    public void profileClick() {
        //简单列表对话框
        final String[] Items = {"拍摄", "从相册中选择"};
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //点击头像
        profile.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setTitle("上传头像").setPositiveButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        }).setItems(Items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Items[i] == "拍摄") {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        } else if (Items[i] == "从相册中选择") {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, PHOTO_REQUEST);
                        }
                    }
                }).create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profile.setImageBitmap(photo);
        } else if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                profile.setImageURI(data.getData());
                //获取返回的数据
                /*Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                //获取选择照片的数据视图
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                //从数据视图中获取已经选择的照片的路径
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                //将图片显示出来
                profile.setImageBitmap(BitmapFactory.decodeFile(picturePath));*/
            }
        }
    }

    //出生点击事件
    public void birthClick() {
        ArrayAdapter<String> birthAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, hero_yearItem);
        birthAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        birth_spinner.setAdapter(birthAdapter);
    }

    //死亡点击事件
    public void deathClick() {
        ArrayAdapter<String> deathAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, hero_yearItem);
        deathAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        death_spinner.setAdapter(deathAdapter);
    }

    //籍贯点击事件
    public void placeClick() {
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, hero_placeItem);
        placeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        place_spinner.setAdapter(placeAdapter);

    }

    //势力点击事件
    public void powerClick() {
        ArrayAdapter<String> powerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, hero_powerItem);
        powerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        power_spinner.setAdapter(powerAdapter);
    }

    //确定或者取消按钮点击事件
    public void buttonClick() {
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditActivity.this, "确定按钮被点击了", Toast.LENGTH_SHORT).show();
                String letter = name.getText().toString().substring(0, 1).toUpperCase();
                String NAME = name.getText().toString();
                String sex = "";
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton rb = (RadioButton)radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        sex = rb.getText().toString();
                        break;
                    }
                }
                String place = place_spinner.getSelectedItem().toString();
                String birth_year = birth_spinner.getSelectedItem().toString();
                String death_year = death_spinner.getSelectedItem().toString();
                String power = power_spinner.getSelectedItem().toString();
                String EVENT = event.getText().toString();
                heros.add(new Hero(letter, NAME, sex, place, birth_year, death_year, power, EVENT, profile.getId()));
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditActivity.this, "取消按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
