package entity;

public class ChatInnerResp {
    private String id;
    private String object;
    private String created;
    private String result;
    private Boolean is_truncated;
    private Boolean need_clear_history;
    private usage usage;

    public ChatInnerResp(String id, String object, String created, String result, Boolean is_truncated, Boolean need_clear_history, usage usage) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.result = result;
        this.is_truncated = is_truncated;
        this.need_clear_history = need_clear_history;
        this.usage = usage;
    }

    public ChatInnerResp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getIs_truncated() {
        return is_truncated;
    }

    public void setIs_truncated(Boolean is_truncated) {
        this.is_truncated = is_truncated;
    }

    public Boolean getNeed_clear_history() {
        return need_clear_history;
    }

    public void setNeed_clear_history(Boolean need_clear_history) {
        this.need_clear_history = need_clear_history;
    }

    public usage getUsage() {
        return usage;
    }

    public void setUsage(usage usage) {
        this.usage = usage;
    }
}
