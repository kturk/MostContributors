package businesslayer;

import businesslayer.model.IRepository;
import dataaccesslayer.DataHandler;
import dataaccesslayer.FileIOManager;

import java.util.List;

public class GitHubManager {

    private final static String URL = "https://api.github.com/users/apache/repos";

    public static void start() {

        List<IRepository> top5DownloadedRepositories = DataHandler.getTop5DownloadedRepositories(URL);
        FileIOManager.writeToTxt(top5DownloadedRepositories);
    }
}
