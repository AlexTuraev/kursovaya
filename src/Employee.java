public class Employee {
    public static int counter;
    protected int id;
    protected String name;
    protected String surname;
    protected String secondName;
    protected int department;
    protected float salary;

    public Employee(String surname, String name, String secondName, int department, float salary) {
        if (department < 1 || department > 5) {
            throw new RuntimeException("По условиям задачи номер отдела department от 1 до 5");
        }
        if (salary < 0) {
            throw new RuntimeException("ЗП не может быть отрицательной");
        }

        id = counter++;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getId() {
        return id;
    }

    public int getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    public static int getCounter() {
        return counter;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return getSurname() + " " + getName() + " " + getSecondName();
    }

    public String toString() {
        return "ID: " + getId() + " " + getSurname() + " " + getName() + " " + getSecondName() + ". Department: " + getDepartment() +
                ". Salary: " + getSalary();
    }

    public void indexSalary(float percent) {
        setSalary(getSalary() + getSalary()/100f*percent);
    }

    public String getDataWithoutDepartment() {
        return "id: " + getId() + " ФИО: " + getFullName() + " ЗП = " + getSalary();
    }
}
