package businesslayer.model;

import java.util.List;

public interface IRepository {
    String getName();

    Integer getForksCount();

    String getContributorsURL();

    List<IUser> getContributors();

    void setContributors(List<IUser> contributors);
}
