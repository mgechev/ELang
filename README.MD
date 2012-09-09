EduLang
=======

EduLang is a simple programming language written in Java. It's originally created for educating myself in better understanding the programming languages.
Anyway it's easy syntax could be used for educational purposes - teaching beginner programmers.

Example
-------
Here is a sample program which can be used for finding factorial:

### EduLang

    print 'Please, enter number: ';
    n = read;
    counter = n;
    result = 1;
    while counter > 0:
        result = counter * result;
        counter = counter -1;
    endwhile;
    print 'The factorial of ';
    print n;
    print ' is: ';
    print result;


License
--------
The language is distributed under the terms of the GPLv3.0.
