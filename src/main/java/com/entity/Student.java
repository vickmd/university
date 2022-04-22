package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

//    @ElementCollection
//    @CollectionTable(
//            name="image",
//            joinColumns = @JoinColumn(name="student_id")
//    )
//    @OrderColumn
//    @Column(name="file_name")
//    private List<String> images = new ArrayList<String>();
    //tabelul image are coloanele studentid file_name order_image

    @ElementCollection
    @CollectionTable(name="image")
    @MapKeyColumn(name="file_name") //Maps Key
    @Column(name="image_name") //Maps Value
  //  @OrderBy mai jos analogie
    @SortComparator(ReverseStringComparator.class)
    private SortedMap<String,String> images = new TreeMap<String, String>();
    //tabelul image are coloanele studentid file_name image_name

//    @ElementCollection
//    @CollectionTable(name="image")
//    @OrderBy("file_name ASC")
//    @Column(name="file_name")
//    private Set<String> images = new LinkedHashSet<String>();
    //tabelul image are coloanele studentid file_name

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Reverse String (comparator...)
    public static class ReverseStringComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
}
