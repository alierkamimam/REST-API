package pojo;

import java.util.List;

public class Courses {
    private List<WebAutomation> WebAutomation;
    private List<Api> Api;
    private List<Mobile> Mobile;

    public List<WebAutomation>  getWebAutomation() {
        return WebAutomation;
    }

    public void setWebAutomation(List<WebAutomation>  webAutomation) {
        WebAutomation = webAutomation;
    }

    public List<Api> getApi() {
        return Api;
    }

    public void setApi(List<Api> api) {
        Api = api;
    }

    public List<Mobile> getMobile() {
        return Mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        Mobile = mobile;
    }
}
