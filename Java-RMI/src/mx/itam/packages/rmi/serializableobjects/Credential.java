package mx.itam.packages.rmi.serializableobjects;

import java.io.Serializable;

public class Credential implements Serializable {
    String name;
    String estado;
    int age;
    String password;

    public Credential(String name, String estado, int age, String password) {
        this.name = name;
        this.estado = estado;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
