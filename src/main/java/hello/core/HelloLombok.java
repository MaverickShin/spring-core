package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    public String name;
    public int age;

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("test");

        String name = helloLombok.getName();
        System.out.println("test = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
