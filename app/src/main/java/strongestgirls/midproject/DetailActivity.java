package strongestgirls.midproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private ImageView hero_img;
    private EditText[] editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //获取控件
        hero_img = (ImageView)findViewById(R.id.img);
        editTexts = new EditText[7];
        editTexts[0] = (EditText)findViewById(R.id.name);   //名字
        editTexts[1] = (EditText)findViewById(R.id.power);  //势力
        editTexts[2] = (EditText)findViewById(R.id.sex);    //性别
        editTexts[3] = (EditText)findViewById(R.id.date);   //生卒年
        editTexts[4] = (EditText)findViewById(R.id.name2);  //字
        editTexts[5] = (EditText)findViewById(R.id.place);  //籍贯
        editTexts[6] = (EditText)findViewById(R.id.event);  //事迹

        //intent接收从主界面传送的数据

    }

    //在标题栏添加图标
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //标题栏图标点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                hero_img.setFocusable(true);
                hero_img.setFocusableInTouchMode(true);
                for (int i = 0;i<7;i++) {
                    //文本框变为可编辑的
                    editTexts[i].setFocusable(true);
                    editTexts[i].setFocusableInTouchMode(true);
                }
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("上传头像");
                //放入两个选项
                final String[] choice={"拍摄","从相册选择"};
                alertDialog.setItems(choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mes="您选择了["+choice[which]+"]";
                        Toast.makeText(getApplicationContext(),mes,Toast.LENGTH_SHORT).show();
                        //获取服务

                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"您选择了[取消]",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.create();
                hero_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.show();
                    }
                });
                return true;
            case R.id.delete:
                final AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);
                alertDialog1.setTitle("删除此项").setMessage("确认删除这一人物数据？");
                alertDialog1.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"您选择了[确定]",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"您选择了[取消]",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog1.create().show();
                return true;
            case R.id.check:
                //有bug，点击之后图像仍可以点击
                hero_img.setFocusable(false);
                hero_img.setFocusableInTouchMode(false);
                //获得img的src，未完成
                for (int i = 0;i < 7;i++) {
                    //文本框变为不可编辑的
                    editTexts[i].setFocusable(false);
                    editTexts[i].setFocusableInTouchMode(false);
                    //获得文本框内容,传递到主界面更新数据
                    editTexts[i].getText().toString();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

