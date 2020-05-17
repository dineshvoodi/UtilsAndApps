#####IP Address
IP address is a string in the form "A.B.C.D", where the value of A, B, C, and D may range from 0 to 255. Leading zeros are allowed. The length of A, B, C, or D can't be greater than 3.

Some valid IP address:
* 000.12.12.034
* 121.234.12.12
* 23.45.12.56

Some invalid IP address:
* 000.12.234.23.23
* 666.666.23.23
* .213.123.23.32
* 23.45.22.32.
* I.Am.not.an.ip

#####Username
Username is considered valid if all the following constraints are satisfied:

The username consists of 8 to 30 characters inclusive. If the username consists of less than 8 or greater than 30 characters, then it is an invalid username.
The username can only contain alphanumeric characters and underscores (_). Alphanumeric characters describe the character set consisting of lowercase characters [a-z], uppercase characters [A-Z], and digits [0-9].
The first character of the username must be an alphabetic character, i.e., either lowercase character [a-z] or uppercase character [A-Z].