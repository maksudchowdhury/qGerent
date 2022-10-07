package com.example.qgerent;

public class requestQ {
    String cName, cEmail, cService;

    public requestQ() {

    }
    public requestQ(String cName, String cEmail, String cService) {
        this.cName = cName;
        this.cEmail = cEmail;
        this.cService = cService;
    }

    public String getcName() {
        return cName;
    }

    public String getcEmail() {
        return cEmail;
    }

    public String getcService() {
        return cService;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public void setcService(String cService) {
        this.cService = cService;
    }
}
