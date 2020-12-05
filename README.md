# LoginSystemDPS

Luis Felipe Castaño


File | position  | Error/Rule/Recommendation | Comments 
:-----:  | :-----:  | :-----:  | :-----:
Login.java | 29 - 35 | IDS01-J. Normalize strings before validating them | They don't validate or normalize the input before use it
Login.java | 19 - 27 | IDS08-J. Sanitize untrusted data included in a regular expression | since regular expressions are added to validate the input, it is necessary to sanitize the data as well
Login.java | 17, 51 - 56 |  Limit the lifetime of sensitive data | Now the password variable is stored in an array and the clearArray function is called as soon a sit is no longer used
Validate.java | 17 - 22 | Store passwords using a hash function | The encrypted password is added to the database, then the query to the database for logging is modified and the SCryptUtil.check function is added


The sql is added for testing, the username and password are as follows: 
felipe@gmail.com, A1234567

Link github: https://github.com/felipe-castano-95/LoginSystemDPS