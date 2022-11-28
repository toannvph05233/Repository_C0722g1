package codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 20, message = "Tên sai định dạng")
    private String name;
    private String img;

    @Min(value = 18, message = "Tuổi phải trên 18")
    @Max(value = 80, message = "già quá rồi")
    private int age;
    @ManyToOne
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(String name, String img, int age, ClassRoom classRoom) {
        this.name = name;
        this.img = img;
        this.age = age;
        this.classRoom = classRoom;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
