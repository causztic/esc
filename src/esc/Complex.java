package esc;

import java.util.Scanner;

public class Complex {
  private double r;
  private double i;

  Complex(double r, double i) {
    this.r = r;
    this.i = i;
  }

  public String toString() {
	String output = "" + r;
    if (i > 0)
      output += '+'; 
    return output + i + 'i';
  }

  public Complex add(Complex other) {
    return add(this, other);
  }

  public static Complex add(Complex c1, Complex c2) {
    return new Complex(c1.r+c2.r, c1.i+c2.i);
  }

  public Complex subtract(Complex other) {
    return subtract(this, other);
  }

  public static Complex subtract(Complex c1, Complex c2) {
    return new Complex(c1.r-c2.r, c1.i-c2.i);
  }

  public Complex multiply(Complex other) {
    return multiply(this, other);
  }

  public static Complex multiply(Complex c1, Complex c2) {
    return new Complex(c1.r*c2.r - c1.i*c2.i, c1.r*c2.i + c1.i*c2.r);
  }

  public Complex divide(Complex other) {
	  return divide(this, other);
  }
  
  public static Complex divide(Complex c1, Complex c2) {
    return new Complex(
      (c1.r*c2.r+c1.i*c2.i)/(c2.r*c2.r+c2.i*c2.i),
      (c1.i*c2.r-c1.r*c2.i)/(c2.r*c2.r+c2.i*c2.i));
  }
  

  public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter first number's real part:" );
	double r1 = sc.nextDouble();
	System.out.print("Enter first number's imaginary part:" );
	double i1 = sc.nextDouble();
	System.out.print("Enter second number's real part:" );
	double r2 = sc.nextDouble();
	System.out.print("Enter second number's imaginary part:" );
	double i2 = sc.nextDouble();
	sc.close();
    Complex c = new Complex(r1,  i1);
    Complex d = new Complex(r2, i2);
    System.out.println(c + " + " + d + " = " + c.add(d));
    System.out.println(c + " - " + d + " = " + c.subtract(d));
    System.out.println(c + " * " + d + " = " + c.multiply(d));
    System.out.println(c + " / " + d + " = " + c.divide(d));
  }
}
       