package calls;

public enum Group {
    WORK ("Работа"),
    FAMILY ("Семья"),
    FRIENDS ("Друзья");

    private String title;

    Group(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}