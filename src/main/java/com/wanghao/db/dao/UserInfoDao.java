package com.wanghao.db.dao;

import com.wanghao.db.model.UserInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 8/5/17.
 */
public interface UserInfoDao {
    int save(UserInfoModel userInfoModel);

    UserInfoModel findById(@Param("id") long id);

    UserInfoModel findByNickname(@Param("nickname") String nickname);

    UserInfoModel findByEmail(@Param("email") String email);

    UserInfoModel findByUserCode(@Param("userCode") String userCode);

    List<UserInfoModel> queryAll();

    void updateActivatedByNickname(@Param("nickname") String nickname, @Param("activated") int activated);

    void updatePasswordByNickname(@Param("nickname") String nickName, @Param("newPassword") String newPassword);

    void updatePhoneAddressByUserCode(@Param("newPhone") String newPhone, @Param("newAddress") String newAddress,
                                      @Param("userCode") String userCode);
}
