ELang
=======

ELang is a simple programming language written in Java. It is strongly typed imperative language.

I originally created it to educate myself in better understanding programming languages.

In it's current version ELang includes:

  * Arithmetical expressions
  * Boolean expressions
  * Defining variables
  * Defining functions
  * Printing to the standard output
  * Reading from the standard input
  * If and while statements
  * Built-in math functions
    * sin
    * cos
    * tan
    * cotan
    * pow
    * abs
    * ceil
    * floor
    * round
    * log

Example
-------
Here is a sample program which can be used for finding factorial and the n-th number of Fibonacci:

### ELang

    def fibonacci(n)
        if n >= 0:
            a1 = 0;
            a2 = 1;
            current = 0;
            while n > current:
                temp = a2;
                a2 = a1 + a2;
                a1 = temp;
                current = current + 1;
            endwhile;
            result = a1;
        else:
            result = 0;
        endif;
        return result;
    enddef;

    def recursiveFibonacci(n)
        if n == 0:
            return 0;
        endif;
        if n == 1:
            return 1;
        endif;
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    enddef;

    def factorial(n)
        result = 1;
        current = 1;
        while current <= n:
            result = result * current;
            current = current + 1;
        endwhile;
        return result;
    enddef;

    print 'Enter n for fibonacci number: ';
    n = read;
    print 'The result for the fibonacci number entered is: ';
    print fibonacci(n);
    print '\n';

    print 'Enter n for fibonacci number: ';
    n = read;
    print 'The result for the fibonacci number using the recursive fibonacci function is: ';
    print recursiveFibonacci(n);
    print '\n';

    print 'Enter n for factorial: ';
    n = read;
    print 'The result for the number you entered for factorial is: ';
    print factorial(n);
    print '\n';

License
--------
The language is distributed under the terms of the GPLv3.0.
