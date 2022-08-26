package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.model.bo.CandidateSimpleBO;
import com.zss.hrims.model.entity.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 23:46
 * @desc 候选人信息持久化
 */
@Mapper
@Repository
public interface CandidateMapper extends BaseMapper<Candidate> {

    /**
     * 分页多条件查询候选人信息
     *
     * @param candPage   分页信息
     * @param name       候选人姓名 -- 模糊匹配
     * @param rating     评级
     * @param work       是否入职
     * @param candIdList 候选人Id
     * @param startTime  起始时间
     * @param endTime    结束时间
     * @return page
     */
    IPage<CandidateSimpleBO> selectCandSimple(IPage<Candidate> candPage, @Param("name") String name,
                                              @Param("rating") Integer rating, @Param("work") Boolean work,
                                              @Param("candIdList") List<String> candIdList,
                                              @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 根据状态统计该企业该岗位的候选人
     *
     * @param postId 岗位Id
     * @param entId  企业Id
     * @param code   状态
     * @return count
     */
    Integer countCandByState(@Param("postId") String postId, @Param("entId") String entId, @Param("code") Integer code);
}
