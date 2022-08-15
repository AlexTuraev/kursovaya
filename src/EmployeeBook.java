public class EmployeeBook {
    private Employee[] employees;

    public EmployeeBook() {
        employees = new Employee[10];
    }
    public String getEmployeeList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sb.append(employees[i] + "\n");
            }
        }
        return sb.toString();
    }

    // Сумма затрат на зарплаты в месяц
    public float calcSumSalary() {
        float sum = 0f;
        for (Employee employee : employees) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    public Employee findMinSalary() {
        float min = employees[0].getSalary();
        int idx = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && min > employees[i].getSalary()) {
                min = employees[i].getSalary();
                idx = i;
            }
        }
        return employees[idx];
    }

    public Employee findMaxSalary() {
        float max = employees[0].getSalary();
        int idx = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && max < employees[i].getSalary()) {
                max = employees[i].getSalary();
                idx = i;
            }
        }
        return employees[idx];
    }

    public float calcAverageSalary() {
        int n = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                n++; // кол-во сотрудников реально присутствующих в массиве (заполнены данные на них) != null
            }
        }
        return calcSumSalary() / n;
    }

    public String getFullNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                sb.append(employees[i].getFullName() + "\n");
            }
        }
        return sb.toString();
    }

    // повышенная сложность
    public void indexSalaries(float percent) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                employees[i].indexSalary(percent);
            }
        }
    }

    // Поиск сотрудника с минимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public Employee findMinSalaryForDepartment(int dep) {
        float min = employees[0].getSalary();
        int idx = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment()==dep && min > employees[i].getSalary()) {
                min = employees[i].getSalary();
                idx = i;
            }
        }
        if (idx == -1){
            return null; // нет сотрудников в данном отделе
        } else{
            return employees[idx];
        }
    }

    // Поиск сотрудника с максимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public Employee findMaxSalaryForDepartment(int dep) {
        float max = employees[0].getSalary();
        int idx = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment()==dep && max < employees[i].getSalary()) {
                max = employees[i].getSalary();
                idx = i;
            }
        }
        if (idx == -1){
            return null; // нет сотрудников в данном отделе
        } else{
            return employees[idx];
        }
    }

    // Сумма затрат на ЗП по отделу
    public float calcSumSalaryForDepartment(int dep) {
        float sum = 0f;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    // Сумма средней ЗП по отделу. Возвращает Ср сумму ЗП или -1, если в отделе нет сотрудников
    public float calcAverageSalaryForDepartment(int dep) {
        float sum = 0f;
        int n = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sum += employee.getSalary();
                n++;
            }
        }

        if (n > 0) {
            return sum / n;
        } else{
            return(-1);
        }
    }

    // Проиндесировать ЗП сотрудников на % = percent отдела dep
    public void indexSalaries(float percent, int dep) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null && employees[i].getDepartment() == dep){
                employees[i].indexSalary(percent);
            }
        }
    }

    // Получить данные по всем сотрудникам отдела dep, не включая номер отдела
    public String getEmployeeListForDepartment(int dep) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }

        return sb.toString();
    }

    public String getListSalaryLess(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }

    public String getListSalaryOver(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() >= salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }

    // Добавление нового сотрудника. Создание объекта и добавление в свободную ячейку массива employees
    public void addNewEmployee(String name, String surname, String secondName, int department, float salary) {
        Employee newEmployee = new Employee(name, surname, secondName, department, salary);
        boolean success = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = newEmployee;
                success = true;
                break;
            }
        }
        if (!success) {
            throw new RuntimeException("Нет места в массиве employees для нового сотрудника");
        }
    }

    // Удаление элемента массива (сотрудника) по ФИО
    public void deleteEmployee(String surname, String name, String secondName) {
        /*for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSurname().equalsIgnoreCase(surname) &&
                    employees[i].getName().equalsIgnoreCase(name) &&
                    employees[i].getSecondName().equalsIgnoreCase(secondName)) {
                employees[i] = null;
                System.out.println("Успешно удалены записи " + surname + " " + name + " " + secondName);
            }
        }*/

        int index = getEmployeeByFIO(surname, name, secondName);
        if (index >= 0) {
            employees[index] = null;
            System.out.println("Успешно удалены записи " + surname + " " + name + " " + secondName);
        }
    }

    // Поиск индекса элемента в массиве с заданым ФИО (поиск сотрудника по ФИО)
    public int getEmployeeByFIO(String surname, String name, String secondName) {
        int start = 0; // можно модифицировать, запускать поиск с нужного элемента start
        int resIndex = -1; // Возвращается индекс -1 ,если элеиент не найден
        for (int i = start; i < employees.length; i++) {
            if (employees[i] != null &&
                    employees[i].getSurname().equalsIgnoreCase(surname) &&
                    employees[i].getName().equalsIgnoreCase(name) &&
                    employees[i].getSecondName().equalsIgnoreCase(secondName)) {
                resIndex = i;
                break;
            }
        }
        return resIndex;
    }

    // Изменение ЗП у сотрудника по ФИО
    public void changeSalaryByFIO(String surname, String name, String secondName, float newSalary) {
        if (newSalary < 0) {
            throw new RuntimeException("Передана ЗП меньше 0");
        }

        int index = getEmployeeByFIO(surname, name, secondName);
        if (index >= 0) {
            employees[index].setSalary(newSalary);
            System.out.println("ЗП " + surname + " " + name + " " + secondName + " успешно изменена");
        } else {
            System.out.println("Не удалось найти сотрудника с заданным ФИО: " + surname + " " + name + " " + secondName);
        }
    }

    public void changeDepartmentByFIO(String surname, String name, String secondName, int newDepartment) {
        if (newDepartment < 1 || newDepartment > 5) {
            throw new RuntimeException("Передан отдел за пределами допустимого (допустимо от 1 до 5)");
        }
        int index = getEmployeeByFIO(surname, name, secondName);
        if (index >= 0) {
            employees[index].setDepartment(newDepartment);
            System.out.println("Отдел для сотрудника " + surname + " " + name + " " + secondName + " успешно изменен");
        } else {
            System.out.println("Не удалось найти сотрудника с заданным ФИО: " + surname + " " + name + " " + secondName);
        }
    }

    // Удаление элемента массива (сотрудника) по id
    public void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                System.out.println("Успешно удалены записи с ID = " + id);
            }
        }
    }

    // 6. Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
    public String getListOrderByDep() {
        // создание массива для хранения ФИО по отделам. В 0-й ячейке - 1-й отдел, в 1-й - 2-й отдел ...
        StringBuilder [] stringBuilders = new StringBuilder[5];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder(); // инициализация
        }

        for (Employee employee : employees) {
            if (employee != null) {
                stringBuilders[employee.getDepartment()-1].append(employee.getFullName() + "\n");
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringBuilders.length; i++) {
            if (stringBuilders[i].length() > 0) {
                sb.append("Department " + (i+1) + "\n" + stringBuilders[i]);
            }
        }
        return sb.toString();
    }
}
