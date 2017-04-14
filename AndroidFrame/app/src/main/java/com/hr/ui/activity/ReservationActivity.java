package com.hr.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.hr.R;
import com.hr.base.BaseActivity;
import com.hr.base.BaseAdapter;
import com.hr.base.RecyclerViewHolder;
import com.hr.bean.Department;
import com.hr.bean.DepartmentInfo;
import com.hr.network.api.SubjectPostApi;
import com.hr.ui.view.TitleBarView;
import com.hr.utils.net.http.HttpManager;
import com.hr.utils.net.listener.HttpOnNextListener;
import com.hr.utils.pinyin.PinYinUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.OnTextChanged;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 院区科室查询
 * Created by Han on 2017/4/5.
 */

public class ReservationActivity extends BaseActivity {

    @Bind(R.id.left_rlv)
    RecyclerView leftRlv;
    @Bind(R.id.right_rlv)
    RecyclerView rightRlv;
    @Bind(R.id.search_et)
    EditText search;
    @Bind(R.id.titleBar)
    TitleBarView mTitleBar;

    private BaseAdapter<DepartmentInfo> adapter;
    private BaseAdapter<ArrayList<DepartmentInfo>> areaAdapter;
    private ArrayList<DepartmentInfo> data;
    private ArrayList<DepartmentInfo> list;
    private ArrayList<ArrayList<DepartmentInfo>> areaList;
    private TreeMap<Integer, String> treeMap;
    private ArrayList<Integer> code;
    private ArrayList<String> name;
    private ArrayList<DepartmentInfo> lists;


    @Override
    protected int getLayoutRes() {
        return R.layout.reservation;
    }

    @Override
    protected void init() {
        mTitleBar.setOnLeftClick(new TitleBarView.OnLeftClick() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取科室数据
        departmentTest();

        //初始化左侧列表 院区列表
        creatLeftRv();

        //初始化右侧列表 对应院区的科室列表
        creatRightRv();

    }

    /**
     * 初始化左侧列表 院区列表
     */
    private void creatLeftRv() {
        data = new ArrayList<>();
        list = new ArrayList<>();
        areaList = new ArrayList<>();
        leftRlv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        areaAdapter = new BaseAdapter<ArrayList<DepartmentInfo>>(mActivity, areaList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.reservation_left;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, ArrayList<DepartmentInfo> item) {
                holder.setText(R.id.tv_area, name.get(position));
            }
        };

        leftRlv.setAdapter(areaAdapter);
        areaAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                //点击条目获取相应的院区科室列表
                ArrayList<DepartmentInfo> departmentInfos = areaList.get(pos);
                lists.clear();
                lists.addAll(departmentInfos);
                adapter.notifyDataSetChanged();

            }
        });

    }

    /**
     * 初始化右侧列表 对应院区的科室列表
     */
    private void creatRightRv() {
        lists = new ArrayList<>();
        rightRlv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        adapter = new BaseAdapter<DepartmentInfo>(mActivity, lists) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.reservation_right;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, DepartmentInfo item) {
                holder.setText(R.id.name_tv, item.getDeptName());
            }
        };
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(mActivity, DayTabActivity.class);
                intent.putExtra("code", lists.get(pos).getDeptCode());
                startActivity(intent);
            }
        });
        rightRlv.setAdapter(adapter);
    }


    /**
     * 搜索科室
     *
     * @param cs
     */
    @OnTextChanged(R.id.search_et)
    public void onTextChange(final CharSequence cs) {
        final String text = cs.toString();
        if (text.length() < 1) {
            //默认显示第一个院区
            if (areaList != null && areaList.size() > 0) {
                lists.clear();
                lists.addAll(areaList.get(0));
                adapter.notifyDataSetChanged();
            }
            return;
        }
        if (data != null && data.size() > 0) {
            lists.clear();
            if (text.length() > 0) {
                //将输入的汉字转换为字母
                Observable.create(new Observable.OnSubscribe<List<DepartmentInfo>>() {
                    @Override
                    public void call(Subscriber<? super List<DepartmentInfo>> subscriber) {
                        String input = PinYinUtils.getPinYin(text);
                        for (int i = 0; i < data.size(); i++) {
                            DepartmentInfo departmentInfo = data.get(i);
                            String deptName = departmentInfo.getDeptName();
                            String name = PinYinUtils.getPinYin(deptName);
                            if (name.contains(input)) {
                                lists.add(departmentInfo);
                            }
                        }
                        subscriber.onNext(lists);
                        subscriber.onCompleted();
                    }
                })
                        .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                        .subscribe(new Observer<List<DepartmentInfo>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<DepartmentInfo> departmentInfos) {
                                adapter.notifyDataSetChanged();
                            }
                        });
            } else {
                list.clear();
            }
        }
    }


    /**
     * 获取科室数据
     */
    private void departmentTest() {
        SubjectPostApi postEntity = new SubjectPostApi(departmentOnNextListener, this, SubjectPostApi.DEPARTMENT)
                .setDepartment();
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(postEntity);

    }

    HttpOnNextListener<Department> departmentOnNextListener = new HttpOnNextListener<Department>() {
        @Override
        public void onNext(Department department) {
            //获取科室信息
            data.addAll(department.getData());

            //初始化左侧院区列表数据
            initLeftData(data);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    };


    /**
     * 初始化左侧院区列表数据
     *
     * @param data
     */
    private void initLeftData(final ArrayList<DepartmentInfo> data) {
        //存储院区编码和名称,升序排序
        treeMap = new TreeMap<>();
        code = new ArrayList<>();
        name = new ArrayList<>();

        Observable.create(new Observable.OnSubscribe<ArrayList<DepartmentInfo>>() {
            @Override
            public void call(Subscriber<? super ArrayList<DepartmentInfo>> subscriber) {

                for (int i = 0; i < data.size(); i++) {
                    DepartmentInfo departmentInfo = data.get(i);
                    int deptDistrict = departmentInfo.getDeptDistrict();
                    String deptDistrictName = departmentInfo.getDeptDistrictName();
                    //获取院区编码和名称
                    treeMap.put(deptDistrict, deptDistrictName);
                }

                //遍历LinkedHashMap
                Iterator<Map.Entry<Integer, String>> iteratorMap = treeMap.entrySet().iterator();

                while (iteratorMap.hasNext()) {
                    Map.Entry<Integer, String> next = iteratorMap.next();
                    Integer key = next.getKey();
                    String value = next.getValue();
                    code.add(key);
                    name.add(value);
                    //院区列表集合
                    ArrayList<DepartmentInfo> list = new ArrayList<>();
                    areaList.add(list);
                }

                for (int i = 0; i < data.size(); i++) {
                    DepartmentInfo departmentInfo = data.get(i);
                    int deptDistrict = departmentInfo.getDeptDistrict();
                    for (int j = 0; j < code.size(); j++) {
                        if (deptDistrict == code.get(j)) {
                            areaList.get(j).add(departmentInfo);
                        }
                    }
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<DepartmentInfo>>() {
                    @Override
                    public void onCompleted() {
                        areaAdapter.notifyDataSetChanged();
                        //显示左侧院区第一个条目的科室列表
                        if (areaList != null && areaList.size() > 0) {
                            lists.clear();
                            lists.addAll(areaList.get(0));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<DepartmentInfo> departmentInfos) {

                    }
                });


    }

}
