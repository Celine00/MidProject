package strongestgirls.midproject;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] firstLetterItem = new String[] {"不限","A","B","C","D","E","F","G","H","I","J","K","L","M",
            "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] hero_powerItem = new String[] {"不限","东汉","魏","蜀","吴","袁绍","袁术","刘表","起义军","董卓",
            "刘璋","西晋","少数民族","在野","其他"};
    String[] hero_placeItem = new String[] {"不限","并州","冀州","交州","荆州","凉州","青州","司隶",
            "徐州","兖州","扬州","益州","幽州","豫州"};
    String[] hero_yearItem = new String[] {"不限","公元184年","公元185年","公元186年","公元187年","公元188年","公元189年","公元190年",
            "公元191年","公元182年","公元193年", "公元194年","公元195年","公元196年","公元197年","公元198年","公元199年","公元200年",
            "公元201年","公元202年","公元203年", "公元204年","公元205年","公元206年","公元207年","公元208年","公元209年","公元210年",
            "公元211年","公元212年","公元213年", "公元214年","公元215年","公元216年","公元217年","公元218年","公元219年","公元220年",
            "公元221年","公元222年","公元223年", "公元224年","公元225年","公元226年","公元227年","公元228年","公元229年","公元230年",
            "公元231年","公元232年","公元233年", "公元234年","公元235年","公元236年","公元237年","公元238年","公元239年","公元240年",
            "公元241年","公元242年","公元243年", "公元244年","公元245年","公元246年","公元247年","公元248年","公元249年","公元250年",
            "公元251年","公元252年","公元253年", "公元254年","公元255年","公元256年","公元257年","公元258年","公元259年","公元260年",
            "公元261年","公元262年","公元263年", "公元264年","公元265年","公元266年","公元267年","公元268年","公元269年","公元270年",
            "公元271年","公元272年","公元273年", "公元274年","公元275年","公元276年","公元277年","公元278年","公元279年","公元280年"};
    String[] hero_sexItem = new String[] {"不限","男","女"};
    String[] hero_name = new String[] {"曹操","曹植","曹丕","貂蝉","董卓","关羽","刘备","孙权","小乔","张飞","周瑜","诸葛亮"};
    int[] hero_photos = new int[] {R.mipmap.cao_cao, R.mipmap.dong_zhuo, R.mipmap.diao_chan, R.mipmap.guan_yu,
            R.mipmap.liu_bei, R.mipmap.zhu_ge_liang, R.mipmap.sun_quan, R.mipmap.zhang_fei, R.mipmap.xiao_qiao, R.mipmap.zhou_yu};
    private GridView hero_view;
    private Toolbar tb;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<Hero> heros = new ArrayList<>();
    private List<Hero> herosTarget = new ArrayList<>();
    private Spinner firstLetterSpinner, hero_powerItemSpinner, hero_placeItemSpinner, hero_yearItemSpinner, hero_sexItemSpinner;
    private ListView listView;
    private EditText searchFrame;
    private Button searchButton;
    private ArrayAdapter firstLetterAdapter, hero_powerItemAdapter, hero_placeItemAdapter, hero_yearItemAdapter, hero_sexItemAdapter, listViewAdapter;
    private List<String> firstLetterList, hero_powerItemList, hero_placeItemList, hero_yearItemList, hero_sexItemList;
    private String firstLetterTarget, hero_powerTarget, hero_placeTarget, hero_yearTarget, hero_sexTarget, hero_nameTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        initDrawerLayout();
        initActionBar();
        initData();
        initViews();
        initSpinner();
        initSearchButton();
        initSearchFrame();

        hero_view = (GridView) findViewById(R.id.hero_view);
        final ArrayList<HashMap<String, Object>> heros = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", hero_photos[i]);
            map.put("text", hero_name[i]);
            heros.add(map);
        }

        SimpleAdapter herosAdapter = new SimpleAdapter(this, heros,
                R.layout.view_hero_in_grid,
                new String[] { "image", "text" },
                new int[] { R.id.hero_photo, R.id.hero_name});
        hero_view.setAdapter(herosAdapter);
        hero_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                hero_view.setPadding(0, 50, 0, 50);//长按后，让hero_view上下都分出点空间

                //有错误，还没有改！！！
                String name = " ";
                //String name = heros.get(position).getKey("text");

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("您要删除"+name+"?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //herosAdapter.remove(position);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
                builder.setCancelable(true);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //加载toolbar的两个按钮
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //toolbar按钮响应事件
        switch (item.getItemId()) {
            case R.id.search:
                //呼出侧边栏
                break;
            case R.id.add:
                //跳转新建页面
                break;

            //邹琳写的！！！
            case R.id.home:
                actionBarDrawerToggle.onOptionsItemSelected(item);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //首字母索引这个实现起来有点复杂，合并之后我来弄--周林
    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     *
     * @param sortKeyString
     *            数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

    private void initDrawerLayout() {
        DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawLayout.setScrimColor(Color.TRANSPARENT);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawLayout,
                tb, R.string.drawerOpen, R.string.drawerClose) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // 重新初始化heroTarget
                herosTarget.addAll(heros);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
        drawLayout.setDrawerListener(actionBarDrawerToggle);
    }
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("AppStore");
        actionBar.setDisplayHomeAsUpEnabled(true);//显示返回键
        actionBar.setHomeAsUpIndicator(R.mipmap.empty_star);
    }
    private void initData() {
        // put String into List
        firstLetterList = new ArrayList<>();
        for (int i = 0; i < firstLetterItem.length; i++) {
            firstLetterList.add(firstLetterItem[i]);
        }
        hero_powerItemList = new ArrayList<>();
        for (int i = 0; i < hero_powerItem.length; i++) {
            hero_powerItemList.add(hero_powerItem[i]);
        }
        hero_placeItemList = new ArrayList<>();
        for (int i = 0; i < hero_placeItem.length; i++) {
            hero_placeItemList.add(hero_placeItem[i]);
        }
        hero_yearItemList = new ArrayList<>();
        for (int i = 0; i < hero_yearItem.length; i++) {
            hero_yearItemList.add(hero_yearItem[i]);
        }
        hero_sexItemList = new ArrayList<>();
        for (int i = 0; i < hero_sexItem.length; i++) {
            hero_sexItemList.add(hero_sexItem[i]);
        }

        // init heros
        // 由主界面实现，这里只是随便举了几个例子进行测试
        heros.add(new Hero("C","曹操","男","豫州","155","220","魏","k",R.mipmap.cao_cao));
        heros.add(new Hero("C","曹丕","男","豫州","155","220","蜀","k",R.mipmap.cao_cao));
        heros.add(new Hero("C","曹布谷","女","豫州","155","220","蜀","k",R.mipmap.cao_cao));
        heros.add(new Hero("C","曹块","男","交州","155","220","魏","k",R.mipmap.cao_cao));
        heros.add(new Hero("B","布谷","女","豫州","155","220","魏","k",R.mipmap.cao_cao));

        // init target heros
        // 初始状态下所有heros都是target，通过属性进行不断筛选，删除herosTarget里面的heros
        herosTarget.addAll(heros);
    }
    private void initViews() {
        // get spinners' id
        firstLetterSpinner = (Spinner) findViewById(R.id.firstLetter);
        hero_powerItemSpinner = (Spinner) findViewById(R.id.hero_power);
        hero_placeItemSpinner = (Spinner) findViewById(R.id.hero_place);
        hero_yearItemSpinner = (Spinner) findViewById(R.id.hero_year);
        hero_sexItemSpinner = (Spinner) findViewById(R.id.hero_sex);

        // get other views' id
        listView = (ListView) findViewById(R.id.listView);
        searchFrame = (EditText) findViewById(R.id.searchFrame);
        searchButton = (Button) findViewById(R.id.searchButton);
    }
    private void initSpinner() {
        // init spinner's adapter
        firstLetterAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, firstLetterItem);
        firstLetterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstLetterSpinner.setAdapter(firstLetterAdapter);

        hero_powerItemAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hero_powerItemList);
        hero_powerItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hero_powerItemSpinner.setAdapter(hero_powerItemAdapter);

        hero_placeItemAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hero_placeItemList);
        hero_placeItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hero_placeItemSpinner.setAdapter(hero_placeItemAdapter);

        hero_yearItemAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hero_yearItemList);
        hero_yearItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hero_yearItemSpinner.setAdapter(hero_yearItemAdapter);

        hero_sexItemAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, hero_sexItemList);
        hero_sexItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hero_sexItemSpinner.setAdapter(hero_sexItemAdapter);
    }
    private void initSearchFrame() {
        // 初始化搜索框
        listViewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hero_name);
        listView.setAdapter(listViewAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchFrame.setText(hero_name[i]);
            }
        });
        searchFrame.addTextChangedListener(new TextWatcher() {
            @Override
            // 文本框发生改变前
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            // 文本框发生改变时
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            // 文本框发生改变后
            public void afterTextChanged(Editable editable) {
                if (editable.length() != 0) {
                    listView.setFilterText(editable.toString());
                } else {
                    listView.clearTextFilter();
                }
            }
        });
    }
    private void initSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取最终确定的搜索条件
                firstLetterTarget =  firstLetterSpinner.getSelectedItem().toString();
                hero_powerTarget = hero_powerItemSpinner.getSelectedItem().toString();
                hero_placeTarget = hero_placeItemSpinner.getSelectedItem().toString();
                hero_yearTarget = hero_yearItemSpinner.getSelectedItem().toString();
                hero_sexTarget = hero_sexItemSpinner.getSelectedItem().toString();
                hero_nameTarget = searchFrame.getText().toString();

                // 点击Search按钮后，左边栏缩回
                DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
                drawLayout.closeDrawer(Gravity.LEFT);
                Toast.makeText(getApplicationContext(),"进行搜索",Toast.LENGTH_SHORT).show();

                if (!firstLetterTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        if (!firstLetterTarget.equals(herosTarget.get(i).getFirstLetter())) {
                            herosTarget.remove(i);
                        }
                    }
                }
                if (!hero_powerTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        if (!hero_powerTarget.equals(herosTarget.get(i).getPower())) {
                            herosTarget.remove(i);
                        }
                    }
                }
                if (!hero_placeTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        if (!hero_placeTarget.equals(herosTarget.get(i).getPlace())) {
                            herosTarget.remove(i);
                        }
                    }
                }
                if (!hero_yearTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        int birthYear = Integer.parseInt(herosTarget.get(i).getBirth_year());
                        int deathYear = Integer.parseInt(herosTarget.get(i).getDeath_year());
                        int yearTarget = Integer.parseInt(hero_yearTarget.substring(2,5));
                        if (yearTarget > deathYear || yearTarget < birthYear) {
                            herosTarget.remove(i);
                        }
                    }
                }
                if (!hero_sexTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        if (!hero_sexTarget.equals(herosTarget.get(i).getSex())) {
                            herosTarget.remove(i);
                        }
                    }
                }
                if (!hero_nameTarget.equals("不限")) {
                    for (int i = 0; i < herosTarget.size(); i++) {
                        if (!hero_nameTarget.equals(herosTarget.get(i).getName())) {
                            herosTarget.remove(i);
                        }
                    }
                }
            }
        });
    }
}
