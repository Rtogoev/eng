import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Topic> topics = TopicUtils.createTopics(new File("topics"));
        TestService.printMainMenu();
        TestService.processInput(topics);
    }
}