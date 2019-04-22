package com.xqx.audition;

/**
 * ������Ķ�̬
 * ��̬���Ƕ�����ֱ�����ʽ�����֡�
 *
 * ��ʵ�У��������ǰ��� F1 �����������
 *
 * �����ǰ�� Flash �����µ����ľ��� AS 3 �İ����ĵ���
 * �����ǰ�� Word �µ����ľ��� Word ������
 * �� Windows �µ����ľ��� Windows ������֧�֡�
 * ͬһ���¼������ڲ�ͬ�Ķ����ϻ������ͬ�Ľ����
 *
 * ��̬���ŵ�
 * 1. ��������֮�����Ϲ�ϵ
 * 2. ���滻��
 * 3. ��������
 * 4. �ӿ���
 * 5. �����
 * 6. ����
 * ��̬���ڵ�������Ҫ����
 * �̳�
 * ��д
 * ��������ָ���������
 * ���磺
 *
 * Parent p = new Child();
 * ��ʹ�ö�̬��ʽ���÷���ʱ�����ȼ�鸸�����Ƿ��и÷��������û�У�������������У���ȥ���������ͬ��������
 *
 * ��̬�ĺô�������ʹ���������õ���չ�������Զ�������Ķ������ͨ�ô���
 */
public class Demo_4_21_2 {
    public static void main(String[] args) {
        show(new Cat());  // �� Cat ������� show ����
        show(new Dog());  // �� Dog ������� show ����

        Animal a = new Cat();  // ����ת��
        a.eat();               // ���õ��� Cat �� eat
        Cat c = (Cat)a;        // ����ת��
        c.work();        // ���õ��� Cat �� work
    }

    public static void show(Animal a)  {
        a.eat();
        // �����ж�
        if (a instanceof Cat)  {  // è��������
            Cat c = (Cat)a;
            c.work();
        } else if (a instanceof Dog) { // ����������
            Dog c = (Dog)a;
            c.work();
        }
    }
}

abstract class Animal {
    abstract void eat();
}

class Cat extends Animal {
    public void eat() {
        System.out.println("����");
    }
    public void work() {
        System.out.println("ץ����");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("�Թ�ͷ");
    }
    public void work() {
        System.out.println("����");
    }
}

