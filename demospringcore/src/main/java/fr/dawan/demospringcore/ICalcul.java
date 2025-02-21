package fr.dawan.demospringcore;

public interface ICalcul {
    void add();
    default void test() {};
    static void delete() {};
}
