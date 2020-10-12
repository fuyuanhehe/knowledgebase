package com.hg.knowledgebase.module.repo.entity;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述: 文档管理
 * @auther: fuyuan
 * @date: 2020/3/16 0016 10:54
 */
public class BaseDocument
{
    /**
     * 文档id
     */
    private String id;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 文档类型（0：文件夹；1：文件）
     */
    private Short type;

    /**
     * 文档大小（bit）
     */
    private BigDecimal size;

    /**
     * 文档后缀
     */
    private String suffix;

    /**
     * 文档父id   0：首级文档
     */
    private String pid;

    /**
     * 文档类型（0：公司文档；1：部门文档；2：个人文档 3:知识文档 4:动态图 5:项目文档）
     */
    private Short weight;

    /**
     * 文档地址
     */
    private String url;

    /**
     * 文档类别id
     */
    private String categoryId;

    /**
     * 创建人部门id
     */
    private String deptId;

    /**
     * 文档封面地址
     */
    private String coverUrl;

    /**
     * 封面大小
     */
    private BigDecimal coverSize;

    /**
     * 浏览量
     */
    private Integer browseNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 下载数
     */
    private Integer downloadNum;

    /**
     * 修改数
     */
    private Integer udpateNum;

    /**
     * 打印数
     */
    private Integer printNum;

    /**
     * 改正记录数
     */
    private Integer correctionNum;

    /**
     * 是否存在子集 sql 需要 (1: 有   0： 否)
     */
    private String reserve1;

    /**
     * 动态图正文
     */
    private String reserve2;

    /**
     * 是否精品文档 (0:是   1：否)
     */
    private String reserve3;

    /**
     * 文档预留4 sql用 所属分类名称     */
    private String reserve4;

    /**
     * 项目id  project_id
     */
    private String projectId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
   // @JsonFormat(timezone = "GMT+8", pattern = "yyyy:MM:dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public BigDecimal getCoverSize() {
        return coverSize;
    }

    public void setCoverSize(BigDecimal coverSize) {
        this.coverSize = coverSize;
    }

    public  Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum( Integer browseNum) {
        this.browseNum = browseNum;
    }

    public  Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum( Integer commentNum) {
        this.commentNum = commentNum;
    }

    public  Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum( Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public  Integer getUdpateNum() {
        return udpateNum;
    }

    public void setUdpateNum( Integer udpateNum) {
        this.udpateNum = udpateNum;
    }

    public  Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum( Integer printNum) {
        this.printNum = printNum;
    }

    public  Integer getCorrectionNum() {
        return correctionNum;
    }

    public void setCorrectionNum( Integer correctionNum) {
        this.correctionNum = correctionNum;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4 == null ? null : reserve4.trim();
    }

    public String getprojectId() {
        return projectId;
    }

    public void setprojectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }




}
