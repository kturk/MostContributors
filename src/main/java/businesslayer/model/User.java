package businesslayer.model;

public class User implements IUser {

    private final String login;
    private String location;
    private String company;
    private final Integer contributions;
    private String userURL;

    public User() {

        this.login = "";
        this.location = "";
        this.company = "";
        this.contributions = 0;
        this.userURL = "";
    }

    public User(String login, Integer contributions, String userURL) {

        this.login = login;
        this.contributions = contributions;
        this.userURL = userURL;
    }

    public User(String login, String location, String company, Integer contributions) {

        this.login = login;
        this.location = location;
        this.company = company;
        this.contributions = contributions;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public Integer getContributions() {
        return contributions;
    }

    @Override
    public String getUserURL() {
        return userURL;
    }
}
