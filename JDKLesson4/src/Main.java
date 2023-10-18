import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobDirectory jobDirectory = new JobDirectory();
        jobDirectory.addHuman(new JobHuman("#3123", "32423", "Petya", 5));
        List<JobHuman> jobSortOfExp = jobDirectory.findByExp(5);
        System.out.println("Array find by exp:");
        for (JobHuman jobHuman : jobSortOfExp) {
            System.out.println(jobHuman.getName());
        }
    }
}