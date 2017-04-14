package com.hr.network.api;

import com.hr.bean.PreRegisterInfo;
import com.hr.network.HttpPostService;
import com.hr.utils.SpUtil;
import com.hr.utils.net.Api.BaseApi;
import com.hr.utils.net.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 测试数据
 * Created by WZG on 2016/7/16.
 */
public class SubjectPostApi extends BaseApi {
    public static final int LOGIN = 20001;
    public static final int REGISTER = 20002;
    // 查询通讯录
    public static final int GET_ADDRESS = 20003;

    // 增加联系人
    public static final int ADD_ADDRESS = 20004;
    // 删除联系人
    public static final int DETEDE_ADDRESS = 20005;
    // 预约保存
    public static final int SAVE_ORDER_INFORMATION = 20006;

    public static final int GET_DOC_LIST = 30001;

    public static final int GET_REG_LIST = 30002;

    public static final int DEL_PREREGISTER = 30003;
    // 挂号科室查询
    public static final int DEPARTMENT = 40001;
    // 预约科室下医生信息查询
    public static final int DETAILS_DEPARTMENT = 40002;
    private int apiType ;
    private String account;
    private String passWd;
    private String datepre;
    private String deptname;
    //科室号
    private int deptCode;
    private String doctorcode;
    private String reggrade;


    // 通讯录名字
    private String listname;
    // 通讯录电话
    private String listphone;
    // 通讯录身份证信息
    private String certificate;
    // 对应的id，用于删除某联系人
    private String listId;
    private String preId;
    private String datePre;
    private String car;
    private String doctor;
    private  int midday;

    public SubjectPostApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, int apiType) {
        super(listener, rxAppCompatActivity);
        this.apiType = apiType;
    }

    public SubjectPostApi setLogin(String account,String passwd){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        this.account = account;
        this.passWd = passwd;
        return this;
    }

    /**
     * 挂号科室查询
     * @return
     */
    public SubjectPostApi setDepartment(){
        setShowProgress(true);
        setCancel(true);
        setCache(false);
        setMothed("registerLogin/getDeptList.action");
        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24*60*60);
        return this;
    }

    /**
     * 预约科室下医生信息查询
     * @return
     */
    public SubjectPostApi setDetailsDepartment(String account){
        setShowProgress(true);
        setCancel(true);
        setCache(false);
        setMothed("registerLogin/getRegisterList.action");
        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24*60*60);
        this.account = account;
        return this;
    }

    /**
     * 查询联系人
     * @param account
     * @return
     */
    public SubjectPostApi setGetAdressBook(String account){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        setMothed("registerLogin/queryContacts.action");
        this.account = account;
        return this;
    }


    public SubjectPostApi setGetDocList(int deptCode){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        this.deptCode = deptCode;
        return this;
    }
    public SubjectPostApi saveOrderInformation(String account,String listname,String listphone,String certificate,String datepre,int midday,int deptCode,String deptname,String doctorcode,String doctor,String reggrade){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        setMothed("registerLogin/queryContacts.action");
        this.account = account;
        this.listname = listname;
        this.listphone = listphone;
        this.certificate = certificate;
        this.datepre = datepre;
        this.midday = midday;
        this.deptCode = deptCode;
        this.deptname = deptname;
        this.doctorcode = doctorcode;
        this.doctor = doctor;
        this.reggrade = reggrade;
        return this;
    }
    /**
     * 增加联系人
     * @param account
     * @return
     */
    public SubjectPostApi setAddAdressBook(String account,String listname,String listphone,String certificate){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        setMothed("registerLogin/queryContacts.action");
        this.account = account;
        this.listname = listname;
        this.listphone = listphone;
        this.certificate = certificate;
        return this;
    }
    /**
     * 删除联系人
     * @param
     * @return
     */
    public SubjectPostApi deteleAddAdressBook(String listid){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        setMothed("registerLogin/deleteContacts.action");
        this.listId = listid;
        return this;
    }




    /**
     * 挂号信息查询
     * @return
     */
    public SubjectPostApi getPreRegisterList(){
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        return this;
    }

    /**
     * 删除已挂号
     * @return
     */
    public SubjectPostApi delPreregister(PreRegisterInfo info){
        preId = info.getId();
        this.datePre = info.getPreregisterDate();
        this.deptCode = info.getPreregisterDept();
        this.doctor = info.getPreregisterExpert();
        this.midday = info.getMidday();
        this.car = info.getPreregisterCertificatesno();
        return this;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService service = retrofit.create(HttpPostService.class);
        switch (apiType) {
            case LOGIN:
                return service.getAllVedioBy(account,passWd);
            case REGISTER:
                return service.getLoginAndRegisterService(account,passWd,2);
            case GET_ADDRESS:
                return  service.getAddressBooks(account);
            case DEPARTMENT:
                //查询科室
                return  service.getDepartment();
            case DETAILS_DEPARTMENT:
                //预约科室下医生信息查询
                return  service.getDetailsDepartment(account);
            case ADD_ADDRESS:
                return  service.Add_AddressBooks(account,listname,listphone,certificate);
            case DETEDE_ADDRESS:
                return service.detele_AddressBooks(listId);
            case GET_DOC_LIST:
                return  service.getDocLists(deptCode);
            case GET_REG_LIST:
                return service.getPreRegisterList(SpUtil.getString("account",""));
            case DEL_PREREGISTER:
                return service.delPreregister(preId,datePre,deptCode,doctor,midday,car);
            case SAVE_ORDER_INFORMATION:
                return  service.saveOdorderInformation(account,listname,listphone,certificate,datepre,midday,deptCode,deptname,doctorcode,doctor,reggrade);
            default:
                return service.getAllVedioBy("zpty1001","123");
        }
    }
}
