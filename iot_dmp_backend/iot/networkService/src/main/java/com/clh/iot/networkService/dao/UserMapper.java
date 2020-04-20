package com.clh.iot.networkService.dao;

import com.clh.iot.networkService.pojo.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 模糊查询多个
     * @param map
     * @return
     */
    List<User> selectManyTusers(Map map);

    /**
     * 查询总数
     * @return
     */
    Integer selectCounts();
}