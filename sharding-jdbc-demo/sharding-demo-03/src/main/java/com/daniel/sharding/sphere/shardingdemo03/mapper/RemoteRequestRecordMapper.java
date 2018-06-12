package com.daniel.sharding.sphere.shardingdemo03.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.daniel.sharding.sphere.shardingdemo03.entity.RemoteRequestRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 发送数据到第三方记录 Mapper 接口
 * </p>
 *
 * @author DaiZM
 * @since 2018-05-15
 */
@Mapper
public interface RemoteRequestRecordMapper extends BaseMapper<RemoteRequestRecord> {

}
