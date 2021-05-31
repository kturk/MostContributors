package businesslayer.model;

public interface IUser {
    String getLogin();

    String getLocation();

    void setLocation(String location);

    String getCompany();

    void setCompany(String company);

    Integer getContributions();

    String getUserURL();
}
