package cn.bsd.learn.refactor.sample;

public class ResponseClass {
//{"resultcode":"101","reason":"错误的请求KEY","result":null,"error_code":10001}
    private int resultcode;
    private String reason;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResponseClass{" +
                "resultcode=" + resultcode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
