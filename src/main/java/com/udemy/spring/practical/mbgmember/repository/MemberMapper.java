package com.udemy.spring.practical.mbgmember.repository;

import com.udemy.spring.practical.mbgmember.vo.Member;
import com.udemy.spring.practical.mbgmember.vo.MemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    long countByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int deleteByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int insert(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int insertSelective(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    List<Member> selectByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    Member selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UDEMY.MEMBER
     *
     * @mbg.generated Fri Feb 19 15:46:14 KST 2021
     */
    int updateByPrimaryKey(Member record);
}