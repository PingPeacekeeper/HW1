package pojo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SID")
    private int SID;

    @Column(name = "SName")
    private String SName;

    @Column(name = "Display")
    private String display;

    @Column(name = "Hidden")
    private String hidden;

    public Student(String name) {
        this.SName = name;
        hidden = "hidden text";
        display= "shown text";
    }

    @Override
    public String toString() {
        return "Student{" + "SID=" + SID + ", SName='" + SName + '\'' + '}';
    }


}
