package com.duobei.core.user.user.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.ClassUtil;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.dao.ProductAuthConfigDao;
import com.duobei.core.operation.product.domain.vo.ProductAuthConfigVo;
import com.duobei.core.user.user.dao.UserAuthDao;
import com.duobei.core.user.user.domain.UserAuth;
import com.duobei.core.user.user.domain.vo.UserAuthListVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserAuthService;
import com.duobei.dic.ZD;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/16
 */
@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    ProductAuthConfigDao productAuthConfigDao;

    @Resource
    UserAuthDao userAuthDao;


    @Override
    public void resetUserAuthByUserId(String authCode, Long userId) throws TqException {
        int count = userAuthDao.resetState(authCode,userId);
        if (count != 1){
            throw new TqException("重置认证状态失败");
        }
    }

    @Override
    public List<UserAuthListVo> getAuthListVoByUser(UserInfoVo userVo) throws TqException {

        //获取产品认证项信息
        List<ProductAuthConfigVo> productAuthConfigVos = productAuthConfigDao.getByProductId(userVo.getProductId());
        //获取用户认证信息
        UserAuth userAuth = userAuthDao.getByUserId(userVo.getId());
        List<UserAuthListVo> data = new ArrayList<>();
        for (ProductAuthConfigVo productAuthConfigVo : productAuthConfigVos){
            UserAuthListVo listVo = new UserAuthListVo();
            String name = productAuthConfigVo.getAuthCode();
            if ("realname".startsWith(name)){
                //实名认证分为上传身份证状态，人脸识别度
                //上传身份证

                listVo.setCode(name+"_idcard");
                listVo.setAuthName("实名认证（上传身份证）");
                String state =  ClassUtil.getClassValueByClassName(name+"IdcardState",userAuth);
                if (state == null){
                    state =  ClassUtil.getClassValueByClassName(name+"IdcardStatus",userAuth);
                }
                //认证状态
                listVo.setAuthState(DictUtil.getDictLabel(ZD.authState,state));
                getAuthTime(listVo,name+"Idcard",userAuth);
                data.add(listVo);
                UserAuthListVo listVo1 = new UserAuthListVo();
                //人脸识别
                listVo1.setCode(name+"_face");
                listVo1.setAuthName("实名认证（人脸识别）");

                state =  ClassUtil.getClassValueByClassName(name+"FaceState",userAuth);
                if (state == null){
                    state =  ClassUtil.getClassValueByClassName(name+"FaceStatus",userAuth);
                }
                //认证状态
                listVo1.setAuthState(DictUtil.getDictLabel(ZD.authState,state));
                getAuthTime(listVo1,name+"Face", userAuth);
                data.add(listVo1);
            }else{
                listVo.setCode(productAuthConfigVo.getAuthCode());
                listVo.setAuthName(productAuthConfigVo.getAuthName());
                String state =  ClassUtil.getClassValueByClassName(name+"State",userAuth);
                if (state == null){
                    state =  ClassUtil.getClassValueByClassName(name+"Status",userAuth);
                }
                //认证状态
                listVo.setAuthState(DictUtil.getDictLabel(ZD.authState,state));
                //时间
                getAuthTime(listVo,name, userAuth);
                if ("zhima".startsWith(name)){
                    listVo.setOperState("reset");
                }
                data.add(listVo);

            }
        }
        //排序
        listSort(data);
        return data;
    }

    private void getAuthTime(UserAuthListVo listVo, String name, UserAuth userAuth) throws TqException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
        try {
            String time = ClassUtil.getClassValueByClassName("gmt"+(new StringBuilder()).append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString(),userAuth);
            if (time != null) {
                listVo.setAuthTime(sdf.parse(time));
            }else{
                listVo.setAuthTime(null);
            }
        } catch (Exception e) {
            throw new TqException(e);
        }
    }

    private  void listSort(List<UserAuthListVo> list) {
        Collections.sort(list, new Comparator<UserAuthListVo>() {
            @Override
            public int compare(UserAuthListVo o1, UserAuthListVo o2) {
                try {
                    Date dt1 = new Date(0);
                    if(o1.getAuthTime() != null){
                        dt1 = o1.getAuthTime();
                    }
                    Date dt2 = new Date(0);
                    if(o2.getAuthTime() != null ){
                        dt2 = o2.getAuthTime();
                    }
                    if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
