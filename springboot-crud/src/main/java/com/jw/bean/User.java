package com.jw.bean;



import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "t_user")
@Getter
@Setter
@ToString
@Generated
public class User implements Serializable {

  private static final long serialVersionUID = -72534564003203064L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long    id;
    @Column(name = "name",nullable = false,length =20)
    private String  name;
    @Column(name = "sex")
    private String  sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "address")
    private String  address;
    @Column(name = "school")
    private String  school;
}
