package ru.itgirl.jbdcspringexample.model;

public class Books {
    private Long id;
    private String name;

    public Books(long id) {
    }

    public Books(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
