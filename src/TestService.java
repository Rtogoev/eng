import java.util.*;

public class TestService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void processInput(Map<String, Topic> topics) {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> testAll(topics);
                case "2" -> choose(topics);
                case "3" -> {
                    return;
                }
                case "p" -> printMainMenu();
                default -> System.out.println("incorrect input");
            }
        }
    }

    private static void choose(Map<String, Topic> topics) {
        topics.keySet().forEach(System.out::println);
        System.out.println(test(topics.get(scanner.nextLine())));
    }

    public static TestResult test(Topic topic) {
        int errors = 0;
        List<Question> problems = new ArrayList<>();
        Collections.shuffle(topic.questions());
        List<String> variants = topic.variants();
        for (Question question : topic.questions()) {
            System.out.println();
            System.out.println(question.question());
            System.out.println();
            printAnswers(variants, question.rightAnswer());
            if (!question.rightAnswer().equals(scanner.nextLine())) {
                errors++;
                problems.add(question);
            }
        }
        return new TestResult(errors, problems);
    }

    private static void printAnswers(List<String> variants, String rightAnswer) {
        Set<String> chosenVariants = new HashSet<>();
        chosenVariants.add(rightAnswer);
        while (chosenVariants.size() < 4) {
            chosenVariants.add(variants.get((int) (Math.random() * variants.size())));
        }

        List<String> finalVariants = new ArrayList<>(chosenVariants);
        Collections.shuffle(finalVariants);
        System.out.println(finalVariants.get(0) + "\t" + finalVariants.get(1));
        System.out.println(finalVariants.get(2) + "\t" + finalVariants.get(3));
    }

    public static void testAll(Map<String, Topic> topics) {
        topics.values().forEach(TestService::test);
    }

    public static void printMainMenu() {
        System.out.println();
        System.out.println("Choose mode");
        System.out.println("1 - all");
        System.out.println("2 - choose topic");
        System.out.println("3 - exit");
        System.out.println();
        System.out.println("p - print this menu again");
        System.out.println();
    }
}
