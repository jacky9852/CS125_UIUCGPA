
package com.example.cs125_uiucgpa;

public class AvgGpaCalc {
    private int totalStudentCount;
    private int numA;
    private int numaMin;
    private int numbPlus;
    private int numB;
    private int numbMin;
    private int numcPlus;
    private int numC;
    private int numcMin;
    private int numdPlus;
    private int numD;
    private int numdMin;
    private int numF;

    // Making sure the class numbers are same. And Calc Avg GPA.
    public double avgGpaCalc(String name, int classNum) {
        double avgGpa = 0;
        //Run down the list to find all the same classes.
        for (int i = 0; all2019Data.get(i)[3].equals(name); i++) {
            // Making sure they have the same class numbers.
            while (all2019Data.get(i)[4] == classNum) {
                //Adding all the students in that class and count the total number of students in the same class.
                numA = Integer.parseInt(all2019Data.get(i)[6]) + Integer.parseInt(all2019Data.get(i)[7]);
                numaMin = Integer.parseInt(all2019Data.get(i)[8]);
                numbPlus = Integer.parseInt(all2019Data.get(i)[9]);
                numB = Integer.parseInt(all2019Data.get(i)[10]);
                numbMin = Integer.parseInt(all2019Data.get(i)[11]);
                numcPlus = Integer.parseInt(all2019Data.get(i)[12]);
                numC = Integer.parseInt(all2019Data.get(i)[13]);
                numcMin = Integer.parseInt(all2019Data.get(i)[14]);
                numdPlus = Integer.parseInt(all2019Data.get(i)[15]);
                numD = Integer.parseInt(all2019Data.get(i)[16]);
                numdMin = Integer.parseInt(all2019Data.get(i)[17]);
                numF = Integer.parseInt(all2019Data.get(i)[18]) + Integer.parseInt(all2019Data.get(i)[19]);
                totalStudentCount = numA + numaMin
                        + numbPlus + numB + numbMin
                        + numcPlus + numC + numcMin
                        + numdPlus + numD + numdMin
                        + numF;
            }
        }
        // Calc the avg of that class by adding the number of A,B,C... together and divided by the total students.
        avgGpa = ((numA * 4) + (numaMin * 3.67)
                + (numbPlus * 3.33) + (numB * 3) + (numbMin * 2.67)
                + (numcPlus * 2.33) + (numC * 2) + (numcMin * 1.67)
                + (numdPlus * 1.33) + (numD * 1) + (numdMin * 0.67)) / totalStudentCount;
        return avgGpa;
    }
}
