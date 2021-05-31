package dataaccesslayer;

import businesslayer.model.IRepository;
import businesslayer.model.IUser;

import java.io.*;
import java.util.List;

public final class FileIOManager {

    public static void writeToTxt(List<IRepository> repositories) {
        try {
            Writer writer= new BufferedWriter( new FileWriter("mostContributors.txt") );
            writer.write(getText(repositories));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getText(List<IRepository> repositories) {
        StringBuilder stringBuilder = new StringBuilder();
        for (IRepository repository : repositories){
            String repoName = repository.getName();
            for (IUser contributor : repository.getContributors()) {
                stringBuilder.append("repo:");
                stringBuilder.append(repoName);
                stringBuilder.append(" ,user:");
                stringBuilder.append(contributor.getLogin());
                stringBuilder.append(" ,location:");
                stringBuilder.append(contributor.getLocation());
                stringBuilder.append(" ,company:");
                stringBuilder.append(contributor.getCompany());
                stringBuilder.append(" ,contributions:");
                stringBuilder.append(contributor.getContributions());
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
