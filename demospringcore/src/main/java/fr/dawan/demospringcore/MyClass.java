package fr.dawan.demospringcore;

public class MyClass implements ICalcul{
    @Override
    public void add() {

    }

    @Override
    public void test() {
        ICalcul.super.test();
    }
}
