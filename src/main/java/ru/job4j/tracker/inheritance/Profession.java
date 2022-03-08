package ru.job4j.tracker.inheritance;

public class Profession {
    private String name;
    private String surname;
    private String education;
    private String birthday;

    public Profession(String educ) {
        this.name = "Vladislav";
        this.surname = "Kotov";
        this.education = educ;
        this.birthday = "10.09.1999";
    }

    public String getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEducation() {
        return education;
    }
}
