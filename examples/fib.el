print 'Please, enter number: ';
n = read;
first = 0;
second = 1;
temp = 0;
while n >= 0:
    n = n - 1;
    temp = second;
    second = first + second;
    first = temp;
endwhile;
print 'The result is: ';
print second;
