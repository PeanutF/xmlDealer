package com.leadOut.entity;

public class PhotoFile {

    private int id;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    private int photoId;


    private String name_ = "";
    private String createDate = "";
    private String camerist = "";
    private String creator = "";

    private String description = "";
    private String security = "";
    private String createUser_ = "";
    private String createDate_ = "";
    private String originalPath = "";

    private String baodaorenwu = "";
    private String categoryCode = "";
    private String yewu = "";
    private String privilege_ = "";
    private String keyWord = "";
    private long fileSize = 0;
    private String fileext = "";

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getYewu() {
        return yewu;
    }

    public void setYewu(String yewu) {
        this.yewu = yewu;
    }

    public String getPrivilege_() {
        return privilege_;
    }

    public void setPrivilege_(String privilege_) {
        this.privilege_ = privilege_;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileext() {
        return fileext;
    }

    public void setFileext(String fileext) {
        this.fileext = fileext;
    }

    public String getBaodaorenwu() {
        return baodaorenwu;
    }

    public void setBaodaorenwu(String baodaorenwu) {
        this.baodaorenwu = baodaorenwu;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCamerist() {
        return camerist;
    }

    public void setCamerist(String camerist) {
        this.camerist = camerist;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getCreateUser_() {
        return createUser_;
    }

    public void setCreateUser_(String createUser_) {
        this.createUser_ = createUser_;
    }

    public String getCreateDate_() {
        return createDate_;
    }

    public void setCreateDate_(String createDate_) {
        this.createDate_ = createDate_;
    }
}
