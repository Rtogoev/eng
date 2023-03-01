import java.util.List;

public record TestResult(
        int errors,
        List<Question> problems
) {
}
