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
    endif;
    if n < 0:
        print 'here';
        result = 0;
    endif;
    return result;
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

print 'Enter n for factorial: ';
n = read;
print 'The result for the number you entered for factorial is: ';
print factorial(n);
print '\n';
