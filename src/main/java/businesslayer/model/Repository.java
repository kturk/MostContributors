package businesslayer.model;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    private final String name;
    private final Integer forksCount;
    private final String contributorsURL;
    private List<IUser> contributors;

    public Repository() {

        this.name = "";
        this.forksCount = 0;
        this.contributorsURL = "";
        this.contributors = new ArrayList<IUser>();
    }

    public Repository(String name, int forksCount, String contributorsURL) {

        this.name = name;
        this.forksCount = forksCount;
        this.contributorsURL = contributorsURL;
        this.contributors = new ArrayList<IUser>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getForksCount() {
        return forksCount;
    }

    @Override
    public String getContributorsURL() {
        return contributorsURL;
    }

    @Override
    public List<IUser> getContributors() {
        return contributors;
    }

    @Override
    public void setContributors(List<IUser> contributors) {
        this.contributors = contributors;
    }
}
