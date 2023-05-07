package com.organazer.web.weborganaizer.model;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Getter
@Setter
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "type_of_test")
    @Enumerated(EnumType.STRING)
    private TypeOfTest typeOfTest;
    @Column(name = "conditions")
    private String condition;

    public Lesson() {
    }
    @Enumerated(EnumType.STRING)
    public TypeOfTest getTypeOfTest() {
        return typeOfTest;
    }


    public Lesson(String name, String typeOfTest, String condition) {
        this.name = name;
        this.typeOfTest = TypeOfTest.valueOf(typeOfTest);
        this.condition = condition;
    }

    public void setTypeOfTest(String typeOfTest) {
        this.typeOfTest = TypeOfTest.valueOf(typeOfTest);
    }
    public void setTypeOfTest(TypeOfTest typeOfTest) {
        this.typeOfTest = typeOfTest;
    }

}
