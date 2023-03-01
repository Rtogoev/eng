import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopicUtils {
    public static Map<String, Topic> createTopics(File topicsDirectory) throws IOException {
        Map<String, Topic> topics = new TreeMap<>();
        for (File topicFile : topicsDirectory.listFiles()) {
            if (topicFile.isDirectory()) {
                String name = topicFile.getName();
                topics.put(name, createTopic(topicFile));
            }
        }
        return topics;
    }

    public static Topic createTopic(File topicFile) throws IOException {
        List<Question> questions = new ArrayList<>();
        List<String> variants = new ArrayList<>();
        for (File file : topicFile.listFiles()) {
            if (file.getName().contains("questions")) {
                questions = createQuestions(file);
            } else if (file.getName().contains("variants")) {
                variants = createVariants(file);
            }
        }
        System.out.println("Topic " + topicFile.getName() + " created");
        return new Topic(topicFile.getName(), questions, variants);
    }

    public static List<String> createVariants(File file) throws IOException {
        List<String> variants = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                variants.add(reader.readLine());
            }
        }
        variants.forEach(System.out::println);
        return variants;
    }

    public static List<Question> createQuestions(File file) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String[] split = reader.readLine().split("\\|");
                questions.add(new Question(split[0], split[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        questions.forEach(System.out::println);
        return questions;
    }
}
