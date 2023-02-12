package task;

public enum Type {
    WORK("Рабочая"), PERSONAL("Личная");
    private final String type;

    public String type() {
        return type;
    }


    Type(String type) {
        this.type = type;
    }

}
