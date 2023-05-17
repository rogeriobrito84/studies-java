package com.studies.studiesjava.studies.basic;

import java.math.BigDecimal;

/**
 *
 */
public class Variable {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Tipos primitivos
        int x = 1;
        long y = 12;
        double z = 12.12;
        float a = 130;
        byte b = 5;
        short c = 543;
        char d = 'A';
        char e = 2;
        boolean f = false;

        //classes wrapper
        Integer a1 = new Integer(1);
        String nome = new String("Luis");
        Long teste = Long.valueOf(12);
        Double testeDouble = Double.valueOf(123.12);
        Byte testeByte = Byte.valueOf("1");
        Short testeShort = Short.valueOf("12");
        Boolean testeBooolean = Boolean.valueOf("1233");
        BigDecimal testeBig = new BigDecimal("12.0");

    }

    /**
     *
     * @param nome
     * @return
     */
    public static String getSobreNome(String nome){

        return nome.concat(" Farias");
    }


}
