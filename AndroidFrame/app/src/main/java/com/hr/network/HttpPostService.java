package com.hr.network;


import com.hr.bean.AdressListInfo;
import com.hr.bean.DocInfo;
import com.hr.bean.ListDataInfo;
import com.hr.bean.Department;
import com.hr.bean.DetailsDepartment;
import com.hr.bean.LoginInfo;
import com.hr.bean.PreRegisterInfo;
import com.hr.utils.net.Api.BaseResultEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface HttpPostService {

    @GET("mobile/login.action")
    Observable<LoginInfo> getAllVedioBy(@Query("account") String userAccount, @Query("passwd") String pass);

    @FormUrlEncoded
    @POST("registerLogin/login.action")
    Observable<LoginInfo> getLoginAndRegisterService(@Field("account") String userAccount, @Field("passwd") String pass
            , @Field("flag") int flag);
    @FormUrlEncoded
    @POST("registerLogin/getRegisterList.action")
    Observable<ListDataInfo<DocInfo>> getDocLists(@Field("deptCode") int code);
    @GET("mobile/login.action")
    Observable<LoginInfo> getAllVedioBys(@Query("once_no") boolean once_no);
    /**
     * 获取通讯录接口
     */
    @FormUrlEncoded
    @POST("registerLogin/queryContacts.action")
    Observable<AdressListInfo> getAddressBooks(@Field("account") String account);
    /**
     * 增加联系人
     */
    @FormUrlEncoded
    @POST("registerLogin/addContacts.action")
    Observable<BaseResultEntity> Add_AddressBooks(@Field("account") String account, @Field("listName") String listname, @Field("listPhone") String listphone, @Field("certificate") String certificate);
    /**
     * 删除联系人
     */
    @FormUrlEncoded
    @POST("registerLogin/deleteContacts.action")
    Observable<BaseResultEntity> detele_AddressBooks(@Field("listId") String listId);

    /**
     * 挂号科室查询
     * @return
     */
    @POST("registerLogin/getDeptList.action")
    Observable<Department> getDepartment();

    /**
     * 预约科室详细查询
     * @param deptCode
     * @return
     */
    @FormUrlEncoded
    @POST("registerLogin/getRegisterList.action")
    Observable<DetailsDepartment> getDetailsDepartment(@Field("deptCode") String deptCode);


    /**
     * 挂号信息查询
     * @param account
     * @return
     */
    @FormUrlEncoded
    @POST("registerLogin/getPreregisterList.action")
    Observable<ListDataInfo<PreRegisterInfo>> getPreRegisterList(@Field("account") String account);

    /**
     * 删除
     */

    @FormUrlEncoded
    @POST("registerLogin/delPreregister.action")
    Observable<BaseResultEntity> delPreregister(@Field("prereId") String id, @Field("datePre") String datePre
            , @Field("deptCode") int deptCode, @Field("Doctor") String Doctor, @Field("Midday") int Midday
            , @Field("Certificate") String Certificate);
    /**
     * 预约保存
     */
    @FormUrlEncoded
    @POST("registerLogin/savePreregister.action")
    Observable<BaseResultEntity> saveOdorderInformation(@Field("account") String account,@Field("listName") String listname,@Field("listPhone") String listphone,@Field("Certificate")String  Certificate,@Field("datePre")String datePre,@Field("Midday")int middle,@Field("deptCode") int deptCode,@Field("Deptname")String Deptname,@Field("Doctor")String
            Doctorcode,@Field("Doctorname")String Doctorname,@Field("Reggrade") String Reggrade);
}
