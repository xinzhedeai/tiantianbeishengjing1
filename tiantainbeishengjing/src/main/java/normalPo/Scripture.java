package normalPo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Scripture {
    private String scriptureNo;

    private String scriptureText;

    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date createDate;

    private Date updateDate;

    public String getScriptureNo() {
        return scriptureNo;
    }

    public void setScriptureNo(String scriptureNo) {
        this.scriptureNo = scriptureNo == null ? null : scriptureNo.trim();
    }

    public String getScriptureText() {
        return scriptureText;
    }

    public void setScriptureText(String scriptureText) {
        this.scriptureText = scriptureText == null ? null : scriptureText.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}