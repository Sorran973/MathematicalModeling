package lab1;

/**
 * Created by ArtemBulkhak on 14/02/2019.
 */
public class lab1 {

    public static void main(String[] args){


        Galilei galilei = new Galilei();
        System.out.println("Method Galilei: ");
//        long startTime1 = System.currentTimeMillis();
        galilei.funcGalilei();
//        long timeSpent1 = System.currentTimeMillis() - startTime1;
//        System.out.println("программа выполнялась " + timeSpent1 + " миллисекунд");

        Newton newton = new Newton();
        System.out.println("Method Newton: ");
//        long startTime2 = System.currentTimeMillis();
        newton.rungeKutta();
//        long timeSpent2 = System.currentTimeMillis() - startTime2;
//        System.out.println("программа выполнялась " + timeSpent2 + " миллисекунд");

        DrawerLines drawerLines = new DrawerLines("lab1", "Galilei", "Newton", galilei.getXs(), galilei.getYs(),
                newton.getXs(), newton.getYs(), "x", "y");
        drawerLines.draw();

    }
}
