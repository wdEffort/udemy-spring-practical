package com.udemy.spring.practical.bbs.vo;

import java.sql.Timestamp;

public class BbsVO {

    private int bbsId;
    private String bbsName;
    private String bbsSbj;
    private String bbsCtt;
    private Timestamp bbsDate;
    private int bbsHit;
    private int bbsGroup;
    private int bbsStep;
    private int bbsIndent;

    public BbsVO() {
    }

    public BbsVO(int bbsId, String bbsName, String bbsSbj, String bbsCtt, Timestamp bbsDate, int bbsHit, int bbsGroup, int bbsStep, int bbsIndent) {
        this.bbsId = bbsId;
        this.bbsName = bbsName;
        this.bbsSbj = bbsSbj;
        this.bbsCtt = bbsCtt;
        this.bbsDate = bbsDate;
        this.bbsHit = bbsHit;
        this.bbsGroup = bbsGroup;
        this.bbsStep = bbsStep;
        this.bbsIndent = bbsIndent;
    }

    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public String getBbsSbj() {
        return bbsSbj;
    }

    public void setBbsSbj(String bbsSbj) {
        this.bbsSbj = bbsSbj;
    }

    public String getBbsCtt() {
        return bbsCtt;
    }

    public void setBbsCtt(String bbsCtt) {
        this.bbsCtt = bbsCtt;
    }

    public Timestamp getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(Timestamp bbsDate) {
        this.bbsDate = bbsDate;
    }

    public int getBbsHit() {
        return bbsHit;
    }

    public void setBbsHit(int bbsHit) {
        this.bbsHit = bbsHit;
    }

    public int getBbsGroup() {
        return bbsGroup;
    }

    public void setBbsGroup(int bbsGroup) {
        this.bbsGroup = bbsGroup;
    }

    public int getBbsStep() {
        return bbsStep;
    }

    public void setBbsStep(int bbsStep) {
        this.bbsStep = bbsStep;
    }

    public int getBbsIndent() {
        return bbsIndent;
    }

    public void setBbsIndent(int bbsIndent) {
        this.bbsIndent = bbsIndent;
    }
}
