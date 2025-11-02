package com.example.mathprojecteylon;

import java.util.Random;

public class Exercise {
    private int num1;
    private int num2;
    private int result;
    private Inter inter;


    public Exercise (Inter inter){
        this.inter=inter;
    }
    public void etgar() {
        Random random = new Random();
        num1 = random.nextInt(25);
        num2 = random.nextInt(50) + 5;
        result=num1*num2;
        inter.showNumber(num1,num2);

}
    public void kefel(){
        Random random = new Random();
        num1 = random.nextInt(20);
        num2 = random.nextInt(10);
        result=num1*num2;
        inter.showNumber(num1,num2);
    }
    public void luch(){
        Random random = new Random();
         num1 = random.nextInt(10);
         num2 = random.nextInt(10);
        result=num1*num2;
        inter.showNumber(num1,num2);
    }

    public Boolean isCorrect(String x){
      String r=this.result+"";
     if(r.equals(x+""))
         return true;
     else
         return false;
    }

}
