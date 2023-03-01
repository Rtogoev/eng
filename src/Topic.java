import java.util.List;

public record Topic(String name, List<Question> questions, List<String> variants) {
}
