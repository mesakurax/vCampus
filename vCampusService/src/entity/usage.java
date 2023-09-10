package entity;

public class usage {
    private String prompt_tokens;
    private String completion_tokens;
    private String total_tokens;

    public usage() {
    }

    public usage(String prompt_tokens, String completion_tokens, String total_tokens) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
    }

    public String getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(String prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public String getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(String completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public String getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(String total_tokens) {
        this.total_tokens = total_tokens;
    }
}
