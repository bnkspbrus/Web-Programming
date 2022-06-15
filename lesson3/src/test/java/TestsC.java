import com.github.mjeanroy.junit.servers.annotations.TestServer;
import com.github.mjeanroy.junit.servers.junit4.JunitServerRunner;
import com.github.mjeanroy.junit.servers.tomcat.EmbeddedTomcat;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.http.util.EntityUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
@RunWith(JunitServerRunner.class)
public class TestsC {
    @TestServer
    private static EmbeddedTomcat tomcat;

    HashMap<String, String> user_cookie = new HashMap<>();

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private CloseableHttpResponse doAuth(String user) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(tomcat.getUrl() + "/message/auth");
        builder.setParameter("user", user);
        HttpPost request = new HttpPost(builder.build());
        return httpClient.execute(request);
    }

    private CloseableHttpResponse doAdd(String cookie) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(tomcat.getUrl() + "/message/add");
        builder.setParameter("text", System.nanoTime() + "");
        HttpPost request = new HttpPost(builder.build());
        return httpClient.execute(request);
    }

    private CloseableHttpResponse doFindAll() throws IOException {
        HttpPost request = new HttpPost(tomcat.getUrl() + "/message/findAll");
        return httpClient.execute(request);
    }

    @Test
    public void createUsers() throws IOException, URISyntaxException {
        for (String user : new String[]{"Nikita"}) {
            CloseableHttpResponse response = doAuth(user);
            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("application/json", response.getEntity().getContentType().getValue());
            assertEquals(String.format("\"%s\"", user), EntityUtils.toString(response.getEntity()));
            response.close();
            user_cookie.put(user, response.getFirstHeader("Set-Cookie").getValue());
        }
    }

//    @Test
    public void creatMessages() throws IOException, URISyntaxException {
        createUsers();
        for (String cookie : user_cookie.values()) {
            CloseableHttpResponse response = doAdd(cookie);
            assertEquals(200, response.getStatusLine().getStatusCode());
            assertEquals("application/json", response.getEntity().getContentType().getValue());
            response.close();
        }
    }

    @Test
    public void listMessages() throws IOException, URISyntaxException {
        creatMessages();
        CloseableHttpResponse response = doFindAll();
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}


