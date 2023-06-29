package com.studies.studiesjava.studies.basic;

public class Lists {

    //Arrays
    Integer listaNumeros[] = new Integer[5];

    //Mais usada:
    Integer[] listaNumeros2 = new Integer[5];

    String[] listaNomes = new String[12];

    Double[] listaDoubles = new Double[4];

    static String[] listaNomes2 = {"Nome1"};

    public static void main(String[] args) {

        System.out.println(Lists.listaNomes2[2]);

        Lists l = new Lists();

        l.listaNomes[11] = "Luis";
        System.out.println(l.listaNomes[11]);

        int v1 = 2;
        int v2 = 2;

        System.out.println(v1);
        System.out.println(l.soma(v1, v2));
        System.out.println(v1);

        System.out.println("Soma: " + l.soma(1, 2, 3, 4, 5, 6, 7, 8));
    }

    public int soma(int a, int b) {
        a = a + b;
        return a;
    }

    public int soma(int a, int b, int c) {

        a = a + b + c;
        return a;

    }

    public int soma(int... numeros) {

        int total = 0;

        for (int i = 0; i < numeros.length; i++) {
            //System.out.println("index: "+i);
            total += numeros[i];
        }
        return total;
    }
}

