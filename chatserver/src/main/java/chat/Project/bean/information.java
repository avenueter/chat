package chat.Project.bean;

public class information {

    private int Iid;
    private String nickname;
    private String signature;
    private int uid;

    public int getIid() {
        return Iid;
    }

    public void setIid(int iid) {
        Iid = iid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "information{" +
                "Iid=" + Iid +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", uid=" + uid +
                '}';
    }
}
