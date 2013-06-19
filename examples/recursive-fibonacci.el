def fibonacci(n)
    if n == 0:
        return 0;
    endif;
    if n == 1:
        return 1;
    endif;
    return fibonacci(n - 1) + fibonacci(n - 2);
enddef;

print 'Enter n for fibonacci number: ';
n = read;
print 'The result for the fibonacci number entered is: ';
print fibonacci(n);
print '\n';
