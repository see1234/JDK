import java.util.ArrayList;
import java.util.List;

public class JobDirectory {
    private List<JobHuman> humans;

    public JobDirectory() {
        humans = new ArrayList<>();
    }
    //метод добавление нового сотрудника в справочник
    public void addHuman(JobHuman human) {
        humans.add(human);
    }

    //метод, который ищет сотрудника по стажу (может быть список)
    public List<JobHuman> findByExp(int exp) {
        List<JobHuman> matchingEmployees = new ArrayList<>();
        for (JobHuman jobHuman : humans) {
            if (jobHuman.getExp() == exp) {
                matchingEmployees.add(jobHuman);
            }
        }
        return matchingEmployees;
    }

    //метод, который выводит номер телефона сотрудника по имени (может быть список)
    public List<String> findPhoneNumberByName(String name) {
        List<String> phoneNumbers = new ArrayList<>();
        for (JobHuman employee : humans) {
            if (employee.getName().equals(name)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }
    //метод, который ищет сотрудника по табельному номеру
    public JobHuman findByHumanId(String employeeId) {
        for (JobHuman jobHuman : humans) {
            if (jobHuman.getEmployeeId().equals(employeeId)) {
                return jobHuman;
            }
        }
        return null;
    }

}
