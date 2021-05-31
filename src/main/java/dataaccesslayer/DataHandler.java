package dataaccesslayer;

import businesslayer.model.IRepository;
import businesslayer.model.IUser;
import businesslayer.model.Repository;
import businesslayer.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

public class DataHandler {

    public static List<IRepository> getTop5DownloadedRepositories(String url) {

        List<IRepository> top5Repository = new ArrayList<IRepository>();
        try {
            JSONArray repositoryJsonArray = new JSONArray( getJsonArray(url) );

            for (int i = 0; i < repositoryJsonArray.length(); i++) {
                JSONObject object = repositoryJsonArray.getJSONObject(i);
                IRepository repository = new Repository(
                        object.getString("name"),
                        object.getInt("forks_count"),
                        object.getString("contributors_url"));
                top5Repository.add(repository);
            }
            top5Repository.sort(Comparator.comparing(IRepository::getForksCount));
            Collections.reverse(top5Repository);
            top5Repository = top5Repository.subList(0, 5);
            setTop10Contributors(top5Repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return top5Repository;
    }

    private static String getJsonArray(String url) {

        String line;
        StringBuilder content = new StringBuilder();
        try {
            HttpURLConnection connection = getHttpURLConnection(url);
            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
            while ((line = reader.readLine()) != null)
                content.append(line);
            reader.close();

            return content.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL connectionUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) connectionUrl.openConnection();
        String token = "GITHUBPERSONALACCESSTOKEN" + ":x-oauth-basic";
        String authString = "Basic " + Base64.encodeBase64String(token.getBytes());
        connection.setRequestProperty("Authorization", authString);
        connection.setRequestMethod("GET");
        return connection;
    }

    private static void setTop10Contributors(List<IRepository> repositories) throws Exception{

        for (IRepository repository : repositories) {
            List<IUser> top10Contributors = new ArrayList<IUser>();
            JSONArray contributorsJsonArray = new JSONArray( getJsonArray(repository.getContributorsURL()) );

            for (int i = 0; i < contributorsJsonArray.length(); i++) {
                JSONObject object = contributorsJsonArray.getJSONObject(i);
                IUser contributor = new User(
                        object.getString("login"),
                        object.getInt("contributions"),
                        object.getString("url"));
                top10Contributors.add(contributor);
            }
            top10Contributors.sort(Comparator.comparing(IUser::getContributions));
            Collections.reverse(top10Contributors);
            top10Contributors = top10Contributors.subList(0, 10);
            setFieldsOfContributors(top10Contributors);

            repository.setContributors(top10Contributors);
        }
    }

    private static void setFieldsOfContributors(List<IUser> top10Contributors) throws JSONException {

        for (IUser contributor : top10Contributors) {
                JSONObject object = new JSONObject(getJsonArray(contributor.getUserURL()));
                contributor.setLocation(object.getString("location"));
                contributor.setCompany(object.getString("company"));
            }
        }
    }
