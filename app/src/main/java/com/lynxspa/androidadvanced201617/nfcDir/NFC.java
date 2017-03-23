package com.lynxspa.androidadvanced201617.nfcDir;

public class NFC {

    private String tagName;
    private String tagId;
    private int profileId;

    public NFC(String tagName, String tagId, int profileId) {
        this.tagName = tagName;
        this.tagId = tagId;
        this.profileId = profileId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
